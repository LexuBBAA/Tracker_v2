package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.dao.TasksDao
import com.tracker.trackerv2.datasource.providers.local.room.entity.TaskEntity

class TasksProvider(private val dao: TasksDao): ITasksProvider {
    override fun getAll(): List<TaskEntity> = dao.getAll()

    override fun getAllForSprint(sprintId: String): List<TaskEntity> = dao.getAllForSprint(sprintId)

    override fun getAllForProject(projectId: String): List<TaskEntity> = dao.getAllForProject(projectId)

    override fun getAllForEpic(epicId: String): List<TaskEntity> = dao.getAllForEpic(epicId)

    override fun getAllAssignedToUser(userId: String): List<TaskEntity> = dao.getAllAssignedTo(userId)

    override fun getAllCreatedByUser(userId: String): List<TaskEntity> = dao.getAllCreatedBy(userId)

    override fun getDetails(taskId: String): TaskEntity = dao.getDetails(taskId)

    override fun create(newTask: TaskEntity) = dao.insert(newTask)

    override fun update(task: TaskEntity) = dao.update(task)

    override fun delete(task: TaskEntity) = dao.delete(task)
}