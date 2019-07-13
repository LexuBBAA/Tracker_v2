package com.tracker.trackerv2.datasource.providers.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.util.StringUtil
import com.google.android.gms.common.util.Strings
import java.sql.Date
import java.util.Random

@Entity(tableName = "accesstokens")
data class TokenEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "token") val token: String = "token",
    @ColumnInfo(name = "expiresAt") val expiresAt: Date? = null,
    @ColumnInfo(name = "refreshToken") val refeshToken: String = "refresh_token"
)