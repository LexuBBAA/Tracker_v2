package com.tracker.trackerv2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lexu.models.Priority
import com.lexu.tracking.TaskAllocationFragment
import com.lexu.tracking.models.TaskAllocationDataModel
import com.tracker.trackerv2.adapters.UsersListAdapter
import com.tracker.trackerv2.custom.BackButtonToolbar
import com.tracker.trackerv2.datasource.providers.local.UserSessionProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import com.tracker.trackerv2.datasource.providers.local.room.entity.TeamEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.UserEntity
import kotlinx.android.synthetic.main.activity_team_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamDetailsActivity : AppCompatActivity(), UsersListAdapter.OnItemClickListener {

    private lateinit var sessionProvider: UserSessionProvider
    private lateinit var appDatabase: AppDatabase

    private lateinit var userId : String
    private lateinit var team : TeamEntity
    private lateinit var updatedTeam : TeamEntity

    private var isEditMode = false

    private lateinit var taskAllocationFragment : TaskAllocationFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)

        sessionProvider = UserSessionProvider(this)
        appDatabase = AppDatabase.getDatabase(this)

        teamDetailsToolbar.setOnBackClickListener(object: BackButtonToolbar.OnBackClickListener {
            override fun onBackClicked() {
                finish()
            }
        })
        teamDetailsToolbar.setOnEditClickListener(object: BackButtonToolbar.OnEditClickListener {
            override fun onEditClicked() {
                isEditMode = !isEditMode
                teamDetailsToolbar.setEditMode(isEditMode)
                toggleEditMode()
            }
        })
        teamDetailsSubmitButton.setOnClickListener {
            setResult(Activity.RESULT_OK)
            updateTeam()
        }

        taskAllocationFragment = taskDetailsAllocationFragment as TaskAllocationFragment
        teamDetailsUsersList.layoutManager = LinearLayoutManager(this)
        teamDetailsUsersList.adapter = UsersListAdapter(this)

        fetchTeamData()
    }

    override fun onItemClicked(item: UserEntity) {
        val intent = Intent(this, PersonalStatusDetailsActivity::class.java)
        intent.putExtra(PersonalStatusDetailsActivity.KEY_USER_ID_EXTRA, item.userId)
        startActivity(intent)
    }

    private fun fetchTeamData() {
        val teamId = intent.getStringExtra(KEY_TEAM_ID_EXTRA)
        if(teamId == null) {
            Log.e(TeamDetailsActivity::class.simpleName, "> Team ID is null!")
            finish()
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            userId = sessionProvider.getUserId() ?: ""
            team = appDatabase.getTeamsProvider()
                .getById(teamId) as TeamEntity
            updatedTeam = team.copy()

            runOnUiThread { onTeamDataProvided() }

            fetchTeamMembersData()
        }
    }

    private suspend fun fetchTeamMembersData() {
        val userTeams = appDatabase.getUserTeamProvider()
            .getForTeam(team.teamId as String)

        val users = appDatabase.getUsersProvider()
            .getAll()
            .filter { user -> user.userId in userTeams.map { userTeam -> userTeam.userId } }
            .sortedBy { it.username }

        runOnUiThread { onTeamMembersDataProvided(users) }

        val allocationModels = users.map { user ->
            val userTasks = appDatabase.getTasksProvider()
                .getAllAssignedToUser(user.userId as String)
                .map { task ->
                    TaskAllocationDataModel.AllocatedTask(
                        task.taskId!!, priority = when (task.priority) {
                            Priority.BLOCKER.name -> 0
                            Priority.CRITICAL.name -> 1
                            Priority.HIGH.name -> 2
                            Priority.MEDIUM.name -> 3
                            else -> 4
                        }
                    )
                }
                .toList()

            TaskAllocationDataModel(user.userId!!, user.username, user.avatarUrl ?: "", userTasks)
        }

        runOnUiThread { onAllocationDataProvided(allocationModels) }
    }

    @MainThread
    private fun onTeamDataProvided() {
        isEditMode = false
        toggleEditMode()

        teamDetailsTitle.text = team.name
        teamDetailsDescription.text = team.description ?: "---"
        teamDetailsTitleInputField.setText(team.name)
        teamDetailsDescriptionInputField.setText(team.description ?: "")

        teamDetailsToolbar.showEditButton(userId == team.createdBy)
    }

    @MainThread
    private fun onTeamMembersDataProvided(users: List<UserEntity>) {
        (teamDetailsUsersList.adapter as UsersListAdapter).setItems(users)
    }

    @MainThread
    private fun onAllocationDataProvided(taskAllocationModels: List<TaskAllocationDataModel>) {
        taskAllocationFragment.loadData(taskAllocationModels)
    }

    private fun toggleEditMode() {
        if(isEditMode) {
            teamDetailsContainer.visibility = View.GONE
            teamDetailsEditContainer.visibility = View.VISIBLE
        } else {
            teamDetailsContainer.visibility = View.VISIBLE
            teamDetailsEditContainer.visibility = View.GONE
        }
    }

    private fun updateTeam() {
        updatedTeam.name = teamDetailsTitleInputField.text?.toString() ?: ""
        updatedTeam.description = teamDetailsDescriptionInputField.text?.toString() ?: ""

        CoroutineScope(Dispatchers.IO).launch {
            appDatabase.getTeamsProvider().update(updatedTeam)
            team = updatedTeam

            runOnUiThread { onTeamDataProvided() }
        }
    }

    companion object {
        const val KEY_TEAM_ID_EXTRA = "team_id_extra"
    }
}
