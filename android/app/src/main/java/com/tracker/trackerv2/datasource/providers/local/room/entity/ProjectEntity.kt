package com.tracker.trackerv2.datasource.providers.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "projectId") var projectId: String? = null,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "createdBy") var createdBy: String,
    @ColumnInfo(name = "createdDate") var createdDate: Date = Date(System.currentTimeMillis()),
    @ColumnInfo(name = "activeSprint") var activeSprint: String? = null,
    @ColumnInfo(name = "assignedTeam") var assignedTeam: String? = null
)