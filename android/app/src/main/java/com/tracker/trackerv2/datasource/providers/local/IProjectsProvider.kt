package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.ProjectEntity

interface IProjectsProvider {
    fun getAll(): List<ProjectEntity>
    fun create(newProject: ProjectEntity): ProjectEntity?
    fun update(project: ProjectEntity)
    fun delete(project: ProjectEntity)
}