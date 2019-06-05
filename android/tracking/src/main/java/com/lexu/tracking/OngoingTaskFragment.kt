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
import com.lexu.models.TrackerTask
import com.lexu.models.Type

class OngoingTaskFragment: Fragment() {
    private var ongoingTask: TrackerTask? = null

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

        ongoingTask?.let { task ->
            detailsContainer.visibility = View.VISIBLE
            errorMessageContainer.visibility = View.GONE
            actionButtonsContainer.visibility = View.VISIBLE

            titleLabel.text = task.title
            typeLabel.text = task.type.name
            statusLabel.text = task.status.name
            priorityLabel.text = if(task.type == Type.ISSUE) "High"
            else "Normal"
        }

        editButton.setOnClickListener {  }
        stopButton.setOnClickListener {  }
        pauseButton.setOnClickListener {  }
        startButton.setOnClickListener {  }
    }
}