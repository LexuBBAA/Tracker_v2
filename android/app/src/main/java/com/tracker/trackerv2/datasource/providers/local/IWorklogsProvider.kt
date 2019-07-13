package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.WorklogEntity

interface IWorklogsProvider {
    fun getAll(): List<WorklogEntity>
    fun getAllForTask(taskId: String): List<WorklogEntity>
    fun getAllForUser(userId: String): List<WorklogEntity>
    fun create(newWorklog: WorklogEntity): WorklogEntity?
    fun update(worklog: WorklogEntity)
    fun delete(worklog: WorklogEntity)
    fun deleteAllForTask(taskId: String)
}