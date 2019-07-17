package com.lexu.tracking.models

class TaskAllocationDataModel(val userId: String, val username: String, val userAvatarUrl: String, val tasks: List<AllocatedTask>) {

    data class AllocatedTask(val taskId: String, val loggedWork: Double = 0.0, val priority: Int = 0)
}