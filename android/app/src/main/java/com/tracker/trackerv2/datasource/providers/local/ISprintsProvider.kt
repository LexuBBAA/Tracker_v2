package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.SprintEntity

interface ISprintsProvider {
    suspend fun getAllForProject(projectId: String): List<SprintEntity>
    suspend fun getSprintById(sprintId: String) : SprintEntity?
    suspend fun create(sprint: SprintEntity): SprintEntity?
    suspend fun update(sprint: SprintEntity)
    suspend fun delete(sprint: SprintEntity)
}