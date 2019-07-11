package com.tracker.trackerv2.datasource.providers.room.dao

import androidx.room.*
import com.tracker.trackerv2.datasource.providers.room.entity.ProjectEntity

@Dao
interface ProjectsDao {
    @Query("SELECT * FROM projects")
    fun getAll() : List<ProjectEntity>

    @Insert
    fun insert(project: ProjectEntity)

    @Update
    fun update(project: ProjectEntity)

    @Delete
    fun delete(project: ProjectEntity)
}