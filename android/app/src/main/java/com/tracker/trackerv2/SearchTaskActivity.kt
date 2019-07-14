/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.tracker.trackerv2

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tracker.trackerv2.adapters.TaskListAdapter
import com.tracker.trackerv2.configs.Config
import com.tracker.trackerv2.configs.SearchTaskConfigBundle
import com.tracker.trackerv2.datasource.providers.local.UserSessionProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import com.tracker.trackerv2.datasource.providers.local.room.entity.TaskEntity
import kotlinx.android.synthetic.main.activity_task_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchTaskActivity : AppCompatActivity(), TaskListAdapter.OnItemClickListener {
    private lateinit var sessionProvider: UserSessionProvider
    private lateinit var appDatabase: AppDatabase

    private lateinit var searchConfig: SearchTaskConfigBundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        sessionProvider = UserSessionProvider(this)
        appDatabase = AppDatabase.getDatabase(this)

        val config = intent.getSerializableExtra(KEY_SEARCH_CONFIG_EXTRA) as SearchTaskConfigBundle?
        CoroutineScope(Dispatchers.IO).launch {
            searchConfig = config ?:SearchTaskConfigBundle(sessionProvider.getUserId() ?: "")
        }

        setupUi()
        val adapter = TaskListAdapter(this)
        searchTaskRecyclerView.layoutManager = LinearLayoutManager(this)
        searchTaskRecyclerView.adapter = adapter

        GlobalScope.async {
            val tasks = appDatabase.getTasksProvider().getAll()
            delay(1500)

            runOnUiThread {
                searchTaskRecyclerView.visibility = if(tasks.isEmpty()) View.GONE
                else View.VISIBLE

                searchTaskNoResultsContainer.visibility = View.VISIBLE
                searchTaskLoadingView.visibility = View.GONE

                searchTaskNoResultsContainer.visibility = if(tasks.isEmpty()) View.VISIBLE
                else View.GONE

                adapter.setItems(tasks)
            }
        }
    }

    private fun setupUi() {
        val textWatcher = object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                searchConfig.searchQuery = s?.toString() ?: ""
                (searchTaskRecyclerView.adapter as TaskListAdapter).onSearchConfigChanged(searchConfig)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //  not needed
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //  not needed
            }
        }
        searchTaskInputField.addTextChangedListener(textWatcher)
        searchTaskFilterButton.setOnClickListener {
            searchTaskFiltersContainer.visibility = if(searchTaskFiltersContainer.visibility == View.VISIBLE) View.GONE
            else View.VISIBLE
        }

        searchTaskBackButton.setOnClickListener { finish() }

        val typeSpinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Config.TypeFilter.values().map { it.value })
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        searchTaskTypeSpinner.adapter = typeSpinnerAdapter

        val statusSpinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Config.StatusFilter.values().map { it.value })
        statusSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        searchTaskStatusSpinner.adapter = statusSpinnerAdapter

        val prioritySpinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Config.PriorityFilter.values().map { it.value })
        prioritySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        searchTaskPrioritySpinner.adapter = prioritySpinnerAdapter

        val assigneeSpinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Config.AssigneeFilter.values().map { it.value })
        assigneeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        searchTaskAssigneeSpinner.adapter = assigneeSpinnerAdapter

        val sortOrderSpinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Config.SortOrder.values().map { it.value })
        sortOrderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        searchTaskSortBySpinner.adapter = sortOrderSpinnerAdapter

        searchTaskTypeSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //  not used
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                searchConfig.typeFilter = Config.TypeFilter.get(searchTaskTypeSpinner.selectedItem as String)
                (searchTaskRecyclerView.adapter as TaskListAdapter).onSearchConfigChanged(searchConfig)
            }
        }
        searchTaskStatusSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //  not used
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                searchConfig.statusFilter = Config.StatusFilter.get(searchTaskStatusSpinner.selectedItem as String)
                (searchTaskRecyclerView.adapter as TaskListAdapter).onSearchConfigChanged(searchConfig)
            }
        }
        searchTaskPrioritySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //  not used
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                searchConfig.priorityFilter = Config.PriorityFilter.get(searchTaskPrioritySpinner.selectedItem as String)
                (searchTaskRecyclerView.adapter as TaskListAdapter).onSearchConfigChanged(searchConfig)
            }
        }
        searchTaskAssigneeSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //  not used
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                searchConfig.assigneeFilter = Config.AssigneeFilter.get(searchTaskAssigneeSpinner.selectedItem as String)
                (searchTaskRecyclerView.adapter as TaskListAdapter).onSearchConfigChanged(searchConfig)
            }
        }
        searchTaskSortBySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //  not used
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                searchConfig.sortOrder = Config.SortOrder.get(searchTaskSortBySpinner.selectedItem as String)
                (searchTaskRecyclerView.adapter as TaskListAdapter).onSearchConfigChanged(searchConfig)
            }
        }

        searchTaskFiltersContainer.visibility = View.GONE
        searchTaskRecyclerView.visibility = View.GONE
        searchTaskNoResultsContainer.visibility = View.VISIBLE
        searchTaskErrorMessage.visibility = View.GONE
        searchTaskLoadingView.visibility = View.VISIBLE
    }

    @MainThread
    override fun onItemClicked(item: TaskEntity, position: Int) {
        Log.i(SearchTaskActivity::class.simpleName, "Task selected: $item")
        navigateToTaskDetails(item)
    }

    @MainThread
    override fun onItemDetailsClicked(item: TaskEntity, position: Int) {
        Log.i(SearchTaskActivity::class.simpleName, "Navigate to task details requested for: $item")
        navigateToTaskDetails(item)
    }

    private fun navigateToTaskDetails(task: TaskEntity) {
        val intent = Intent(this, TaskDetailsActivity::class.java)
        intent.putExtra(TaskDetailsActivity.KEY_TASK_ID_EXTRA, task.taskId)
        startActivity(intent)
        finish()
    }

    companion object {
        const val KEY_SEARCH_CONFIG_EXTRA = "search_task_config_extra"
    }
}
