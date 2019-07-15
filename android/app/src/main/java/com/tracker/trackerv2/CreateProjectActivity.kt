package com.tracker.trackerv2

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import com.lexu.models.Status
import com.tracker.trackerv2.custom.BackButtonToolbar
import com.tracker.trackerv2.datasource.providers.local.UserSessionProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import com.tracker.trackerv2.datasource.providers.local.room.entity.ProjectEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.SprintEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.TeamEntity
import com.tracker.trackerv2.utils.SimpleSpinnerListener
import com.tracker.trackerv2.utils.SimpleTextWatcher
import kotlinx.android.synthetic.main.activity_create_project.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateProjectActivity : AppCompatActivity() {

    private lateinit var sessionProvider: UserSessionProvider
    private lateinit var appDatabase: AppDatabase

    private val newProjectEntity = ProjectEntity(title = "", createdBy = "")
    private var teamsList: List<TeamEntity> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_project)

        createProjectLoadingView.visibility = View.VISIBLE

        sessionProvider = UserSessionProvider(this)
        appDatabase = AppDatabase.getDatabase(this)

        createProjectBackButtonToolbar.setOnBackClickListener(object: BackButtonToolbar.OnBackClickListener {
            override fun onBackClicked() {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        })

        CoroutineScope(Dispatchers.IO).launch {
            val userId = sessionProvider.getUserId() ?: ""
            newProjectEntity.createdBy = userId

            teamsList = appDatabase.getTeamsProvider()
                .getAll()

            runOnUiThread {
                setupTeamList(teamsList)
            }
        }
    }

    @MainThread
    private fun setupTeamList(teams: List<TeamEntity>) {
        createProjectLoadingView.visibility = View.GONE

        val titleTextWatcher = object : SimpleTextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s?.toString().isNullOrEmpty()) showError(createProjectTitleInputField)
                else if(createProjectErrorMessage.visibility == View.VISIBLE) removeError(createProjectTitleInputField)

                newProjectEntity.title = s?.toString() ?: ""
            }
        }
        createProjectTitleInputField.addTextChangedListener(titleTextWatcher)

        val descriptionTextWatcher = object: SimpleTextWatcher {
            override fun afterTextChanged(s: Editable?) {
                newProjectEntity.description = s?.toString()
            }
        }
        createProjectDescriptionInputField.addTextChangedListener(descriptionTextWatcher)

        val spinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teams.map { it.name })
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        createProjectTeamSpinner.adapter = spinnerAdapter

        createProjectTeamSpinner.onItemSelectedListener = object : SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                newProjectEntity.assignedTeam = teams.find { it.name == createProjectTeamSpinner.selectedItem }!!.teamId
            }
        }

        createProjectSubmitButton.setOnClickListener {
            createProjectLoadingView.visibility = View.VISIBLE
            CoroutineScope(Dispatchers.IO).launch {
                val newProjectId = "pj-${(appDatabase.getProjectsProvider().getAll().count() + 1).toString().padStart(5, '0').toUpperCase()}"
                newProjectEntity.projectId = newProjectId

                val storedProject = appDatabase.getProjectsProvider().create(newProjectEntity)

                val newSprintId = "s-${(appDatabase.getSprintsProvider().getAllForProject(newProjectId).count() + 1).toString().padStart(6, '0').toUpperCase()}/$newProjectId"
                val projectBacklog = SprintEntity(
                    sprintId = newSprintId,
                    title = "Backlog",
                    description = "Auto-generated sprint for project: [$newProjectId] ${newProjectEntity.title}",
                    createdBy = newProjectEntity.createdBy,
                    project = newProjectId,
                    status = Status.OPEN.name
                )

                appDatabase.getSprintsProvider().create(projectBacklog)

                runOnUiThread {
                    storedProject?.let { onProjectCreated(it) }
                }
            }
        }
    }

    @MainThread
    private fun onProjectCreated(project: ProjectEntity) {
        val intent = Intent(this, ProjectDetailsActivity::class.java)
        intent.putExtra(ProjectDetailsActivity.KEY_PROJECT_ID_EXTRA, project.projectId)
        startActivity(intent)

        setResult(Activity.RESULT_OK)
        finish()
    }

    private fun showError(view: EditText) {
        view.setTextColor(Color.RED)
        createProjectErrorMessage.visibility = View.VISIBLE
    }

    private fun removeError(view: EditText) {
        view.setTextColor(Color.WHITE)
    }
}
