package com.tracker.trackerv2.datasource.providers.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "projectId") val projectId: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "createdBy") val createdBy: String,
    @ColumnInfo(name = "createdDate") val createdDate: Date,
    @ColumnInfo(name = "activeSprint") val activeSprint: String,
    @ColumnInfo(name = "assignedTeam") val assignedTeam: String
)