package com.tracker.trackerv2.datasource.providers.local.room.dao

import androidx.room.*
import com.tracker.trackerv2.datasource.providers.local.room.entity.TokenEntity

@Dao
interface TokensDao {
    @Query("SELECT * FROM accesstokens WHERE token = :token LIMIT 1")
    fun getToken(token: String) : TokenEntity?

    @Query("SELECT * FROM accesstokens WHERE id = :id LIMIT 1")
    fun getToken(id: Long) : TokenEntity?

    @Insert
    fun insert(token: TokenEntity): Long

    @Update
    fun update(token: TokenEntity)

    @Delete
    fun delete(token: TokenEntity)
}