package com.tracker.trackerv2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import com.lexu.models.Priority
import com.lexu.models.Status
import com.lexu.models.Type
import com.tracker.trackerv2.datasource.providers.local.UserSessionProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import com.tracker.trackerv2.datasource.providers.local.room.entity.ProjectEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.SprintEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.TaskEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.UserEntity
import com.tracker.trackerv2.utils.SimpleSpinnerListener
import kotlinx.android.synthetic.main.activity_create_task.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CreateTaskActivity : AppCompatActivity() {
    private lateinit var sessionProvider: UserSessionProvider
    private lateinit var appDatabase: AppDatabase

    private val newTaskEntity = TaskEntity(
        createdBy = "",
        title = "",
        sprintId = "",
        priority = "",
        status = "",
        type = "",
        project = ""
    )

    private lateinit var userId: String
    private lateinit var projects : List<ProjectEntity>

    private lateinit var typeAdapter: ArrayAdapter<String>
    private lateinit var statusAdapter: ArrayAdapter<String>
    private lateinit var priorityAdapter: ArrayAdapter<String>
    private lateinit var projectAdapter: ArrayAdapter<String>
    private lateinit var sprintAdapter: ArrayAdapter<String>
    private lateinit var assigneeAdapter: ArrayAdapter<String>

    private lateinit var partOfAdapter: ArrayAdapter<String>
    private lateinit var blocksAdapter: ArrayAdapter<String>
    private lateinit var linkedToAdapter: ArrayAdapter<String>
    private lateinit var subtaskAdapter: ArrayAdapter<String>
    private lateinit var parentAdapter: ArrayAdapter<String>
    private lateinit var epicAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        sessionProvider = UserSessionProvider(this)
        appDatabase = AppDatabase.getDatabase(this)

        createTaskLoadingView.visibility = View.VISIBLE

        typeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        statusAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        priorityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        projectAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        sprintAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        assigneeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        partOfAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        blocksAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        linkedToAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        subtaskAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        parentAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        epicAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)

        typeAdapter.addAll(Type.values().map { it.name })
        statusAdapter.addAll(Status.values().map { it.name })
        priorityAdapter.addAll(Priority.values().map { it.name })

        setupUi()

        CoroutineScope(Dispatchers.IO).launch {
            userId = sessionProvider.getUserId() ?: ""

            projects = appDatabase.getProjectsProvider()
                .getAll()

            runOnUiThread { onProjectsProvided() }
        }
    }

    private fun setupUi() {
        createTaskTypeSpinner.adapter = typeAdapter
        createTaskTypeSpinner.onItemSelectedListener = object: SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                newTaskEntity.type = createTaskTypeSpinner.selectedItem as String
            }
        }
        createTaskStatusSpinner.adapter = statusAdapter
        createTaskStatusSpinner.onItemSelectedListener = object: SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                newTaskEntity.status = createTaskStatusSpinner.selectedItem as String
            }
        }
        createTaskPrioritySpinner.adapter = priorityAdapter
        createTaskPrioritySpinner.onItemSelectedListener = object: SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                newTaskEntity.priority = createTaskPrioritySpinner.selectedItem as String
            }
        }
        createTaskProjectSpinner.adapter = projectAdapter
        createTaskProjectSpinner.onItemSelectedListener = object: SimpleSpinnerListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedProject = projects.find { "[${it.projectId}] ${it.title}" == createTaskProjectSpinner.selectedItem as String }
                newTaskEntity.project = selectedProject!!.projectId!!

                newTaskEntity.assignedTo = ""
                newTaskEntity.sprintId = ""
                newTaskEntity.parent = ""
                newTaskEntity.partOf = ""
                newTaskEntity.blocks = ""
                newTaskEntity.linkedTo = ""
                newTaskEntity.subtaskOf = ""
                newTaskEntity.epic = ""

                fetchProjectData(selectedProject)
            }
        }
    }

    private fun fetchProjectData(project: ProjectEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            val sprints = appDatabase.getSprintsProvider()
                .getAllForProject(project.projectId as String)
                .sortedBy { it.createdDate }

            runOnUiThread { onSprintsProvided(sprints) }

            val userTeam = appDatabase.getUserTeamProvider()
                .getForTeam(project.assignedTeam as String)

            val projectUsers = appDatabase.getUsersProvider()
                .getAll()
                .filter { user -> user.userId in userTeam.map { it.userId } }
                .sortedBy { it.username }

            runOnUiThread { onUsersProvided(projectUsers) }

            val projectTasks = appDatabase.getTasksProvider()
                .getAllForProject(project.projectId as String)
                .sortedBy { it.title }

            runOnUiThread { onTasksProvided(projectTasks) }
        }
    }

    @MainThread
    private fun onProjectsProvided() {
        createTaskLoadingView.visibility = View.GONE
        projectAdapter.addAll(projects
            .map { pj -> "[${pj.projectId}] ${pj.title}" }
            .sortedBy { it }
        )
        projectAdapter.notifyDataSetChanged()
    }

    @MainThread
    private fun onSprintsProvided(sprints: List<SprintEntity>) {

    }

    @MainThread
    private fun onUsersProvided(users: List<UserEntity>) {

    }

    @MainThread
    private fun onTasksProvided(tasks: List<TaskEntity>) {

    }
}
