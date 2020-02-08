package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.dao.TasksDao
import com.tracker.trackerv2.datasource.providers.local.room.entity.TaskEntity

class TasksProvider(private val dao: TasksDao): ITasksProvider {
    override suspend fun getAll(): List<TaskEntity> = dao.getAll()

    override suspend fun getAllForSprint(sprintId: String): List<TaskEntity> = dao.getAllForSprint(sprintId)

    override suspend fun getAllForProject(projectId: String): List<TaskEntity> = dao.getAllForProject(projectId)

    override suspend fun getAllForEpic(epicId: String): List<TaskEntity> = dao.getAllForEpic(epicId)

    override suspend fun getAllAssignedToUser(userId: String): List<TaskEntity> = dao.getAllAssignedTo(userId)

    override suspend fun getAllCreatedByUser(userId: String): List<TaskEntity> = dao.getAllCreatedBy(userId)

    override suspend fun getDetails(taskId: String): TaskEntity? = dao.getDetails(taskId)

    override suspend fun create(newTask: TaskEntity): TaskEntity? = dao.getDetails(dao.insert(newTask))

    override suspend fun update(task: TaskEntity) = dao.update(task)

    override suspend fun delete(task: TaskEntity) = dao.delete(task)
}