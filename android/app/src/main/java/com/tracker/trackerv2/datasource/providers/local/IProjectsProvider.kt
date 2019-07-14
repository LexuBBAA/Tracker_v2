package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.ProjectEntity

interface IProjectsProvider {
    suspend fun getAll(): List<ProjectEntity>
    suspend fun create(newProject: ProjectEntity): ProjectEntity?
    suspend fun update(project: ProjectEntity)
    suspend fun delete(project: ProjectEntity)
}