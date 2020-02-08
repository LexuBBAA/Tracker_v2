package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.WorklogEntity

interface IWorklogsProvider {
    suspend fun getAll(): List<WorklogEntity>
    suspend fun getAllForTask(taskId: String): List<WorklogEntity>
    suspend fun getAllForUser(userId: String): List<WorklogEntity>
    suspend fun create(newWorklog: WorklogEntity): WorklogEntity?
    suspend fun update(worklog: WorklogEntity)
    suspend fun delete(worklog: WorklogEntity)
    suspend fun deleteAllForTask(taskId: String)
}