package com.tracker.trackerv2.configs

import com.lexu.models.Priority
import com.lexu.models.Status
import com.lexu.models.Type
import java.io.Serializable

class SearchTaskConfigBundle(
    val userId: String,
    var searchQuery: String? = null,
    var typeFilter: Config.TypeFilter = Config.TypeFilter.FILTER_ALL,
    var statusFilter: Config.StatusFilter = Config.StatusFilter.FILTER_ALL,
    var priorityFilter: Config.PriorityFilter = Config.PriorityFilter.FILTER_ALL,
    var assigneeFilter: Config.AssigneeFilter = Config.AssigneeFilter.FILTER_ALL,
    var sortOrder: Config.SortOrder = Config.SortOrder.DEFAULT
) : Serializable

abstract class Config {

    enum class TypeFilter(val value: String, val filter: Type?) {
        FILTER_ALL("All", null),
        FILTER_STORY("Story", Type.STORY),
        FILTER_TASK("Task", Type.TASK),
        FILTER_SUBTASK("Subtask", Type.SUBTASK),
        FILTER_ISSUE("Issue", Type.ISSUE),
        FILTER_QUESTION("Question", Type.QUESTION);

        companion object {
            fun get(value: String): TypeFilter = when(value) {
                FILTER_STORY.value -> FILTER_STORY
                FILTER_TASK.value -> FILTER_TASK
                FILTER_SUBTASK.value -> FILTER_SUBTASK
                FILTER_ISSUE.value -> FILTER_ISSUE
                FILTER_QUESTION.value -> FILTER_QUESTION
                else -> FILTER_ALL
            }

            fun get(filter: Type?): TypeFilter = when(filter) {
                Type.STORY -> FILTER_STORY
                Type.TASK -> FILTER_TASK
                Type.SUBTASK -> FILTER_SUBTASK
                Type.ISSUE -> FILTER_ISSUE
                Type.QUESTION -> FILTER_QUESTION
                null -> FILTER_ALL
            }
        }
    }

    enum class StatusFilter(val value: String, val filter: Status?) {
        FILTER_ALL("All", null),
        FILTER_OPEN("Open", Status.OPEN),
        FILTER_IN_PROGRESS("In Progress", Status.IN_PROGRESS),
        FILTER_ON_HOLD("On Hold", Status.ON_HOLD),
        FILTER_DONE("Done", Status.DONE),
        FILTER_CLOSED("Closed", Status.CLOSED),
        FILTER_REOPENED("Reopened", Status.REOPENED),
        FILTER_IN_QA("In QA", Status.IN_QA);

        companion object {
            fun get(value: String): StatusFilter = when(value) {
                FILTER_ALL.value -> FILTER_ALL
                FILTER_OPEN.value -> FILTER_OPEN
                FILTER_IN_PROGRESS.value -> FILTER_IN_PROGRESS
                FILTER_ON_HOLD.value -> FILTER_ON_HOLD
                FILTER_DONE.value -> FILTER_DONE
                FILTER_CLOSED.value -> FILTER_CLOSED
                FILTER_REOPENED.value -> FILTER_REOPENED
                FILTER_IN_QA.value -> FILTER_IN_QA
                else -> FILTER_ALL
            }

            fun get(filter: Status?): StatusFilter = when(filter) {
                Status.OPEN -> FILTER_OPEN
                Status.IN_PROGRESS -> FILTER_IN_PROGRESS
                Status.ON_HOLD -> FILTER_ON_HOLD
                Status.DONE -> FILTER_DONE
                Status.CLOSED -> FILTER_CLOSED
                Status.REOPENED -> FILTER_REOPENED
                Status.IN_QA -> FILTER_IN_QA
                else -> FILTER_ALL
            }
        }
    }

    enum class PriorityFilter(val value: String, val filter: Priority?) {
        FILTER_ALL("All", null),
        FILTER_BLOCKER("Blocker", Priority.BLOCKER),
        FILTER_CRITICAL("Critical", Priority.CRITICAL),
        FILTER_HIGH("High", Priority.HIGH),
        FILTER_MEDIUM("Medium", Priority.MEDIUM),
        FILTER_LOW("Low", Priority.LOW);

        companion object {
            fun get(value: String): PriorityFilter = when(value) {
                FILTER_BLOCKER.value -> FILTER_BLOCKER
                FILTER_CRITICAL.value -> FILTER_CRITICAL
                FILTER_HIGH.value -> FILTER_HIGH
                FILTER_MEDIUM.value -> FILTER_MEDIUM
                FILTER_LOW.value -> FILTER_LOW
                else -> FILTER_ALL
            }

            fun get(filter: Priority?): PriorityFilter = when(filter) {
                Priority.BLOCKER -> FILTER_BLOCKER
                Priority.CRITICAL -> FILTER_CRITICAL
                Priority.HIGH -> FILTER_HIGH
                Priority.MEDIUM -> FILTER_MEDIUM
                Priority.LOW -> FILTER_LOW
                else -> FILTER_ALL
            }
        }
    }

    enum class AssigneeFilter(val value: String) {
        FILTER_ALL("All"),
        FILTER_PERSONAL("Only my tasks"),
        FILTER_OWNED("Created by me");

        companion object {
            fun get(value: String): AssigneeFilter = when(value) {
                FILTER_PERSONAL.value -> FILTER_PERSONAL
                FILTER_OWNED.value -> FILTER_OWNED
                else -> FILTER_ALL
            }
        }
    }

    enum class SortOrder(val value: String) {
        DEFAULT("Default"),
        ALPHA_ASC("Alphabetic (A-Z)"),
        ALPHA_DESC("Alphabetic (Z-A)"),
        CREATED_ASC("Oldest to Newest"),
        CREATED_DESC("Newest to Oldest");

        companion object {
            fun get(value: String): SortOrder = when(value) {
                ALPHA_ASC.value -> ALPHA_ASC
                ALPHA_DESC.value -> ALPHA_DESC
                CREATED_ASC.value -> CREATED_ASC
                CREATED_DESC.value -> CREATED_DESC
                else -> DEFAULT
            }
        }
    }

}