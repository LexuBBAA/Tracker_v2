/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.tracker.trackerv2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lexu.tracking.utils.TeamTask
import com.tracker.trackerv2.adapters.TaskListAdapter
import kotlinx.android.synthetic.main.activity_task_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class TaskListActivity : AppCompatActivity(), TaskListAdapter.OnItemClickListener {
    private val mockDataParser = MockDataParser(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        taskListRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = TaskListAdapter(this)
        taskListRecyclerView.adapter = adapter

        GlobalScope.async {
            val tasks = mockDataParser.getTeamWeekStats()
            runOnUiThread {
                adapter.setItems(tasks)
            }
        }
    }

    override fun onItemClicked(item: TeamTask, position: Int) {
        Log.i(TaskListActivity::class.simpleName, "Task selected: $item")
    }

    override fun onItemDetailsClicked(item: TeamTask, position: Int) {
        Log.i(TaskListActivity::class.simpleName, "Navigate to task details requested for: $item")
    }
}
