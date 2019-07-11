package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.room.dao.ProjectsDao
import com.tracker.trackerv2.datasource.providers.room.entity.ProjectEntity

class ProjectsProvider(private val dao: ProjectsDao): IProjectsProvider {
    override fun getAll(): List<ProjectEntity> = dao.getAll()

    override fun create(newProject: ProjectEntity) = dao.insert(newProject)

    override fun update(project: ProjectEntity) = dao.update(project)

    override fun delete(project: ProjectEntity) = dao.delete(project)
}