/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.tracker.trackerv2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lexu.models.Type
import com.lexu.tracking.OngoingTaskFragment
import com.lexu.tracking.PersonalStatsFragment
import com.lexu.tracking.TeamStatsFragment
import com.lexu.tracking.delegates.OngoingTaskContract
import com.lexu.tracking.delegates.PersonalStatsContract
import com.lexu.tracking.delegates.TeamStatsContract
import com.lexu.tracking.utils.DayLog
import com.lexu.tracking.utils.TeamTask
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class DashboardActivity : AppCompatActivity(), OngoingTaskContract.OngoingTaskDelegate,
    TeamStatsContract.TeamStatsDelegate, PersonalStatsContract.PersonalStatsDelegate {

    private val mockDataParser = MockDataParser(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val ongoingTaskFragment = OngoingTaskFragment(this)

        supportFragmentManager.beginTransaction()
            .replace(ongoingTaskFragmentContainer.id, ongoingTaskFragment, null)
            .commit()

        CoroutineScope(Dispatchers.IO).async {
            val stats = mockDataParser.getPersonalWeekStats()
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
            val stats = mockDataParser.getTeamWeekStats()
            delay(2000)

            runBlocking {
                runOnUiThread {
                    ongoingTaskFragmentContainer.visibility = View.VISIBLE
                    ongoingTaskFragment.setTask(stats[0])

                    val teamStatsFragment = (dashboardTeamStatsFragment as TeamStatsFragment)
                    teamStatsFragment.registerDelegate(this@DashboardActivity)
                    teamStatsFragment.updateStats(stats)
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
        val intent = Intent(this, TaskListActivity::class.java)
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
