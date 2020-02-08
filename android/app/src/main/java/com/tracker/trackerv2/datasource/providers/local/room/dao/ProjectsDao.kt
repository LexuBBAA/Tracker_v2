package com.tracker.trackerv2.datasource.providers.local.room.dao

import androidx.room.*
import com.tracker.trackerv2.datasource.providers.local.room.entity.ProjectEntity

@Dao
interface ProjectsDao {
    @Query("SELECT * FROM projects")
    fun getAll() : List<ProjectEntity>

    @Query("SELECT * FROM projects WHERE id = :id LIMIT 1")
    fun getById(id: Long) : ProjectEntity?

    @Insert
    fun insert(project: ProjectEntity): Long

    @Update
    fun update(project: ProjectEntity)

    @Delete
    fun delete(project: ProjectEntity)
}