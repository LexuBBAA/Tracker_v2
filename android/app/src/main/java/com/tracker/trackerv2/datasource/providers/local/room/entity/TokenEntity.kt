package com.tracker.trackerv2.datasource.providers.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "accesstokens")
data class TokenEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "token") val token: String,
    @ColumnInfo(name = "expiresAt") val expiresAt: Date,
    @ColumnInfo(name = "refreshToken") val refeshToken: String
)