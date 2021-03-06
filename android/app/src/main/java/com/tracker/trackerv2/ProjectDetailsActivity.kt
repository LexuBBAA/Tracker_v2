package com.tracker.trackerv2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.recyclerview.widget.LinearLayoutManager
import com.tracker.trackerv2.adapters.SprintsListAdapter
import com.tracker.trackerv2.custom.BackButtonToolbar
import com.tracker.trackerv2.datasource.providers.local.UserSessionProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import com.tracker.trackerv2.datasource.providers.local.room.entity.ProjectEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.SprintEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.TeamEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.UserEntity
import kotlinx.android.synthetic.main.activity_project_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProjectDetailsActivity : AppCompatActivity(), SprintsListAdapter.OnItemClickListener {
    private lateinit var sessionProvider: UserSessionProvider
    private lateinit var appDatabase: AppDatabase

    private lateinit var userId: String
    private lateinit var projectId: String
    private lateinit var projectEntity: ProjectEntity
    private lateinit var projectCreator: UserEntity
    private lateinit var teams: List<TeamEntity>

    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_details)

        projectId = intent?.extras?.getString(KEY_PROJECT_ID_EXTRA) ?: ""
        if(projectId.isEmpty()) {
            Log.e(ProjectDetailsActivity::class.simpleName, "> No ProjectID provided; closing activity")
            finish()
            return
        }

        projectDetailsBackButtonToolbar.setOnBackClickListener(object: BackButtonToolbar.OnBackClickListener {
            override fun onBackClicked() {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        })
        projectDetailsBackButtonToolbar.setOnAddClickListener(object: BackButtonToolbar.OnAddButtonClickListener {
            override fun onAddClicked() {
                val intent = Intent(this@ProjectDetailsActivity, CreateSprintActivity::class.java)
                intent.putExtra(KEY_PROJECT_ID_EXTRA, projectId)
                startActivityForResult(intent, REQUEST_CODE_CREATE_SPRINT)
            }
        })
        projectDetailsBackButtonToolbar.setOnEditClickListener(object: BackButtonToolbar.OnEditClickListener {
            override fun onEditClicked() {
                isEditMode = !isEditMode
                toggleEditMode()
            }
        })

        sessionProvider = UserSessionProvider(this)
        appDatabase = AppDatabase.getDatabase(this)

        val adapter = SprintsListAdapter(this)
        adapter.setOnItemClickListener(this)
        projectDetailsRecyclerView.layoutManager = LinearLayoutManager(this)
        projectDetailsRecyclerView.adapter = adapter

        projectDetailsSubmitButton.setOnClickListener { updateProject() }

        fetchData()
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            userId = sessionProvider.getUserId() ?: ""

            projectEntity = appDatabase.getProjectsProvider().getAll()
                .first { it.projectId == projectId }

            projectCreator = appDatabase.getUsersProvider().getDetails(projectEntity.createdBy) as UserEntity

            teams = appDatabase.getTeamsProvider().getAll()

            runOnUiThread { onProjectProvided() }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            REQUEST_CODE_CREATE_SPRINT -> { fetchSprintsForProject() }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    @MainThread
    private fun onProjectProvided() {
        isEditMode = false
        toggleEditMode()

        //  remove loading
        projectDetailsLoadingView.visibility = View.GONE

        //  toggle edit button visibility
        projectDetailsBackButtonToolbar.showEditButton(projectEntity.createdBy == userId)

        //  populate data
        projectDetailsBackButtonToolbar.setTitle("[${projectEntity.projectId}] ${projectEntity.title}")
        projectDetailsTitleInputField.setText(projectEntity.title)
        projectDetailsDescriptionInputField.setText(projectEntity.description ?: "---")

        projectDetailsDescriptionInputField.isEnabled = isEditMode

        projectDetailsAssignedTeamNameView.text = teams.first { it.teamId == projectEntity.assignedTeam }.name
        projectDetailsCreatedByView.text = "@".plus(projectCreator.username)

        fetchSprintsForProject()
    }

    @MainThread
    private fun onSprintsProvided(sprints: List<SprintEntity>) {
        projectDetailsSprintsListLoadingView.visibility = View.GONE
        (projectDetailsRecyclerView.adapter as SprintsListAdapter).setItems(sprints)
    }

    private fun fetchSprintsForProject() {
        CoroutineScope(Dispatchers.IO).launch {
            val sprints = appDatabase.getSprintsProvider()
                .getAllForProject(projectEntity.projectId as String)
                .sortedBy { it.createdDate }

            runOnUiThread {
                onSprintsProvided(sprints)
            }
        }
    }

    private fun toggleEditMode() {
        if(isEditMode) setEditMode()
        else setDisplayMode()
        projectDetailsBackButtonToolbar.setEditMode(isEditMode)
    }

    private fun setEditMode() {
        projectDetailsTitleContainer.visibility = View.VISIBLE

        projectDetailsDescriptionInputField.isEnabled = true
        projectDetailsDescriptionInputField.isClickable = true
        projectDetailsDescriptionInputField.isFocusable = true

        projectDetailsSubmitButton.visibility = View.VISIBLE
    }

    private fun setDisplayMode() {
        projectDetailsTitleContainer.visibility = View.GONE

        projectDetailsDescriptionInputField.isEnabled = false
        projectDetailsDescriptionInputField.isClickable = false
        projectDetailsDescriptionInputField.isFocusable = false

        projectDetailsSubmitButton.visibility = View.GONE
    }

    private fun updateProject() {
        projectEntity.title = projectDetailsTitleInputField.text?.toString() ?: ""
        projectEntity.description = projectDetailsDescriptionInputField.text?.toString() ?: ""

        CoroutineScope(Dispatchers.IO).launch {
            appDatabase.getProjectsProvider()
                .update(projectEntity)

            runOnUiThread { onProjectProvided() }
        }
    }

    override fun onItemClicked(item: SprintEntity) {
        val intent = Intent(this, SprintDetailsActivity::class.java)
        intent.putExtra(SprintDetailsActivity.KEY_SPRINT_ID_EXTRA, item.sprintId)
        startActivity(intent)
    }

    companion object {
        const val KEY_PROJECT_ID_EXTRA = "project_id"
        const val REQUEST_CODE_CREATE_SPRINT = 2000
    }
}
