/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.tracker.trackerv2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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
import com.lexu.tracking.delegates.TeamStatsContract
import com.lexu.tracking.models.DashboardMembersProgressItem
import com.lexu.tracking.models.TeamTask
import com.lexu.tracking.utils.DayLog
import com.tracker.trackerv2.datasource.providers.local.UserSessionProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import com.tracker.trackerv2.datasource.providers.local.room.entity.UserEntity
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.sql.Date
import java.util.Calendar

class DashboardActivity : AppCompatActivity(), OngoingTaskContract.OngoingTaskDelegate,
    TeamStatsContract.TeamStatsDelegate, PersonalStatsContract.PersonalStatsDelegate {
    private lateinit var userId: String
    private lateinit var appDatabase : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        userId = UserSessionProvider(this).getUserId() ?: ""
        appDatabase = AppDatabase.getDatabase(this)

        val ongoingTaskFragment = OngoingTaskFragment(this)

        supportFragmentManager.beginTransaction()
            .replace(ongoingTaskFragmentContainer.id, ongoingTaskFragment, null)
            .commit()

        CoroutineScope(Dispatchers.IO).async {
            val userOngoingTasks = appDatabase.getTasksProvider()
                .getAllAssignedToUser(userId)
                .sortedBy { Type.valueOf(it.type).value }
                .sortedBy { Priority.valueOf(it.priority).value }
                .firstOrNull { it.status.toUpperCase().contentEquals("IN_PROGRESS") }

            val widgetTask = if(userOngoingTasks != null)
                TeamTask(
                    Type.valueOf(userOngoingTasks.type),
                    Status.valueOf(userOngoingTasks.status),
                    userOngoingTasks.title,
                    userOngoingTasks.taskId,
                    Priority.valueOf(userOngoingTasks.priority)
            ) else null

            ongoingTaskFragmentContainer.visibility = View.VISIBLE
            ongoingTaskFragment.setTask(widgetTask)
        }

        CoroutineScope(Dispatchers.IO).async {
            val stats = appDatabase.getWorklogsProvider().getAllForUser(userId)
                .filter {
                    val cal = Calendar.getInstance()
                    cal.time = Date(System.currentTimeMillis())
                    cal.add(Calendar.DAY_OF_YEAR, -7)
                    it.createdDate.after(cal.time)
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
                .map { pair ->
                    val cal = Calendar.getInstance()
                    cal.time = pair.first
                    DayLog(cal.get(Calendar.DAY_OF_WEEK), pair.second)
                }
                .toMutableList()

            var missingDay = Calendar.SUNDAY
            while (stats.size < 7) {
                if (stats.find { it.day == missingDay } == null) stats.add(
                    DayLog(missingDay, 0.0)
                )
                missingDay++
            }

            stats.sortBy { it.day }
            delay(1500)

            runBlocking {
                runOnUiThread {
                    val personalStatsFragment = (dashboardPersonalStatsFragment as PersonalStatsFragment)
                    personalStatsFragment.registerDelegate(this@DashboardActivity)
                    personalStatsFragment.updateStats(stats)
                }
            }
        }

        CoroutineScope(Dispatchers.IO).async {
            appDatabase.getUserTeamProvider().getForUser(userId)?.apply {
                val teamMembersIds = appDatabase.getUserTeamProvider().getForTeam(teamId).map { it.userId }
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

                delay(1500)

                runBlocking {
                    runOnUiThread {
                        val teamStatsFragment = (dashboardTeamStatsFragment as TeamStatsFragment)
                        teamStatsFragment.registerDelegate(this@DashboardActivity)
                        teamStatsFragment.updateStats(teamTasks)
                    }
                }
            }
        }

        CoroutineScope(Dispatchers.IO).async {
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
                            userId,
                            userWorklogs.first.username,
                            userWorklogs.first.avatarUrl,
                            DayLog(
                                Calendar.getInstance()[Calendar.DAY_OF_WEEK],
                                userWorklogs.second.fold(0.0) { total, logged -> total.plus(logged.value) }
                            )
                        )
                    }

                runOnUiThread {
                    (dashboardTeamMembersFragment as TeamMembersFragment).setupData(items)
                }
            }
        }
    }

    override fun onWorklogSelected(worklog: DayLog) {
        Log.i(DashboardActivity::class.simpleName, "Worklog selected: $worklog")
    }

    override fun onNavigateToWorklogList() {
        val intent = Intent(this, PersonalStatusDetailsActivity::class.java)
        startActivity(intent)
    }

    override fun onCategorySelected(taskType: Type) {
        val intent = Intent(this, SearchTaskActivity::class.java)
        startActivity(intent)
    }

    override fun onTaskTrackingStarted(task: TeamTask) {
        Log.i(DashboardActivity::class.simpleName, "Started to track effort for task: $task")
    }

    override fun onTaskTrackingPaused(task: TeamTask) {
        Log.i(DashboardActivity::class.simpleName, "Paused tracking effort for task: $task")
    }

    override fun onTaskTrackingStopped(task: TeamTask, loggedTime: Long) {
        Log.i(DashboardActivity::class.simpleName, "Stopped tracking effort for task: $task")
    }

    override fun onNavigateToTaskDetails(task: TeamTask, isEditMode: Boolean) {
        Log.i(DashboardActivity::class.simpleName, "Navigate to task details requested for: $task")
    }

    override fun onNavigateToTaskList() {
        Log.i(DashboardActivity::class.simpleName, "Navigate to tasks list requested")
    }
}
