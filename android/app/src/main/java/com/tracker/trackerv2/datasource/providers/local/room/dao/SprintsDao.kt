package com.tracker.trackerv2.datasource.providers.local.room.dao

import androidx.room.*
import com.tracker.trackerv2.datasource.providers.local.room.entity.SprintEntity

@Dao
interface SprintsDao {
    @Query("SELECT * FROM sprints")
    fun getAll() : List<SprintEntity>

    @Query("SELECT * FROM sprints WHERE project = :projectId")
    fun getAllForProject(projectId: String) : List<SprintEntity>

    @Query("SELECT * FROM SPRINTS WHERE sprintId = :sprintId LIMIT 1")
    fun getSprintById(sprintId: String): SprintEntity

    @Insert
    fun insert(sprint: SprintEntity)

    @Update
    fun update(sprint: SprintEntity)

    @Delete
    fun delete(sprint: SprintEntity)
}