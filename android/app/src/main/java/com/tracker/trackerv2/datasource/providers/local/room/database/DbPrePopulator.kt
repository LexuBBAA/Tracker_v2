package com.tracker.trackerv2.datasource.providers.local.room.database

import android.util.Log
import com.lexu.models.Priority
import com.lexu.models.Status
import com.lexu.models.Type
import com.tracker.trackerv2.datasource.providers.local.room.entity.ProjectEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.SprintEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.TaskEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.TeamEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.UserEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.WorklogEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.utils.UserTeamEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date
import java.util.Calendar
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

internal class DbPrePopulator(private val db: AppDatabase) {
    private val calendar = Calendar.getInstance()

    fun execute() = CoroutineScope(Dispatchers.IO).launch {
        populateUsers()
        populateTeams()
        populateUserTeams()
        populateProjects()
        populateTasks()
        populateWorklogs()
    }

    private suspend fun populateUsers() {
        val usersProvider = db.getUsersProvider()
        for(i in 1..15) usersProvider.create(UserEntity(
            userId = "u${i}upso${i}sjnfnf1-2ub41hu24b",
            username = "testUser$i",
            email = "test$i@test.com",
            phone = "100000000${i%10}",
            password = "Password123!"
        ))
    }

    private suspend fun populateTeams() {
        val userId = db.getUsersProvider().getAll().first().userId!!
        val teamsProvider = db.getTeamsProvider()
        for(i in 1..5) teamsProvider.create(TeamEntity(
            teamId = "tm-0000$i".toUpperCase(),
            name = "Test team",
            createdBy = userId
        ))
    }

    private suspend fun populateUserTeams() {
        val users = db.getUsersProvider().getAll().shuffled()
        val userTeamProvider = db.getUserTeamProvider()
        val teams = db.getTeamsProvider().getAll()
        for(i in 0 until teams.size) {
            val team = teams[i]
            userTeamProvider.create(UserTeamEntity(
                userId = users[i].userId!!,
                teamId = team.teamId!!
            ))
            userTeamProvider.create(UserTeamEntity(
                userId = users[i+5].userId!!,
                teamId = team.teamId as String
            ))
            userTeamProvider.create(UserTeamEntity(
                userId = users[i+10].userId!!,
                teamId = team.teamId as String
            ))
        }
    }

    private suspend fun populateProjects() {
        val userId = db.getUsersProvider().getAll().first { it.username == "testUser1" }.userId as String
        val teamId = (db.getUserTeamProvider().getForUser(userId) as UserTeamEntity).teamId
        val projectsProvider = db.getProjectsProvider()
        val project1Id = projectsProvider.create(ProjectEntity(
            title = "Test Project 1",
            createdBy = userId,
            projectId = "pj-00001".toUpperCase(),
            assignedTeam = teamId
        ))!!.projectId!!

        val sprintsProvider = db.getSprintsProvider()
        sprintsProvider.create(SprintEntity(
            sprintId = "S-000001".toUpperCase(),
            title = "Backlog",
            createdBy = userId,
            project = project1Id,
            status = "OPEN"
        ))

        val project2Id = projectsProvider.create(ProjectEntity(
            title = "Degree Project",
            createdBy = userId,
            projectId = "pj-00002".toUpperCase(),
            assignedTeam = teamId
        ))!!.projectId!!
        sprintsProvider.create(SprintEntity(
            sprintId = "S-000002".toUpperCase(),
            title = "Backlog",
            createdBy = userId,
            project = project2Id,
            status = "OPEN"
        ))
    }

