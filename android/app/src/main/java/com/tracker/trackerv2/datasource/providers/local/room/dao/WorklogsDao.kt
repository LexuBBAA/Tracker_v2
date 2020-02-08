package com.tracker.trackerv2.datasource.providers.local.room.dao

import androidx.room.*
import com.tracker.trackerv2.datasource.providers.local.room.entity.WorklogEntity

@Dao
interface WorklogsDao {
    @Query("SELECT * FROM worklogs")
    fun getAll(): List<WorklogEntity>

    @Query("SELECT * FROM worklogs WHERE relatesTo = :relatesTo")
    fun getForTask(relatesTo: String) : List<WorklogEntity>

    @Query("SELECT * FROM worklogs WHERE createdBy = :createdBy")
    fun getForUser(createdBy: String) : List<WorklogEntity>

    @Query("SELECT * FROM worklogs WHERE id = :id LIMIT 1")
    fun getDetails(id: Long) : WorklogEntity?

    @Insert
    fun insert(worklog: WorklogEntity): Long

    @Update
    fun update(worklog: WorklogEntity)

    @Delete
    fun delete(worklog: WorklogEntity)

    @Query("DELETE FROM worklogs WHERE relatesTo = :relatedTo")
    fun deleteAllForTask(relatedTo: String)
}