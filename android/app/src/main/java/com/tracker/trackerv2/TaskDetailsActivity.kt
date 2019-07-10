/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.tracker.trackerv2

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lexu.tracking.OverallTaskStatsFragment
import com.lexu.tracking.delegates.PersonalStatsContract
import com.lexu.tracking.utils.DayLog
import com.tracker.trackerv2.R.id.taskDetailsChartFragment
import kotlinx.android.synthetic.main.activity_task_details.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class TaskDetailsActivity : AppCompatActivity(), PersonalStatsContract.PersonalStatsDelegate {
    private val mockDataParser = MockDataParser(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)

        val chartFragment = OverallTaskStatsFragment()

        supportFragmentManager.beginTransaction()
            .replace(taskDetailsChartFragmentContainer.id, ongoingTaskFragment, null)
            .commit()

        GlobalScope.async {
            val tasks = mockDataParser.getTaskTotalEffortStats()

            runOnUiThread {
                (taskDetailsChartFragment as OverallTaskStatsFragment).registerDelegate(this@TaskDetailsActivity)
                (taskDetailsChartFragment as OverallTaskStatsFragment).updateStats(tasks)
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
}
