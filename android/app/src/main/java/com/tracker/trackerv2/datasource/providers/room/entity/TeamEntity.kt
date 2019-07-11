package com.tracker.trackerv2.datasource.providers.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "teams")
data class TeamEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "teamId") val teamId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "createdDate") val createdDate: Date,
    @ColumnInfo(name = "createdBy") val createdBy: String
)