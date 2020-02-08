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
import com.tracker.trackerv2.custom.BackButtonToolbar
import com.tracker.trackerv2.datasource.providers.local.ITasksProvider
import com.tracker.trackerv2.datasource.providers.local.IUsersProvider
import com.tracker.trackerv2.datasource.providers.local.IWorklogsProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import com.tracker.trackerv2.datasource.providers.local.room.entity.TaskEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.UserEntity
import com.tracker.trackerv2.utils.WorklogsFormatter
import kotlinx.android.synthetic.main.activity_task_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class TaskDetailsActivity : AppCompatActivity(), PersonalStatsContract.PersonalStatsDelegate {
    private lateinit var appDatabase : AppDatabase
    private lateinit var tasksProvider : ITasksProvider
    private lateinit var worklogsProvider : IWorklogsProvider
    private lateinit var usersProvider: IUsersProvider
    private val worklogsFormatter = WorklogsFormatter()

    private lateinit var chartFragment : OverallTaskStatsFragment

    private lateinit var taskId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)

        appDatabase = AppDatabase.getDatabase(this)
        tasksProvider = appDatabase.getTasksProvider()
        worklogsProvider = appDatabase.getWorklogsProvider()
        usersProvider = appDatabase.getUsersProvider()
        chartFragment = taskDetailsChartFragment as OverallTaskStatsFragment

        taskId = intent.getStringExtra(KEY_TASK_ID_EXTRA)
        fetchTaskDetails(taskId)
    }

    private fun fetchTaskDetails(taskId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val worklogs = worklogsProvider.getAllForTask(taskId)
                .groupBy { worklog -> worklog.createdDate }
                .toList()
                .sortedBy { worklogsByDate -> worklogsByDate.first }
                .mapIndexed { index, pair ->
                    DayLog(
                        index,
                        pair.second.fold(0.0) { total, worklog -> total.plus(worklog.value) })
                }
            delay(2000)

            tasksProvider.getDetails(taskId)?.apply {
                val assignee = assignedTo?.let { usersProvider.getDetails(it) }
                val creator = usersProvider.getDetails(createdBy) as UserEntity

                runOnUiThread {
                    setupDetails(this, worklogs, assignee, creator)
                }
            }
        }
    }

    override fun onWorklogSelected(worklog: DayLog) {
        Log.i(DashboardActivity::class.simpleName, "Worklog selected: $worklog")
    }

    override fun onNavigateToUserDetails() {
        val intent = Intent(this, PersonalStatusDetailsActivity::class.java)
        startActivity(intent)
    }

    @MainThread
    private fun setupDetails(task: TaskEntity, worklogs: List<DayLog>, assignee: UserEntity? = null, creator: UserEntity) {
        taskDetailsToolbar.setOnBackClickListener(object : BackButtonToolbar.OnBackClickListener {
            override fun onBackClicked() {
                finish()
            }
        })
        taskDetailsToolbar.setOnEditClickListener(object : BackButtonToolbar.OnEditClickListener {
            override fun onEditClicked() {
                val intent = Intent(this@TaskDetailsActivity, CreateTaskActivity::class.java)
                intent.putExtra(CreateTaskActivity.KEY_IS_UPDATE_EXTRA, true)
                intent.putExtra(CreateTaskActivity.KEY_TASK_ID_EXTRA, task.taskId)
                startActivityForResult(intent, REQUEST_CODE_UPDATE_TASK)
            }
        })
        taskDetailsToolbar.setTitle("[".plus(task.taskId).plus("] ".plus(task.title)))
        taskDetailsTypeLabel.text = task.type
        taskDetailsStatusLabel.text = task.status
        taskDetailsPriorityLabel.text = task.priority
        taskDetailsStoryPointsLabel.text = task.storyPoints?.toString() ?: "---"

        task.dueDate?.let {
            val formattedDueDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(it)
            taskDetailsDueDateLabel.text = formattedDueDate
        }
        taskDetailsEstimateLabel.text = worklogsFormatter.format(task.estimate)
        val totalLogged = worklogs
            .map { it.loggedTime }
            .fold(0.0) { total, log -> total.plus(log) }
        taskDetailsTotalEffortLabel.text = worklogsFormatter.format(totalLogged)

        assignee?.let {
            taskDetailsAsigneeUsernameLabel.text = it.username
        }
        taskDetailsTaskCreatedByLabel.text = creator.username
        taskDetailsTaskCreatedAtLabel.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(creator.createdAt)

        chartFragment.registerDelegate(this@TaskDetailsActivity)
        chartFragment.setTitle("Effort timeline")
        chartFragment.updateStats(worklogs)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            REQUEST_CODE_UPDATE_TASK -> fetchTaskDetails(taskId)
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        const val KEY_TASK_ID_EXTRA = "task_id"
        const val REQUEST_CODE_UPDATE_TASK = 1004
    }
}
