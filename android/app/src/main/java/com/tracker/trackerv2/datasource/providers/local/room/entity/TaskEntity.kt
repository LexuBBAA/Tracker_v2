package com.tracker.trackerv2.datasource.providers.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "taskId") val taskId: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "createdBy") val createdBy: String,
    @ColumnInfo(name = "assignedTo") val assignedTo: String,
    @ColumnInfo(name = "createdDate") val createdDate: Date,
    @ColumnInfo(name = "storyPoints") val storyPoints: Int,
    @ColumnInfo(name = "lastModifiedBy") val lastModifiedBy: String,
    @ColumnInfo(name = "lastModifiedAt") val lastModifiedAt: Date,
    @ColumnInfo(name = "dueDate") val dueDate: Date,
    @ColumnInfo(name = "estimate") val estimate: Double,
    @ColumnInfo(name = "partOf") val partOf: String,
    @ColumnInfo(name = "blocks") val blocks: String,
    @ColumnInfo(name = "linkedTo") val linkedTo: String,
    @ColumnInfo(name = "sprintId") val sprintId: String,
    @ColumnInfo(name = "priority") val priority: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "logged") val logged: Double,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "subtaskOf") val subtaskOf: String,
    @ColumnInfo(name = "parent") val parent: String,
    @ColumnInfo(name = "project") val project: String,
    @ColumnInfo(name = "epic") val epic: String,
    @ColumnInfo(name = "isEpic") val isEpic: Boolean = false
)