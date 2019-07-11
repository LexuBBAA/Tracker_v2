package com.tracker.trackerv2.datasource.providers.local.room.entity.utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userteams")
data class UserTeamEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "userId") val userId: String,
    @ColumnInfo(name = "teamId") val teamId: String,
    @ColumnInfo(name = "joinedDate") val joinedDate: String
)