package com.tracker.trackerv2.datasource.providers.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tracker.trackerv2.datasource.providers.local.room.entity.utils.UserTeamEntity

@Dao
interface UserTeamDao {
    @Query("SELECT * FROM userteams WHERE userId = :userId LIMIT 1")
    fun getForUser(userId: String): UserTeamEntity?

    @Query("SELECT * FROM userteams WHERE teamId = :teamId")
    fun getForTeam(teamId: String): List<UserTeamEntity>

    @Query("SELECT * FROM userteams WHERE id = :id LIMIT 1")
    fun getById(id: Long) : UserTeamEntity?

    @Insert
    fun insert(newUserTeamEntity: UserTeamEntity): Long

    @Delete
    fun delete(userTeamEntity: UserTeamEntity)
}