    private suspend fun populateTasks() {
        val users = db.getUsersProvider().getAll()
        val creatorId = users.first { it.username == "testUser1" }.userId as String
        val assignees = users.filter { it.userId != creatorId }.shuffled()

        Log.d(DbPrePopulator::class.simpleName, "> Task creator ID: $creatorId")

        db.getProjectsProvider().getAll().forEach {project ->
            val backlogId = db.getSprintsProvider().getAllForProject(project.projectId as String).first().sprintId as String
            for(i in 0 until 15) {
                val assignee = assignees.random()
                val taskType : Type = when(Random.nextInt(0, 25)) {
                    in 6..10 -> Type.TASK
                    in 11..15 -> Type.SUBTASK
                    in 16..20 -> Type.ISSUE
                    in 21..25 -> Type.QUESTION
                    else -> Type.STORY
                }
                val taskStatus : Status = when(Random.nextInt(0, 7)) {
                    0 -> Status.OPEN
                    1 -> Status.IN_PROGRESS
                    2 -> Status.ON_HOLD
                    3 -> Status.DONE
                    4 -> Status.IN_QA
                    5 -> Status.REOPENED
                    6 -> Status.CLOSED
                    else -> Status.OPEN
                }
                Log.d(DbPrePopulator::class.simpleName, "> Task assignee ID: ${assignee.userId}")
                val newTask = generateTask(
                    taskIdSuf = db.getTasksProvider().getAll().count { it.type == taskType.name }.plus(1),
                    assignedTo = assignee.userId as String,
                    createdBy = creatorId,
                    sprintId = backlogId,
                    taskType = taskType,
                    taskStatus = taskStatus
                )

                Log.d(DbPrePopulator::class.simpleName, "> Creating entry for task: $newTask")
                db.getTasksProvider().create(newTask)
            }
        }
    }

    private suspend fun populateWorklogs() {
        val tasks = db.getTasksProvider().getAll()
        tasks.forEach { task ->
            db.getUsersProvider().getAll().shuffled().subList(0, 5).forEach { user ->
                val userNoOfWorklogs = Random.nextInt(0, 15)
                for(worklogIndex in 0 until userNoOfWorklogs) {
                    val newLogsCount = db.getWorklogsProvider().getAll().count() + 1
                    val idNo = newLogsCount.toString().padStart(10, '0')
                    calendar.time = Date(System.currentTimeMillis())
                    calendar.add(Calendar.DAY_OF_YEAR, Random.nextInt(-15, 0))
                    val worklog = WorklogEntity(
                        worklogId = "t-$idNo".toUpperCase(),
                        createdBy = user.userId as String,
                        createdDate = Date(calendar.time.time),
                        value = Random.nextDouble(13.7).div(userNoOfWorklogs),
                        relatesTo = task.taskId as String
                    )

                    Log.d(DbPrePopulator::class.simpleName, "> Creating Worklog: $worklog")
                    db.getWorklogsProvider().create(worklog)
                }
            }
        }
    }

    private suspend fun generateTask(taskIdSuf: Int, assignedTo: String, createdBy: String, sprintId: String, taskType: Type, taskStatus: Status): TaskEntity {
        val sprintProjectId = db.getSprintsProvider().getSprintById(sprintId)!!.project
        val projectId = db.getProjectsProvider().getAll().find { it.projectId == sprintProjectId }?.projectId as String
        val parentTaskId = db.getTasksProvider().getAllForSprint(sprintId).shuffled().firstOrNull { Type.valueOf(it.type.toUpperCase()) == Type.TASK }?.taskId
        val internalTaskType : Type = if(taskType == Type.SUBTASK && parentTaskId == null) Type.TASK
        else taskType

        calendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, Random.nextInt(1, 29))
        calendar.set(Calendar.MONTH, Random.nextInt(1, 7))
        calendar.set(Calendar.YEAR, 2018)

        return TaskEntity(
            taskId = when(internalTaskType) {
                Type.STORY -> "str"
                Type.TASK -> "tsk"
                Type.SUBTASK -> "stsk"
                Type.ISSUE -> "bug"
                Type.QUESTION -> "qst"
            }.plus("-$taskIdSuf").toUpperCase(),
            title = when(internalTaskType) {
                Type.STORY -> "Test Story-type task"
                Type.TASK -> "Test Task-type task"
                Type.SUBTASK -> "Test Sub-task-type"
                Type.ISSUE -> "Test Issue-type task"
                Type.QUESTION -> "Test Question-type task"
            }.plus(" <<$taskIdSuf>>"),
            assignedTo = assignedTo,
            createdBy = createdBy,
            createdDate = Date(calendar.time.time),
            sprintId = sprintId,
            subtaskOf = parentTaskId,
            type = internalTaskType.name,
            status = taskStatus.name,
            priority = when(Random.nextInt(0, 50)) {
                in 0..10 -> Priority.BLOCKER.name
                in 11..20 -> Priority.CRITICAL.name
                in 21..30 -> Priority.HIGH.name
                in 31..40 -> Priority.MEDIUM.name
                else -> Priority.LOW.name
            },
            project = projectId
        )
    }
}