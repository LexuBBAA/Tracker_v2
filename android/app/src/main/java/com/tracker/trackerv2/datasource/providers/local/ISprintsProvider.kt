package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.SprintEntity

interface ISprintsProvider {
    fun getAllForProject(projectId: String): List<SprintEntity>
    fun getSprintById(sprintId: String) : SprintEntity?
    fun create(sprint: SprintEntity): SprintEntity?
    fun update(sprint: SprintEntity)
    fun delete(sprint: SprintEntity)
}