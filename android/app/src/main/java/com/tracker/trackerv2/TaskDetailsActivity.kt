/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.tracker.trackerv2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import com.lexu.tracking.OverallTaskStatsFragment
import com.lexu.tracking.delegates.PersonalStatsContract
import com.lexu.tracking.utils.DayLog
import com.tracker.trackerv2.datasource.providers.local.ITasksProvider
import com.tracker.trackerv2.datasource.providers.local.IWorklogsProvider
import com.tracker.trackerv2.datasource.providers.local.TasksProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import com.tracker.trackerv2.datasource.providers.local.room.entity.TaskEntity
import kotlinx.android.synthetic.main.activity_task_details.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class TaskDetailsActivity : AppCompatActivity(), PersonalStatsContract.PersonalStatsDelegate {
    private lateinit var appDatabase : AppDatabase
    private lateinit var tasksProvider : ITasksProvider
    private lateinit var worklogsProvider : IWorklogsProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)

        appDatabase = AppDatabase.getDatabase(this)
        tasksProvider = appDatabase.getTasksProvider()
        worklogsProvider = appDatabase.getWorklogsProvider()

        val chartFragment = OverallTaskStatsFragment(this)

        supportFragmentManager.beginTransaction()
            .replace(taskDetailsChartFragmentContainer.id, chartFragment, null)
            .commit()

        GlobalScope.async {
            val taskId = intent.getStringExtra(KEY_TASK_ID_EXTRA)
            tasksProvider.getDetails(taskId)?.apply { setupDetails(this) }

            val tasks = worklogsProvider.getAllForTask(taskId)
                .groupBy { worklog -> worklog.createdDate }
                .toList()
                .sortedByDescending { worklogsByDate -> worklogsByDate.first }
                .mapIndexed { index, pair ->
                    DayLog(index, pair.second.sumByDouble { worklog -> worklog.value })
                }

            runOnUiThread {
                chartFragment.registerDelegate(this@TaskDetailsActivity)
                chartFragment.updateStats(tasks)
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

    @MainThread
    private fun setupDetails(task: TaskEntity) {

    }

    companion object {
        const val KEY_TASK_ID_EXTRA = "task_id"
    }
}
