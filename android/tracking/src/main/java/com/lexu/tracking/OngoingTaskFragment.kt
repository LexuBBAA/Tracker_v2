/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.tracking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.lexu.models.Type
import com.lexu.tracking.delegates.OngoingTaskContract
import com.lexu.tracking.models.TeamTask
import kotlinx.coroutines.*

class OngoingTaskFragment(private val contract: OngoingTaskContract.OngoingTaskDelegate) : Fragment(),
    OngoingTaskContract.OngoingTaskView {
    private var ongoingTask: TeamTask? = null
    private var loggedTime: Long = 0

    private var isTracking = false
    private var isPaused = false

    private var ongoingJob: Job? = null

    private lateinit var rootView: View

    private lateinit var detailsContainer: LinearLayout
    private lateinit var errorMessageContainer: RelativeLayout
    private lateinit var actionButtonsContainer: LinearLayout

    private lateinit var icon: AppCompatImageView
    private lateinit var titleLabel: AppCompatTextView
    private lateinit var typeLabel: AppCompatTextView
    private lateinit var statusLabel: AppCompatTextView
    private lateinit var priorityLabel: AppCompatTextView
    private lateinit var durationLabel: AppCompatTextView

    private lateinit var editButton: AppCompatImageButton
    private lateinit var stopButton: AppCompatImageButton
    private lateinit var pauseButton: AppCompatImageButton
    private lateinit var startButton: AppCompatImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_ongoing_task, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsContainer = rootView.findViewById(R.id.ongoingTaskDetailsContainer)
        errorMessageContainer = rootView.findViewById(R.id.ongoingTaskErrorMessageContainer)
        actionButtonsContainer = rootView.findViewById(R.id.ongoingTaskActionButtonsContainer)

        icon = rootView.findViewById(R.id.ongoingTaskIcon)
        titleLabel = rootView.findViewById(R.id.ongoingTaskTitleLabel)
        typeLabel = rootView.findViewById(R.id.ongoingTaskTypeLabel)
        statusLabel = rootView.findViewById(R.id.ongoingTaskStatusLabel)
        priorityLabel = rootView.findViewById(R.id.ongoingTaskPriorityLabel)
        durationLabel = rootView.findViewById(R.id.ongoingTaskDurationLabel)
        editButton = rootView.findViewById(R.id.ongoingTaskEditButton)
        stopButton = rootView.findViewById(R.id.ongoingTaskStopButton)
        pauseButton = rootView.findViewById(R.id.ongoingTaskPauseButton)
        startButton = rootView.findViewById(R.id.ongoingTaskStartButton)

        updateUI()

        detailsContainer.setOnClickListener {
            contract.onNavigateToTaskDetails(ongoingTask!!, false)
        }

        editButton.setOnClickListener {
            stopTaskTracking()
            contract.onNavigateToTaskDetails(ongoingTask!!, true)
        }
        stopButton.setOnClickListener { stopTaskTracking() }
        pauseButton.setOnClickListener { pauseTaskTracking() }
        startButton.setOnClickListener { startTaskTracking() }

        errorMessageContainer.setOnClickListener {
            contract.onNavigateToTaskList()
        }
    }

    private fun increaseTime() {
        loggedTime++
        updateUI()
    }

    private fun updateUI() {
        if(ongoingTask != null) {
            detailsContainer.visibility = View.VISIBLE
            errorMessageContainer.visibility = View.GONE
            actionButtonsContainer.visibility = View.VISIBLE

            titleLabel.text = ongoingTask!!.title
            typeLabel.text = ongoingTask!!.type.name
            statusLabel.text = ongoingTask!!.status.name
            priorityLabel.text = if (ongoingTask!!.type == Type.ISSUE) "High"
            else "Normal"

            val loggedSeconds = loggedTime % 60L
            var loggedMins = loggedTime / 60L
            var loggedHours = if(loggedMins >= 60L) {
                val hours = loggedMins / 60L
                loggedMins %= 60L
                hours
            } else 0L
            val loggedDays = if(loggedHours >= 8L) {
                val days = loggedHours / 8L
                loggedHours %= 8L
                days
            } else 0L

            val formattedDuration = when {
                loggedDays != 0L -> "${loggedMins}m\n${loggedHours}h\n${loggedDays}d"
                else -> "${loggedSeconds}s\n${loggedMins}m\n${loggedHours}h"
            }

            durationLabel.text = formattedDuration
        } else {
            detailsContainer.visibility = View.GONE
            errorMessageContainer.visibility = View.VISIBLE
            actionButtonsContainer.visibility = View.GONE
        }
    }

    override fun setTask(task: TeamTask?) {
        ongoingTask = task
        loggedTime = 0L
        updateUI()
    }

    override fun startTaskTracking() {
        isPaused = false
        if(isTracking) return
        isTracking = true
        ongoingTask?.let { task ->
            ongoingJob = GlobalScope.launch {
                while (isTracking) {
                    delay(1000)

                    GlobalScope.launch(Dispatchers.Main) {
                        if(!isPaused) increaseTime()
                    }
                }
            }
            contract.onTaskTrackingStarted(task)
        }
    }

    override fun pauseTaskTracking() {
        if(!isTracking || isPaused) return
        isPaused = true
        ongoingTask?.let { task ->
            contract.onTaskTrackingPaused(task)
        }
    }

    override fun stopTaskTracking() {
        if(!isTracking) return
        isTracking = false
        isPaused = false
        ongoingTask?.let { task ->
            updateUI()
            contract.onTaskTrackingStopped(task, loggedTime)
        }
    }
}