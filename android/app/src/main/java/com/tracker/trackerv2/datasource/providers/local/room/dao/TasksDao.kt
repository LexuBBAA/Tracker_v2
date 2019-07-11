package com.tracker.trackerv2.datasource.providers.local.room.dao

import androidx.room.*
import com.tracker.trackerv2.datasource.providers.local.room.entity.TaskEntity

@Dao
interface TasksDao {
    @Query("SELECT * FROM tasks")
    fun getAll() : List<TaskEntity>

    @Query("SELECT * FROM tasks WHERE epic = :epicId")
    fun getAllForEpic(epicId: String) : List<TaskEntity>

    @Query("SELECT * FROM tasks WHERE project = :projectId")
    fun getAllForProject(projectId: String) : List<TaskEntity>

    @Query("SELECT * FROM tasks WHERE sprintId = :sprintId")
    fun getAllForSprint(sprintId: String) : List<TaskEntity>

    @Query("SELECT * FROM tasks WHERE assignedTo = :assignedTo")
    fun getAllAssignedTo(assignedTo: String): List<TaskEntity>

    @Query("SELECT * FROM tasks WHERE createdBy = :createdBy")
    fun getAllCreatedBy(createdBy: String): List<TaskEntity>

    @Query("SELECT * FROM TASKS WHERE taskId = :taskId LIMIT 1")
    fun getDetails(taskId: String) : TaskEntity

    @Insert
    fun insert(task: TaskEntity)

    @Update
    fun update(task: TaskEntity)

    @Delete
    fun delete(task: TaskEntity)
}