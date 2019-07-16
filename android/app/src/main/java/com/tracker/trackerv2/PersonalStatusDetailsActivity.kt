/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.tracker.trackerv2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lexu.models.Status
import com.lexu.tracking.StatsInfoFragment
import com.lexu.tracking.models.StatsInfoTaskData
import com.tracker.trackerv2.adapters.TaskListAdapter
import com.tracker.trackerv2.configs.Config
import com.tracker.trackerv2.configs.SearchTaskConfigBundle
import com.tracker.trackerv2.custom.BackButtonToolbar
import com.tracker.trackerv2.datasource.providers.local.UserSessionProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import com.tracker.trackerv2.datasource.providers.local.room.entity.TaskEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.TeamEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.UserEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.utils.UserTeamEntity
import com.tracker.trackerv2.utils.SimpleSpinnerListener
import kotlinx.android.synthetic.main.activity_personal_status_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PersonalStatusDetailsActivity : AppCompatActivity(), TaskListAdapter.OnItemClickListener {

    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    private lateinit var currentUserId : String
    private var userId : String? = null
    private lateinit var user : UserEntity
    private lateinit var teams : List<TeamEntity>
    private var userTeam : TeamEntity? = null

    private lateinit var usersProvider : UserSessionProvider
    private lateinit var appDatabase : AppDatabase

    private lateinit var statsInfoFragment: StatsInfoFragment

    private lateinit var adapter: TaskListAdapter
    private lateinit var teamsSpinnerAdapter: ArrayAdapter<String>

    private val updatedUser = UserEntity(username = "", email = "", phone = "", password = "")
    private val updatedUserTeam = UserTeamEntity(userId = "", teamId = "")

    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_status_details)

        usersProvider = UserSessionProvider(this)
        appDatabase = AppDatabase.getDatabase(this)

        statsInfoFragment = personalStatusStatsInfoFragment as StatsInfoFragment

        personalStatusDetailsToolbar.setOnBackClickListener(object: BackButtonToolbar.OnBackClickListener {
            override fun onBackClicked() {
                finish()
            }
        })

        personalStatusDetailsToolbar.setOnEditClickListener(object: BackButtonToolbar.OnEditClickListener {
            override fun onEditClicked() {
                isEditMode = !isEditMode
                personalStatusDetailsToolbar.setEditMode(isEditMode)
                toggleEditMode()

                currentFocus?.clearFocus()
            }
        })

        teamsSpinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item)
        teamsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        personalStatsTeamSpinner.adapter = teamsSpinnerAdapter
        personalStatsTeamSpinner.onItemSelectedListener = object: SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val team = teams.first { "[".plus(it.teamId).plus("] ").plus(it.name).contentEquals(personalStatsTeamSpinner.selectedItem as String) }
                updatedUserTeam.userId = user.userId as String
                updatedUserTeam.teamId = team.teamId as String
            }
        }

        personalStatsUpdateButton.setOnClickListener { updateUser() }

        adapter = TaskListAdapter(this)
        personalStatusRecyclerView.layoutManager = LinearLayoutManager(this)
        personalStatusRecyclerView.adapter = adapter

        personalStatsTeam.setOnClickListener { navigateToTeamDetails() }
        seeAllTasksView.setOnClickListener { navigateToTasksList() }

        if(intent.hasExtra(KEY_USER_ID_EXTRA)) {
            userId = intent.extras!!.getString(KEY_USER_ID_EXTRA)
        }

        fetchUserID()
    }

    private fun fetchUserID() = CoroutineScope(Dispatchers.IO).async {
        currentUserId = usersProvider.getUserId() ?: ""
        val userId = this@PersonalStatusDetailsActivity.userId ?: currentUserId
        if(userId.isEmpty()) {
            Log.e(PersonalStatusDetailsActivity::class.simpleName, "UserId is null or empty; no session provided")
            return@async
        }

        fetchUserDetails(userId)
        fetchTasksForUser(userId)
        fetchOngoingTasksForUser(userId)
    }

    private suspend fun fetchUserDetails(userId: String) {
        user = appDatabase.getUsersProvider()
            .getDetails(userId) as UserEntity

        updatedUser.username = user.username
        updatedUser.firstname = user.firstname
        updatedUser.lastname = user.lastname
        updatedUser.createdAt = user.createdAt
        updatedUser.createdBy = user.createdBy
        updatedUser.userId = user.userId
        updatedUser.password = user.password
        updatedUser.avatarUrl = user.avatarUrl
        updatedUser.email = user.email
        updatedUser.phone = user.phone
        updatedUser.id = user.id

        val userTeam = appDatabase.getUserTeamProvider()
            .getForUser(userId)

        teams = appDatabase.getTeamsProvider()
            .getAll()
        userTeam?.let {
            this@PersonalStatusDetailsActivity.userTeam = teams.first { team -> team.teamId == userTeam.teamId }
            this@PersonalStatusDetailsActivity.updatedUserTeam.teamId = this@PersonalStatusDetailsActivity.userTeam!!.teamId as String

            updatedUserTeam.id = it.id
            updatedUserTeam.teamId = it.teamId
            updatedUserTeam.userId = it.userId
        }

        runOnUiThread { onUserDetailsFetched() }
    }

    private suspend fun fetchTasksForUser(userId: String) {
        val tasksForUser = appDatabase.getTasksProvider()
            .getAllAssignedToUser(userId)
        val closedTasks: Int = tasksForUser.count { it.status.toUpperCase().contentEquals("CLOSED") }
        val estimates: Double = tasksForUser.fold(0.0) { total, time -> total.plus(time.estimate) }

        val worklogsForUser = appDatabase.getWorklogsProvider().getAllForUser(userId)

        val loggedTime: Double = worklogsForUser.fold(0.0) { total, time -> total.plus(time.value) }
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
            .filter { Status.valueOf(it.status) in arrayOf(Status.OPEN, Status.IN_PROGRESS, Status.ON_HOLD, Status.REOPENED) }
            .take(5)

        runOnUiThread {
            onOngoingTasksProvided(userId, items)
        }
    }

    @MainThread
    private fun onUserDetailsFetched() {
        isEditMode = false
        personalStatusDetailsToolbar.showEditButton(user.userId == currentUserId)
        personalStatusDetailsToolbar.setEditMode(isEditMode)
        personalStatsDetailsContainer.visibility = View.VISIBLE
        personalStatsDetailsEditContainer.visibility = View.GONE

        val fullname = when {
            user.firstname != null && user.lastname != null -> user.firstname.plus(" ").plus(user.lastname)
            user.firstname != null -> user.firstname
            user.lastname != null -> user.lastname
            else -> "---"
        }
        personalStatsFullname.text = fullname
        personalStatsFirstnameInputField.setText(updatedUser.firstname ?: "")
        personalStatsLastnameInputField.setText(updatedUser.lastname ?: "")
        personalStatsPhoneInputField.setText(updatedUser.phone)
        teamsSpinnerAdapter.clear()
        teamsSpinnerAdapter.addAll(teams.map { "[".plus(it.teamId).plus("] ").plus(it.name) })
        teamsSpinnerAdapter.notifyDataSetChanged()

        val teamIndex = teamsSpinnerAdapter.getPosition("[".plus(userTeam?.teamId).plus("] ").plus(userTeam?.name))
        personalStatsTeamSpinner.setSelection(if(teamIndex >= 0) teamIndex else 0)

        personalStatsUsername.text = "@".plus(user.username)
        personalStatsCreatedDate.text = dateFormatter.format(user.createdAt)

        personalStatsEmail.text = user.email
        personalStatsPhone.text = user.phone

        personalStatsTeam.text = userTeam?.name ?: "---"
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

    private fun toggleEditMode() {
        if(isEditMode) {
            personalStatsDetailsContainer.visibility = View.GONE
            personalStatsDetailsEditContainer.visibility = View.VISIBLE
        } else {
            personalStatsDetailsContainer.visibility = View.VISIBLE
            personalStatsDetailsEditContainer.visibility = View.GONE
        }
    }

    private fun updateUser() {
        updatedUser.firstname = personalStatsFirstnameInputField.text?.toString()
        updatedUser.lastname = personalStatsLastnameInputField.text?.toString()
        updatedUser.phone = personalStatsPhoneInputField.text?.toString()

        CoroutineScope(Dispatchers.IO).launch {
            appDatabase.getUsersProvider()
                .update(updatedUser)

            user = appDatabase.getUsersProvider()
                .getDetails(user.userId as String) as UserEntity

            userTeam?.let {
                val storedUserTeam = appDatabase.getUserTeamProvider()
                    .getForUser(user.userId as String) as UserTeamEntity

                appDatabase.getUserTeamProvider()
                    .delete(storedUserTeam)
            }

            val newTeam = appDatabase.getUserTeamProvider()
                .create(updatedUserTeam)

            userTeam = appDatabase.getTeamsProvider()
                .getById(newTeam!!.teamId)

            if(userTeam == null) Log.e(PersonalStatusDetailsActivity::class.simpleName, "> User Team is NULL!")

            runOnUiThread {
                setResult(Activity.RESULT_OK)
                onUserDetailsFetched()
            }
        }
    }

    override fun onItemClicked(item: TaskEntity, position: Int) {
        navigateToTaskDetails(item)
    }

    override fun onItemDetailsClicked(item: TaskEntity, position: Int) {
        navigateToTaskDetails(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            REQUEST_CODE_TEAM_DETAILS -> if (resultCode == Activity.RESULT_OK) {
                setResult(resultCode)
                CoroutineScope(Dispatchers.IO).launch {
                    fetchUserDetails(userId ?: currentUserId)
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun navigateToTaskDetails(task: TaskEntity) {
        val intent = Intent(this, TaskDetailsActivity::class.java)
        intent.putExtra(TaskDetailsActivity.KEY_TASK_ID_EXTRA, task.taskId)
        startActivity(intent)
    }

    private fun navigateToTeamDetails() {
        userTeam?.let {
            val intent = Intent(this, TeamDetailsActivity::class.java)
            intent.putExtra(TeamDetailsActivity.KEY_TEAM_ID_EXTRA, it.teamId)
            startActivityForResult(intent, REQUEST_CODE_TEAM_DETAILS)
        }
    }

    private fun navigateToTasksList() {
        val searchConfig = SearchTaskConfigBundle(userId as String, assigneeFilter = Config.AssigneeFilter.FILTER_PERSONAL)

        val intent = Intent(this, SearchTaskActivity::class.java)
        intent.putExtra(SearchTaskActivity.KEY_SEARCH_CONFIG_EXTRA, searchConfig)
        startActivity(intent)
    }

    companion object {
        const val KEY_USER_ID_EXTRA = "user_id_extra"
        const val REQUEST_CODE_TEAM_DETAILS = 1001
    }
}
