/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.tracking.delegates

import com.lexu.tracking.models.TeamTask

interface OngoingTaskContract {

    interface OngoingTaskView {
        fun setTask(task: TeamTask?)

        fun startTaskTracking()
        fun pauseTaskTracking()
        fun stopTaskTracking()
    }

    interface OngoingTaskDelegate {
        fun onTaskTrackingStarted(task: TeamTask)
        fun onTaskTrackingPaused(task: TeamTask)
        fun onTaskTrackingStopped(task: TeamTask, loggedTime: Long)

        fun onNavigateToTaskDetails(task: TeamTask, isEditMode: Boolean = false)
        fun onNavigateToTaskList()
    }

}