package com.tracker.trackerv2.datasource.providers.local.room.entity.utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "userteams")
data class UserTeamEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "userId") val userId: String,
    @ColumnInfo(name = "teamId") val teamId: String,
    @ColumnInfo(name = "joinedDate") val joinedDate: Date = Date(System.currentTimeMillis())
)