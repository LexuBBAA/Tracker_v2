package com.tracker.trackerv2.datasource.providers.local.room.entity.utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "userteams")
data class UserTeamEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "userId") var userId: String,
    @ColumnInfo(name = "teamId") var teamId: String,
    @ColumnInfo(name = "joinedDate") var joinedDate: Date = Date(System.currentTimeMillis())
)