package com.tracker.trackerv2.datasource.providers.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "projectId") val projectId: String? = null,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "createdBy") val createdBy: String,
    @ColumnInfo(name = "createdDate") val createdDate: Date = Date(System.currentTimeMillis()),
    @ColumnInfo(name = "activeSprint") val activeSprint: String? = null,
    @ColumnInfo(name = "assignedTeam") val assignedTeam: String? = null
)