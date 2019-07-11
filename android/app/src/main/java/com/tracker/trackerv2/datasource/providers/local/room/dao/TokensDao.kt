package com.tracker.trackerv2.datasource.providers.local.room.dao

import androidx.room.*
import com.tracker.trackerv2.datasource.providers.local.room.entity.TokenEntity

@Dao
interface TokensDao {
    @Query("SELECT * FROM accesstokens WHERE token = :token LIMIT 1")
    fun getToken(token: String) : TokenEntity

    @Insert
    fun insert(token: TokenEntity)

    @Update
    fun update(token: TokenEntity)

    @Delete
    fun delete(token: TokenEntity)
}