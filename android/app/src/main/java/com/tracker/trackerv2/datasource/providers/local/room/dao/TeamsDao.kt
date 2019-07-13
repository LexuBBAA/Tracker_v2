package com.tracker.trackerv2.datasource.providers.local.room.dao

import androidx.room.*
import com.tracker.trackerv2.datasource.providers.local.room.entity.TeamEntity

@Dao
interface TeamsDao {
    @Query("SELECT * FROM teams")
    fun getAll() : List<TeamEntity>

    @Query("SELECT * FROM TEAMS WHERE teamId = :teamId LIMIT 1")
    fun getById(teamId: String) : TeamEntity?

    @Query("SELECT * FROM TEAMS WHERE id = :teamId LIMIT 1")
    fun getById(teamId: Long) : TeamEntity?

    @Insert
    fun insert(team: TeamEntity): Long

    @Update
    fun update(team: TeamEntity)

    @Delete
    fun delete(team: TeamEntity)
}