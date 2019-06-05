/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.tracking.utils

import com.lexu.models.Status
import com.lexu.models.Type
import java.util.*

object MockData {
    private val currentDate = Calendar.getInstance()[Calendar.DAY_OF_WEEK]

    fun lastWeekTimes() = arrayListOf(
        DayLog(Calendar.SUNDAY, 0F),
        DayLog(Calendar.MONDAY, 8F),
        DayLog(Calendar.TUESDAY, 5.7F),
        DayLog(Calendar.WEDNESDAY, 4.9F),
        DayLog(Calendar.THURSDAY, 7.6F),
        DayLog(Calendar.FRIDAY, 6.35F),
        DayLog(Calendar.SATURDAY, 0F)
    )

    fun lastTeamTasks() = arrayListOf(
        TeamTask(Type.STORY, Status.OPEN, "Project"),
        TeamTask(Type.ISSUE, Status.REOPENED, "Crash"),
        TeamTask(Type.TASK, Status.DONE, "Create Dashboard"),
        TeamTask(Type.SUBTASK, Status.OPEN, "[Dashboard]Create searchView"),
        TeamTask(Type.STORY, Status.CLOSED, "Review reqs"),
        TeamTask(Type.TASK, Status.ON_HOLD, "Refactor Auth"),
        TeamTask(Type.TASK, Status.ON_HOLD, "Refactor Dashboard"),
        TeamTask(Type.SUBTASK, Status.IN_PROGRESS, "Review Dashboard"),
        TeamTask(Type.ISSUE, Status.OPEN, "Texts not aligned"),
        TeamTask(Type.ISSUE, Status.OPEN, "Cannot login"),
        TeamTask(Type.ISSUE, Status.REOPENED, "Cannot register"),
        TeamTask(Type.STORY, Status.IN_PROGRESS, "Test data"),
        TeamTask(Type.STORY, Status.OPEN, "Mock e2e flow"),
        TeamTask(Type.TASK, Status.OPEN, "Design"),
        TeamTask(Type.QUESTION, Status.OPEN, "Should you be able to update task?"),
        TeamTask(Type.QUESTION, Status.OPEN, "Should I be able to logout?"),
        TeamTask(Type.QUESTION, Status.OPEN, "[Suggestion]Biometrics Auth"),
        TeamTask(Type.ISSUE, Status.OPEN, "No data displayed"),
        TeamTask(Type.STORY, Status.IN_PROGRESS, "Task Details screen"),
        TeamTask(Type.STORY, Status.IN_QA, "New Task screen"),
        TeamTask(Type.SUBTASK, Status.DONE, "Create Timer"),
        TeamTask(Type.SUBTASK, Status.OPEN, "Tasks API integration")
    )
}

data class DayLog(val day: Int, val loggedTime: Float)

data class TeamTask(val type: Type, val status: Status, val title: String)