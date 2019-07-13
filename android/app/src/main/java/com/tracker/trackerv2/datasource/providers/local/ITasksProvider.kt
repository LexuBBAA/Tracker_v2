package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.TaskEntity

interface ITasksProvider {
    fun getAll() : List<TaskEntity>
    fun getAllForSprint(sprintId: String): List<TaskEntity>
    fun getAllForProject(projectId: String): List<TaskEntity>
    fun getAllForEpic(epicId: String): List<TaskEntity>
    fun getAllAssignedToUser(userId: String): List<TaskEntity>
    fun getAllCreatedByUser(userId: String): List<TaskEntity>
    fun getDetails(taskId: String): TaskEntity?
    fun create(newTask: TaskEntity): TaskEntity?
    fun update(task: TaskEntity)
    fun delete(task: TaskEntity)
}