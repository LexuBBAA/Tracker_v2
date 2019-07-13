package com.tracker.trackerv2.datasource.providers.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "taskId") val taskId: String? = null,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "createdBy") val createdBy: String,
    @ColumnInfo(name = "assignedTo") val assignedTo: String? = null,
    @ColumnInfo(name = "createdDate") val createdDate: Date = Date(System.currentTimeMillis()),
    @ColumnInfo(name = "storyPoints") val storyPoints: Int? = null,
    @ColumnInfo(name = "lastModifiedBy") val lastModifiedBy: String = createdBy,
    @ColumnInfo(name = "lastModifiedAt") val lastModifiedAt: Date = Date(System.currentTimeMillis()),
    @ColumnInfo(name = "dueDate") val dueDate: Date? = null,
    @ColumnInfo(name = "estimate") val estimate: Double = 0.0,
    @ColumnInfo(name = "partOf") val partOf: String? = null,
    @ColumnInfo(name = "blocks") val blocks: String? = null,
    @ColumnInfo(name = "linkedTo") val linkedTo: String? = null,
    @ColumnInfo(name = "sprintId") val sprintId: String,
    @ColumnInfo(name = "priority") val priority: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "logged") val logged: Double = 0.0,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "subtaskOf") val subtaskOf: String? = null,
    @ColumnInfo(name = "parent") val parent: String? = null,
    @ColumnInfo(name = "project") val project: String,
    @ColumnInfo(name = "epic") val epic: String? = null,
    @ColumnInfo(name = "isEpic") val isEpic: Boolean = false
)