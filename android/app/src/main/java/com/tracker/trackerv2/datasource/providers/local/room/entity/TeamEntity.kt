package com.tracker.trackerv2.datasource.providers.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "teams")
data class TeamEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "teamId") var teamId: String? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "createdDate") var createdDate: Date = Date(System.currentTimeMillis()),
    @ColumnInfo(name = "createdBy") var createdBy: String
)