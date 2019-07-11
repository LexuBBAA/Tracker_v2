package com.tracker.trackerv2.datasource.providers.local.room.entity.utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usertokens")
data class UserTokenEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "userId") val userId: String,
    @ColumnInfo(name = "tokenId") val tokenId: String
)