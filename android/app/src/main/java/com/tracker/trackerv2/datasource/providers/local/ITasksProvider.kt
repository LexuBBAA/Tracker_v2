package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.TaskEntity

interface ITasksProvider {
    suspend fun getAll() : List<TaskEntity>
    suspend fun getAllForSprint(sprintId: String): List<TaskEntity>
    suspend fun getAllForProject(projectId: String): List<TaskEntity>
    suspend fun getAllForEpic(epicId: String): List<TaskEntity>
    suspend fun getAllAssignedToUser(userId: String): List<TaskEntity>
    suspend fun getAllCreatedByUser(userId: String): List<TaskEntity>
    suspend fun getDetails(taskId: String): TaskEntity?
    suspend fun create(newTask: TaskEntity): TaskEntity?
    suspend fun update(task: TaskEntity)
    suspend fun delete(task: TaskEntity)
}