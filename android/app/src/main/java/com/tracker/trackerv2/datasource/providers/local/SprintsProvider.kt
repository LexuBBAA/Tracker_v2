package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.room.dao.SprintsDao
import com.tracker.trackerv2.datasource.providers.room.entity.SprintEntity

class SprintsProvider(private val dao: SprintsDao): ISprintsProvider {
    override fun getAllForProject(projectId: String): List<SprintEntity> = dao.getAllForProject(projectId)

    override fun getSprintById(sprintId: String): SprintEntity = dao.getSprintById(sprintId)

    override fun create(sprint: SprintEntity) = dao.insert(sprint)

    override fun update(sprint: SprintEntity) = dao.update(sprint)

    override fun delete(sprint: SprintEntity) = dao.delete(sprint)
}