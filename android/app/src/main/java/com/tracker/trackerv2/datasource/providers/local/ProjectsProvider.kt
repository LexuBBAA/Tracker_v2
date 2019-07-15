package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.dao.ProjectsDao
import com.tracker.trackerv2.datasource.providers.local.room.entity.ProjectEntity

class ProjectsProvider(private val dao: ProjectsDao): IProjectsProvider {
    override suspend fun getAll(): List<ProjectEntity> = dao.getAll()

    override suspend fun create(newProject: ProjectEntity): ProjectEntity? = dao.getById(dao.insert(newProject))

    override suspend fun update(project: ProjectEntity) = dao.update(project)

    override suspend fun delete(project: ProjectEntity) = dao.delete(project)
}