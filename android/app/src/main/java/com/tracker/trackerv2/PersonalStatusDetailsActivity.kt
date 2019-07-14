/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.tracker.trackerv2

import android.os.Bundle
import android.util.Log
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import com.lexu.tracking.StatsInfoFragment
import com.lexu.tracking.models.StatsInfoTaskData
import com.tracker.trackerv2.R
import com.tracker.trackerv2.datasource.providers.local.UserSessionProvider
import com.tracker.trackerv2.datasource.providers.local.UsersProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import kotlinx.android.synthetic.main.activity_personal_status_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlin.coroutines.CoroutineContext

class PersonalStatusDetailsActivity : AppCompatActivity() {

    private lateinit var usersProvider : UserSessionProvider
    private lateinit var appDatabase : AppDatabase

    private lateinit var statsInfoFragment: StatsInfoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_status_details)

        usersProvider = UserSessionProvider(this)
        appDatabase = AppDatabase.getDatabase(this)

        statsInfoFragment = personalStatusStatsInfoFragment as StatsInfoFragment

        fetchUserID()
    }

    private fun fetchUserID() = CoroutineScope(Dispatchers.IO).async {
        val userId = usersProvider.getUserId()
        if(userId.isNullOrEmpty()) {
            Log.e(PersonalStatusDetailsActivity::class.simpleName, "UserId is null or empty; no session provided")
            return@async
        }

        fetchTasksForUser(userId)
    }

    private fun fetchTasksForUser(userId: String) = CoroutineScope(Dispatchers.IO).async {
        val tasksForUser = appDatabase.getTasksProvider()
            .getAllAssignedToUser(userId)
        val closedTasks: Int = tasksForUser.count { it.status.toUpperCase().contentEquals("CLOSED") }
        val estimates: Double = tasksForUser.sumByDouble { it.estimate }

        val worklogsForUser = appDatabase.getWorklogsProvider().getAllForUser(userId)

        val loggedTime: Double = tasksForUser.sumByDouble { it.logged }
        val dailyLoggedTimeOnTasks = worklogsForUser.filter { worklog ->
            worklog.relatesTo in tasksForUser.map { task -> task.taskId }
        }.groupBy { it.createdDate }
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

        onDataProvided(taskData)
    }

    @MainThread
    private fun onDataProvided(taskData: StatsInfoTaskData) {
        statsInfoFragment.updateUI(taskData, true)
    }
}
