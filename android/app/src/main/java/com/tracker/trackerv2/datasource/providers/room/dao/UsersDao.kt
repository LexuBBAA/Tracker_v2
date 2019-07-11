package com.tracker.trackerv2.datasource.providers.room.dao

import androidx.room.*
import com.tracker.trackerv2.datasource.providers.room.entity.UserEntity

@Dao
interface UsersDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM users WHERE userId = :userId LIMIT 1")
    fun getDetails(userId: String) : UserEntity

    @Query("SELECT * FROM users WHERE createdBy = :createdBy")
    fun getCreatedBy(createdBy: String) : List<UserEntity>

    @Insert
    fun insert(user: UserEntity)

    @Update
    fun update(user: UserEntity)

    @Delete
    fun delete(user: UserEntity)
}