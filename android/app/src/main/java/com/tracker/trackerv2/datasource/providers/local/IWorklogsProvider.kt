package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.room.entity.WorklogEntity

interface IWorklogsProvider {
    fun getAllForTask(taskId: String): List<WorklogEntity>
    fun getAllForUser(userId: String): List<WorklogEntity>
    fun create(newWorklog: WorklogEntity)
    fun update(worklog: WorklogEntity)
    fun delete(worklog: WorklogEntity)
    fun deleteAllForTask(taskId: String)
}