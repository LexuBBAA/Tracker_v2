package com.tracker.trackerv2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lexu.models.SprintStatus
import com.tracker.trackerv2.custom.BackButtonToolbar
import com.tracker.trackerv2.datasource.providers.local.UserSessionProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import com.tracker.trackerv2.datasource.providers.local.room.entity.SprintEntity
import kotlinx.android.synthetic.main.activity_create_sprint.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

class CreateSprintActivity : AppCompatActivity() {
    private lateinit var sessionProvider: UserSessionProvider
    private lateinit var appDatabase: AppDatabase

    private lateinit var projectId : String
    private lateinit var userId: String

    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    private val newSprint = SprintEntity(createdBy = "", project = "", status = "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_sprint)

        sessionProvider = UserSessionProvider(this)
        appDatabase = AppDatabase.getDatabase(this)

        createSprintToolbar.setOnBackClickListener(object: BackButtonToolbar.OnBackClickListener {
            override fun onBackClicked() {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        })

        createSprintSubmitButton.setOnClickListener { onSubmitClicked() }

        projectId = intent.getStringExtra(ProjectDetailsActivity.KEY_PROJECT_ID_EXTRA)

        fetchSprintDetails()
    }

    private fun fetchSprintDetails() {
        CoroutineScope(Dispatchers.IO).launch {
            userId = sessionProvider.getUserId() ?: ""

            newSprint.createdBy = userId
            newSprint.project = projectId
            newSprint.status = SprintStatus.NOT_STARTED.name
        }
    }

    private fun onSubmitClicked() {
        newSprint.title = createSprintTitleInput.text?.toString() ?: ""
        newSprint.description = createSprintDescriptionInput.text?.toString() ?: ""

        val startDateStr = createSprintStartDateInput.text?.toString()
        val endDateStr = createSprintEndDateInput.text?.toString()

        if(!startDateStr.isNullOrEmpty()) {
            val date = dateFormatter.parse(startDateStr)
            newSprint.startDate = Date(date.time)
        }

        if(!endDateStr.isNullOrEmpty()) {
            val date = dateFormatter.parse(endDateStr)
            newSprint.endDate = Date(date.time)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val sprintId = "s-".plus((appDatabase.getSprintsProvider().getAllForProject(projectId).count() + 1).toString().padStart(6, '0'))
            newSprint.sprintId = sprintId

            appDatabase.getSprintsProvider().create(newSprint)

            runOnUiThread {
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }
}
