package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.dao.WorklogsDao
import com.tracker.trackerv2.datasource.providers.local.room.entity.WorklogEntity

class WorklogsProvider(private val dao: WorklogsDao) : IWorklogsProvider {
    override fun getAll(): List<WorklogEntity> = dao.getAll()

    override fun getAllForTask(taskId: String): List<WorklogEntity> = dao.getForTask(taskId)

    override fun getAllForUser(userId: String): List<WorklogEntity> = dao.getForUser(userId)

    override fun create(newWorklog: WorklogEntity): WorklogEntity? = dao.getDetails(dao.insert(newWorklog))

    override fun update(worklog: WorklogEntity) = dao.update(worklog)

    override fun delete(worklog: WorklogEntity) = dao.delete(worklog)

    override fun deleteAllForTask(taskId: String) = dao.deleteAllForTask(taskId)
}