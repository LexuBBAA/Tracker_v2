package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.dao.WorklogsDao
import com.tracker.trackerv2.datasource.providers.local.room.entity.WorklogEntity

class WorklogsProvider(private val dao: WorklogsDao) : IWorklogsProvider {
    override suspend fun getAll(): List<WorklogEntity> = dao.getAll()

    override suspend fun getAllForTask(taskId: String): List<WorklogEntity> = dao.getForTask(taskId)

    override suspend fun getAllForUser(userId: String): List<WorklogEntity> = dao.getForUser(userId)

    override suspend fun create(newWorklog: WorklogEntity): WorklogEntity? = dao.getDetails(dao.insert(newWorklog))

    override suspend fun update(worklog: WorklogEntity) = dao.update(worklog)

    override suspend fun delete(worklog: WorklogEntity) = dao.delete(worklog)

    override suspend fun deleteAllForTask(taskId: String) = dao.deleteAllForTask(taskId)
}