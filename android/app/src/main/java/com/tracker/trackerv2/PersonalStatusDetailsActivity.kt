/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.tracker.trackerv2

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lexu.models.Status
import com.lexu.tracking.StatsInfoFragment
import com.lexu.tracking.models.StatsInfoTaskData
import com.tracker.trackerv2.adapters.TaskListAdapter
import com.tracker.trackerv2.configs.SearchTaskConfigBundle
import com.tracker.trackerv2.datasource.providers.local.UserSessionProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import com.tracker.trackerv2.datasource.providers.local.room.entity.TaskEntity
import kotlinx.android.synthetic.main.activity_personal_status_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import java.util.Calendar

class PersonalStatusDetailsActivity : AppCompatActivity() {

    private var userId : String? = null

    private lateinit var usersProvider : UserSessionProvider
    private lateinit var appDatabase : AppDatabase

    private lateinit var statsInfoFragment: StatsInfoFragment

    private lateinit var adapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_status_details)

        usersProvider = UserSessionProvider(this)
        appDatabase = AppDatabase.getDatabase(this)

        statsInfoFragment = personalStatusStatsInfoFragment as StatsInfoFragment

        personalStatusDetailsBackButton.setOnClickListener { finish() }


        adapter = TaskListAdapter(this)
        personalStatusRecyclerView.layoutManager = LinearLayoutManager(this)
        personalStatusRecyclerView.adapter = adapter

        if(intent.hasExtra(KEY_USER_ID_EXTRA)) {
            userId = intent.extras!!.getString(KEY_USER_ID_EXTRA)
        }

        fetchUserID()
    }

    private fun fetchUserID() = CoroutineScope(Dispatchers.IO).async {
        val userId = this@PersonalStatusDetailsActivity.userId ?: usersProvider.getUserId()
        if(userId.isNullOrEmpty()) {
            Log.e(PersonalStatusDetailsActivity::class.simpleName, "UserId is null or empty; no session provided")
            return@async
        }

        fetchTasksForUser(userId)
        fetchOngoingTasksForUser(userId)
    }

    private suspend fun fetchTasksForUser(userId: String) {
        val tasksForUser = appDatabase.getTasksProvider()
            .getAllAssignedToUser(userId)
        val closedTasks: Int = tasksForUser.count { it.status.toUpperCase().contentEquals("CLOSED") }
        val estimates: Double = tasksForUser.sumByDouble { it.estimate }

        val worklogsForUser = appDatabase.getWorklogsProvider().getAllForUser(userId)

        val loggedTime: Double = tasksForUser.sumByDouble { it.logged }
        val dailyLoggedTimeOnTasks = worklogsForUser.filter { worklog ->
            worklog.relatesTo in tasksForUser.map { task -> task.taskId }
        }.groupBy {
            val cal = Calendar.getInstance()
            cal.time = it.createdDate
            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)
            cal.time
        }
        delay(1000)

        val taskData = StatsInfoTaskData(
            totalAssignedTasks = tasksForUser.size,
            totalClosedTasks = closedTasks,
            totalEstimates = estimates,
            totalLoggedTime = loggedTime,
            averageLoggedTimePerDay = dailyLoggedTimeOnTasks.map { dayToLogged ->
                dayToLogged.value.fold(0.0) { total, log -> total.plus(log.value) }
            }.average()
        )

        runOnUiThread {
            onDataProvided(taskData)
        }
    }

    private suspend fun fetchOngoingTasksForUser(userId: String) {
        val items = appDatabase.getTasksProvider()
            .getAllAssignedToUser(userId)
            .filter { Status.valueOf(it.status) == Status.IN_PROGRESS }

        runOnUiThread {
            onOngoingTasksProvided(userId, items)
        }
    }

    @MainThread
    private fun onDataProvided(taskData: StatsInfoTaskData) {
        statsInfoFragment.updateUI(taskData, true)
    }

    @MainThread
    private fun onOngoingTasksProvided(userId: String, tasks: List<TaskEntity>) {
        personalStatusLoadingView.visibility = View.GONE
        if(tasks.isEmpty()) {
            personalStatusErrorMessage.visibility = View.VISIBLE
            personalStatusRecyclerView.visibility = View.GONE
        } else {
            personalStatusErrorMessage.visibility = View.GONE
            personalStatusRecyclerView.visibility = View.VISIBLE
        }

        adapter.setItems(tasks)
        adapter.onSearchConfigChanged(SearchTaskConfigBundle(userId))
    }

    companion object {
        const val KEY_USER_ID_EXTRA = "user_id_extra"
    }
}
