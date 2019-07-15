package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.dao.SprintsDao
import com.tracker.trackerv2.datasource.providers.local.room.entity.SprintEntity

class SprintsProvider(private val dao: SprintsDao): ISprintsProvider {
    override suspend fun getAllForProject(projectId: String): List<SprintEntity> = dao.getAllForProject(projectId)

    override suspend fun getSprintById(sprintId: String): SprintEntity? = dao.getSprintById(sprintId)

    override suspend fun create(sprint: SprintEntity): SprintEntity? = dao.getSprintById(dao.insert(sprint))

    override suspend fun update(sprint: SprintEntity) = dao.update(sprint)

    override suspend fun delete(sprint: SprintEntity) = dao.delete(sprint)
}