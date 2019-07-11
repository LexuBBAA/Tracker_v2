package com.tracker.trackerv2.datasource.providers.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "sprints")
data class SprintEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "sprintId") val sprintId: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "createdBy") val createdBy: String,
    @ColumnInfo(name = "createdDate") val createdDate: Date,
    @ColumnInfo(name = "startDate") val startDate: Date,
    @ColumnInfo(name = "endDate") val endDate: Date,
    @ColumnInfo(name = "project") val project: String,
    @ColumnInfo(name = "status") val status: String
)