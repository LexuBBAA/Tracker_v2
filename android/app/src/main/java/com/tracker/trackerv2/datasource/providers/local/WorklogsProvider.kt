package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.room.dao.WorklogsDao
import com.tracker.trackerv2.datasource.providers.room.entity.WorklogEntity

class WorklogsProvider(private val dao: WorklogsDao) : IWorklogsProvider {
    override fun getAllForTask(taskId: String): List<WorklogEntity> = dao.getForTask(taskId)

    override fun getAllForUser(userId: String): List<WorklogEntity> = dao.getForUser(userId)

    override fun create(newWorklog: WorklogEntity) = dao.insert(newWorklog)

    override fun update(worklog: WorklogEntity) = dao.update(worklog)

    override fun delete(worklog: WorklogEntity) = dao.delete(worklog)

    override fun deleteAllForTask(taskId: String) = dao.deleteAllForTask(taskId)
}