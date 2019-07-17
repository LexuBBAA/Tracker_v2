/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.tracker.trackerv2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import com.lexu.models.Priority
import com.lexu.models.Status
import com.lexu.models.Type
import com.lexu.tracking.OngoingTaskFragment
import com.lexu.tracking.PersonalStatsFragment
import com.lexu.tracking.TeamMembersFragment
import com.lexu.tracking.TeamStatsFragment
import com.lexu.tracking.delegates.OngoingTaskContract
import com.lexu.tracking.delegates.PersonalStatsContract
import com.lexu.tracking.delegates.TeamMembersFragmentDelegate
import com.lexu.tracking.delegates.TeamStatsContract
import com.lexu.tracking.models.DashboardMembersProgressItem
import com.lexu.tracking.models.TeamTask
import com.lexu.tracking.utils.DayLog
import com.tracker.trackerv2.configs.SearchUserConfigBundle
import com.tracker.trackerv2.datasource.providers.local.UserSessionProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import com.tracker.trackerv2.datasource.providers.local.room.entity.WorklogEntity
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.Date
import java.util.Calendar

class DashboardActivity : AppCompatActivity(), OngoingTaskContract.OngoingTaskDelegate,
    TeamStatsContract.TeamStatsDelegate, PersonalStatsContract.PersonalStatsDelegate,
    TeamMembersFragmentDelegate {
    private lateinit var userId: String
    private lateinit var appDatabase: AppDatabase

    private lateinit var primaryProjectId : String

    private lateinit var ongoingTaskFragment: OngoingTaskFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        appDatabase = AppDatabase.getDatabase(this)

        ongoingTaskFragment = OngoingTaskFragment(this)

        supportFragmentManager.beginTransaction()
            .replace(ongoingTaskFragmentContainer.id, ongoingTaskFragment, null)
            .commit()

        dashboardAddEntryButton.setOnClickListener { navigateToNewEntryActivity() }

        ongoingTaskFragment.setLoading()
        (dashboardPersonalStatsFragment as PersonalStatsFragment).setLoading()
        val teamStatsFragment = (dashboardTeamStatsFragment as TeamStatsFragment)
        teamStatsFragment.registerDelegate(this)
        teamStatsFragment.setLoading()
        (dashboardTeamMembersFragment as TeamMembersFragment).setLoading()

        CoroutineScope(Dispatchers.IO).launch {
            setupOngoingTask()
            setupPersonalData()
            setupTeamStats()
            setupTeamMembersStatus()
        }
    }

    private suspend fun setupOngoingTask() {
        userId = UserSessionProvider(this).getUserId() ?: ""
        val userOngoingTasks = appDatabase.getTasksProvider()
            .getAllAssignedToUser(userId)
            .filter { it.status == Status.IN_PROGRESS.name }
            .sortedByDescending { Type.valueOf(it.type).value }
            .sortedByDescending { Priority.valueOf(it.priority).value }
            .firstOrNull()

        val widgetTask = if (userOngoingTasks != null)
            TeamTask(
                Type.valueOf(userOngoingTasks.type),
                Status.valueOf(userOngoingTasks.status),
                userOngoingTasks.title,
                userOngoingTasks.taskId,
                Priority.valueOf(userOngoingTasks.priority)
            ) else null

        delay(500)

        runOnUiThread {
            ongoingTaskFragmentContainer.visibility = View.VISIBLE
            ongoingTaskFragment.setTask(widgetTask)
        }
    }

    private suspend fun setupTeamStats() {
        appDatabase.getUserTeamProvider().getForUser(userId)?.apply {
            val teamMembersIds =
                appDatabase.getUserTeamProvider().getForTeam(teamId).map { it.userId }
            val teamTasks = appDatabase.getTasksProvider().getAll()
                .filter { task -> task.assignedTo in teamMembersIds }
                .map { task ->
                    TeamTask(
                        Type.valueOf(task.type),
                        Status.valueOf(task.status),
                        task.title,
                        task.taskId,
                        Priority.valueOf(task.priority)
                    )
                }

            primaryProjectId = appDatabase.getProjectsProvider()
                .getAll()
                .sortedBy { it.createdDate }
                .first { it.createdBy in teamMembersIds }
                .projectId as String

            runOnUiThread {
                val teamStatsFragment = (dashboardTeamStatsFragment as TeamStatsFragment)
                teamStatsFragment.updateStats(teamTasks)
            }
        }
    }

    private suspend fun setupTeamMembersStatus() {
        val userTeam = appDatabase.getUserTeamProvider().getForUser(userId)
        userTeam?.let { userToTeam ->
            val items = appDatabase.getUserTeamProvider()
                .getForTeam(userToTeam.teamId)
                .filter { it.userId != userId }
                .map { userInTeam ->
                    val user = appDatabase.getUsersProvider()
                        .getAll()
                        .first { userInTeam.userId == it.userId }
                    val userLogs = appDatabase.getWorklogsProvider()
                        .getAllForUser(user.userId as String)
                        .filter { worklog ->
                            val cal = Calendar.getInstance()
                            cal.set(Calendar.HOUR_OF_DAY, 0)
                            cal.set(Calendar.MINUTE, 0)
                            cal.set(Calendar.SECOND, 0)
                            cal.set(Calendar.MILLISECOND, 0)

                            worklog.createdDate.after(cal.time)
                        }
                    Pair(user, userLogs)
                }
                .map { userWorklogs ->
                    DashboardMembersProgressItem(
                        userWorklogs.first.userId!!,
                        userWorklogs.first.username,
                        userWorklogs.first.avatarUrl,
                        DayLog(
                            Calendar.getInstance()[Calendar.DAY_OF_WEEK],
                            userWorklogs.second.fold(0.0) { total, logged -> total.plus(logged.value) }
                        )
                    )
                }

            runOnUiThread {
                val teamMemberStatusFragment = dashboardTeamMembersFragment as TeamMembersFragment
                teamMemberStatusFragment.setupData(items)
            }
        }
    }

    private suspend fun setupPersonalData() {
        val stats = appDatabase.getWorklogsProvider().getAllForUser(userId)
            .also { Log.i(DashboardActivity::class.simpleName, "Got: $it") }
            .filter {
                val cal = Calendar.getInstance()
                cal.firstDayOfWeek = Calendar.SUNDAY
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
                it.createdDate.after(cal.time)
                    .and(it.createdDate.before(Date(System.currentTimeMillis())))
            }
            .also {
                Log.i(DashboardActivity::class.simpleName, "Filtered: $it")
            }
            .groupBy {
                val cal = Calendar.getInstance()
                cal.time = it.createdDate
                cal.set(Calendar.HOUR, 0)
                cal.set(Calendar.MINUTE, 0)
                cal.set(Calendar.SECOND, 0)
                cal.set(Calendar.MILLISECOND, 0)
                cal.time
            }
            .also { Log.i(DashboardActivity::class.simpleName, "Grouped: $it") }
            .map { pair ->
                Pair(
                    pair.key,
                    pair.value.fold(0.0) { total, worklog -> total.plus(worklog.value) })
            }
            .also { Log.i(DashboardActivity::class.simpleName, "Paired: $it") }
            .map { pair ->
                val cal = Calendar.getInstance()
                cal.firstDayOfWeek = Calendar.SUNDAY
                cal.time = pair.first
                DayLog(cal.get(Calendar.DAY_OF_WEEK), pair.second)
            }
            .also { Log.i(DashboardActivity::class.simpleName, "Generated: $it") }
            .toMutableList()

        var missingDay = Calendar.SUNDAY
        while (stats.size < 7) {
            if (stats.find { it.day == missingDay } == null) stats.add(
                DayLog(missingDay, 0.0)
            )
            missingDay++
        }

        val sortedStats = stats.sortedBy { it.day }

        delay(1000)

        runOnUiThread {
            val personalStatsFragment =
                (dashboardPersonalStatsFragment as PersonalStatsFragment)
            personalStatsFragment.registerDelegate(this)
            personalStatsFragment.updateStats(sortedStats)
        }
    }

    override fun onWorklogSelected(worklog: DayLog) {
        Log.i(DashboardActivity::class.simpleName, "Worklog selected: $worklog")
    }

    override fun onNavigateToUserDetails() {
        val intent = Intent(this, PersonalStatusDetailsActivity::class.java)
        intent.putExtra(PersonalStatusDetailsActivity.KEY_USER_ID_EXTRA, userId)
        startActivityForResult(intent, REQUEST_CODE_PROFILE)
    }

    override fun onCategorySelected(taskType: Type) {
        val intent = Intent(this, ProjectDetailsActivity::class.java)
        intent.putExtra(ProjectDetailsActivity.KEY_PROJECT_ID_EXTRA, primaryProjectId)
        startActivity(intent)
    }

    @MainThread
    override fun onTaskTrackingStarted(task: TeamTask) {
        Log.i(DashboardActivity::class.simpleName, "Started to track effort for task: $task")
    }

    @MainThread
    override fun onTaskTrackingPaused(task: TeamTask) {
        Log.i(DashboardActivity::class.simpleName, "Paused tracking effort for task: $task")
    }

    @MainThread
    override fun onTaskTrackingStopped(task: TeamTask, loggedTime: Long) {
        Log.i(DashboardActivity::class.simpleName, "Stopped tracking effort for task: $task")
        CoroutineScope(Dispatchers.IO).async {
            val newWorklogId =
                "t-${(appDatabase.getWorklogsProvider().getAll().count() + 1).toString().padStart(
                    10,
                    '0'
                )}"
            val newWorklog = WorklogEntity(
                worklogId = newWorklogId,
                value = loggedTime.toDouble().div(60 * 60),
                relatesTo = task.id!!,
                createdBy = userId
            )
            appDatabase.getWorklogsProvider().create(newWorklog)
            setupPersonalData()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_ADD_ENTRY, REQUEST_CODE_PROFILE -> {
                if (resultCode == Activity.RESULT_OK) {
                    CoroutineScope(Dispatchers.IO).launch {
                        setupOngoingTask()
                        setupPersonalData()
                        setupTeamStats()
                        setupTeamMembersStatus()
                    }
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun navigateToNewEntryActivity() {
        val intent = Intent(this, AddNewEntryActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_ADD_ENTRY)
    }

    override fun onNavigateToTaskDetails(task: TeamTask, isEditMode: Boolean) {
        Log.i(DashboardActivity::class.simpleName, "Navigate to task details requested for: $task")
        val intent = Intent(this, TaskDetailsActivity::class.java)
        intent.putExtra(TaskDetailsActivity.KEY_TASK_ID_EXTRA, task.id)
        startActivity(intent)
    }

    override fun onNavigateToTaskList() {
        Log.i(DashboardActivity::class.simpleName, "Navigate to tasks list requested")
        val intent = Intent(this, SearchTaskActivity::class.java)
        startActivity(intent)
    }

    override fun onNavigateToUsersListClicked() {
        val intent = Intent(this, SearchUsersActivity::class.java)
        val searchConfig = SearchUserConfigBundle(userId)
        intent.putExtra(SearchUsersActivity.KEY_SEARCH_CONFIG_EXTRA, searchConfig)
        startActivity(intent)
    }

    override fun onNavigateToUserDetailsClicked(userId: String) {
        val intent = Intent(this, PersonalStatusDetailsActivity::class.java)
        intent.putExtra(PersonalStatusDetailsActivity.KEY_USER_ID_EXTRA, userId)
        startActivity(intent)
    }

    companion object {
        const val REQUEST_CODE_ADD_ENTRY = 1000
        const val REQUEST_CODE_PROFILE = 1001
    }
}
