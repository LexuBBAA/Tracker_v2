package com.tracker.trackerv2

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import com.lexu.models.Priority
import com.lexu.models.Status
import com.lexu.models.Type
import com.tracker.trackerv2.datasource.providers.local.UserSessionProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import com.tracker.trackerv2.datasource.providers.local.room.entity.ProjectEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.SprintEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.TaskEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.UserEntity
import com.tracker.trackerv2.utils.SimpleSpinnerListener
import kotlinx.android.synthetic.main.activity_create_task.*
import kotlinx.android.synthetic.main.activity_project_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateTaskActivity : AppCompatActivity() {
    private lateinit var sessionProvider: UserSessionProvider
    private lateinit var appDatabase: AppDatabase

    private val newTaskEntity = TaskEntity(
        createdBy = "",
        title = "",
        sprintId = "",
        project = "",
        priority = "",
        status = "",
        type = ""
    )

    private lateinit var taskToUpdate : TaskEntity
    private var taskId : String? = null

    private lateinit var userId: String
    private lateinit var projects : List<ProjectEntity>

    private lateinit var projectUsers : List<UserEntity>
    private lateinit var projectTasks : List<TaskEntity>
    private lateinit var projectSprints : List<SprintEntity>

    private lateinit var typeAdapter: ArrayAdapter<String>
    private lateinit var statusAdapter: ArrayAdapter<String>
    private lateinit var priorityAdapter: ArrayAdapter<String>
    private lateinit var projectAdapter: ArrayAdapter<String>
    private lateinit var sprintAdapter: ArrayAdapter<String>
    private lateinit var assigneeAdapter: ArrayAdapter<String>

    private lateinit var partOfAdapter: ArrayAdapter<String>
    private lateinit var blocksAdapter: ArrayAdapter<String>
    private lateinit var linkedToAdapter: ArrayAdapter<String>
    private lateinit var subtaskAdapter: ArrayAdapter<String>
    private lateinit var parentAdapter: ArrayAdapter<String>
    private lateinit var epicAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        sessionProvider = UserSessionProvider(this)
        appDatabase = AppDatabase.getDatabase(this)

        createTaskLoadingView.visibility = View.VISIBLE

        typeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        statusAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        priorityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        projectAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        sprintAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        assigneeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        partOfAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        blocksAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        linkedToAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        subtaskAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        parentAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        epicAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)

        typeAdapter.addAll(Type.values().map { it.name })
        statusAdapter.addAll(Status.values().map { it.name })
        priorityAdapter.addAll(Priority.values().map { it.name })

        setupUi()

        taskId = intent.getStringExtra(KEY_TASK_ID_EXTRA)

        fetchData()
    }

    private fun setupUi() {
        createTaskTypeSpinner.adapter = typeAdapter
        createTaskTypeSpinner.onItemSelectedListener = object: SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                newTaskEntity.type = createTaskTypeSpinner.selectedItem as String
            }
        }
        createTaskStatusSpinner.adapter = statusAdapter
        createTaskStatusSpinner.onItemSelectedListener = object: SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                newTaskEntity.status = createTaskStatusSpinner.selectedItem as String
            }
        }
        createTaskPrioritySpinner.adapter = priorityAdapter
        createTaskPrioritySpinner.onItemSelectedListener = object: SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                newTaskEntity.priority = createTaskPrioritySpinner.selectedItem as String
            }
        }
        createTaskProjectSpinner.adapter = projectAdapter
        createTaskProjectSpinner.onItemSelectedListener = object: SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedProject = projects.find { "[${it.projectId}] ${it.title}" == createTaskProjectSpinner.selectedItem as String }
                newTaskEntity.project = selectedProject!!.projectId!!

                newTaskEntity.assignedTo = ""
                newTaskEntity.sprintId = ""
                newTaskEntity.parent = ""
                newTaskEntity.partOf = ""
                newTaskEntity.blocks = ""
                newTaskEntity.linkedTo = ""
                newTaskEntity.subtaskOf = ""
                newTaskEntity.epic = ""

                fetchProjectData(selectedProject)
            }
        }

        createTaskPartOfSpinner.adapter = partOfAdapter
        createTaskPartOfSpinner.onItemSelectedListener = object : SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //  TODO
            }
        }
        createTaskBlocksSpinner.adapter = blocksAdapter
        createTaskBlocksSpinner.onItemSelectedListener = object : SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //  TODO
            }
        }
        createTaskLinkedToSpinner.adapter = linkedToAdapter
        createTaskLinkedToSpinner.onItemSelectedListener = object : SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //  TODO
            }
        }
        createTaskSubtaskOfSpinner.adapter = subtaskAdapter
        createTaskSubtaskOfSpinner.onItemSelectedListener = object : SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //  TODO
            }
        }
        createTaskParentTaskSpinner.adapter = parentAdapter
        createTaskParentTaskSpinner.onItemSelectedListener = object : SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //  TODO
            }
        }
        createTaskEpicTaskSpinner.adapter = epicAdapter
        createTaskEpicTaskSpinner.onItemSelectedListener = object : SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //  TODO
            }
        }

        createTaskAssigneeSpinner.adapter = assigneeAdapter
        createTaskAssigneeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                createTaskAssigneeSpinner.setSelection(0)
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selection = createTaskAssigneeSpinner.selectedItem as String?
                newTaskEntity.assignedTo = if(!selection.isNullOrEmpty()) projectUsers.find { "@".plus(it.username) == selection }?.userId
                else null
            }
        }

        createTaskStoryPointsSpinner.onItemSelectedListener = object : SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                newTaskEntity.storyPoints = (createTaskStoryPointsSpinner.selectedItem as String?)?.toInt() ?: 0
            }
        }

        createTaskSubmitButton.setOnClickListener { createTask() }
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            userId = sessionProvider.getUserId() ?: ""

            appDatabase.getUserTeamProvider()
                .getForUser(userId)
                ?.let { userTeam ->
                    projects = appDatabase.getProjectsProvider()
                        .getAll()
                        .filter { project -> project.assignedTeam == userTeam.teamId }
                        .sortedBy { it.title }

                    val userIds = appDatabase.getUserTeamProvider()
                        .getForTeam(userTeam.teamId)
                        .map { it.userId }

                    val users = appDatabase.getUsersProvider()
                        .getAll()
                        .filter { it.userId in userIds }
                        .sortedBy { it.username }

                    runOnUiThread { onUsersProvided(users) }
                }

            runOnUiThread { onProjectsProvided() }

            if(taskId != null) {
                taskToUpdate = appDatabase.getTasksProvider()
                    .getDetails(taskId as String) as TaskEntity
                runOnUiThread { setupCopyData() }
            }
        }
    }

    @MainThread
    private fun setupCopyData() {
        createTaskTitleInputField.setText(taskToUpdate.title)
        val typeIndex = typeAdapter.getPosition(taskToUpdate.type)
        createTaskTypeSpinner.setSelection(typeIndex)

        val statusIndex = statusAdapter.getPosition(taskToUpdate.status)
        createTaskStatusSpinner.setSelection(statusIndex)

        val priorityIndex = statusAdapter.getPosition(taskToUpdate.priority)
        createTaskPrioritySpinner.setSelection(priorityIndex)

        val selectedProject = projects.find { it.projectId == taskToUpdate.project }
        val projectIndex = projectAdapter.getPosition("[${selectedProject!!.projectId}] ${selectedProject.title}")
        createTaskProjectSpinner.setSelection(projectIndex)

        createTaskDescriptionInputField.setText(taskToUpdate.description)

        val assigneeIndex = assigneeAdapter.getPosition("@".plus(taskToUpdate.assignedTo))
        createTaskAssigneeSpinner.setSelection(assigneeIndex)
    }

    private fun fetchProjectData(project: ProjectEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            projectSprints = appDatabase.getSprintsProvider()
                .getAllForProject(project.projectId as String)
                .sortedBy { it.createdDate }

            runOnUiThread { onSprintsProvided(projectSprints) }

            val userTeam = appDatabase.getUserTeamProvider()
                .getForTeam(project.assignedTeam as String)

            projectUsers = appDatabase.getUsersProvider()
                .getAll()
                .filter { user -> user.userId in userTeam.map { it.userId } }
                .sortedBy { it.username }

            runOnUiThread { onUsersProvided(projectUsers) }

            projectTasks = appDatabase.getTasksProvider()
                .getAllForProject(project.projectId as String)
                .sortedBy { it.title }

            runOnUiThread { onTasksProvided(projectTasks) }
        }
    }

    private fun createTask() {
        newTaskEntity.title = createTaskTitleInputField.text?.toString() ?: ""
        newTaskEntity.description = createTaskDescriptionInputField.text?.toString() ?: ""

        CoroutineScope(Dispatchers.IO).launch {
            val taskId = appDatabase.getTasksProvider()
                .getAll().count().toString().padStart(5, '0')

            val taskType = Type.valueOf(createTaskTypeSpinner.selectedItem as String)

            if(this@CreateTaskActivity.taskId != null) {
                newTaskEntity.id = taskToUpdate.id
                newTaskEntity.taskId = taskToUpdate.taskId
            } else {
                newTaskEntity.taskId = when(taskType) {
                    Type.STORY -> "str"
                    Type.TASK -> "tsk"
                    Type.SUBTASK -> "stsk"
                    Type.ISSUE -> "bug"
                    Type.QUESTION -> "qst"
                }.plus("-$taskId").toUpperCase()
            }

            newTaskEntity.createdBy = userId


            if(this@CreateTaskActivity.taskId != null) {
                appDatabase.getTasksProvider().update(newTaskEntity)
            } else {
                appDatabase.getTasksProvider()
                    .create(newTaskEntity)
            }

            runOnUiThread { onTaskSaved() }
        }
    }

    @MainThread
    private fun onProjectsProvided() {
        createTaskLoadingView.visibility = View.GONE
        projectAdapter.addAll(projects
            .map { pj -> "[${pj.projectId}] ${pj.title}" }
            .sortedBy { it }
        )
        projectAdapter.notifyDataSetChanged()
    }

    @MainThread
    private fun onSprintsProvided(sprints: List<SprintEntity>) {
        sprintAdapter.clear()
        sprintAdapter.addAll(sprints.map { "[".plus(it.sprintId).plus("] ").plus(it.title) }.sortedBy { it })
        sprintAdapter.notifyDataSetChanged()
    }

    @MainThread
    private fun onUsersProvided(users: List<UserEntity>) {
        assigneeAdapter.clear()
        assigneeAdapter.addAll(users.map { "@".plus(it.username) }.plus("").sortedBy { it.length })
        assigneeAdapter.notifyDataSetChanged()
    }

    @MainThread
    private fun onTasksProvided(tasks: List<TaskEntity>) {
        val taskLabels = tasks.map { "[".plus(it.taskId).plus("] ").plus(it.title) }
            .sortedBy { it }

        partOfAdapter.apply {
            clear()
            add("")
            addAll(taskLabels)
            notifyDataSetChanged()
        }
        blocksAdapter.apply {
            clear()
            add("")
            addAll(taskLabels)
            notifyDataSetChanged()
        }
        linkedToAdapter.apply {
            clear()
            add("")
            addAll(taskLabels)
            notifyDataSetChanged()
        }
        subtaskAdapter.apply {
            clear()
            add("")
            addAll(taskLabels)
            notifyDataSetChanged()
        }
        parentAdapter.apply {
            clear()
            add("")
            addAll(taskLabels)
            notifyDataSetChanged()
        }
        epicAdapter.apply {
            clear()
            add("")
            addAll(taskLabels)
            notifyDataSetChanged()
        }
    }

    @MainThread
    private fun onTaskSaved() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    companion object {
        const val KEY_IS_UPDATE_EXTRA = "is_update_extra"
        const val KEY_TASK_ID_EXTRA = "task_id_extra"
    }
}
