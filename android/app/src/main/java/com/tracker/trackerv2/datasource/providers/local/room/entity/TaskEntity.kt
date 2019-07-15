package com.tracker.trackerv2.datasource.providers.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Date

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "taskId") var taskId: String? = null,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "createdBy") var createdBy: String,
    @ColumnInfo(name = "assignedTo") var assignedTo: String? = null,
    @ColumnInfo(name = "createdDate") var createdDate: Date = Date(System.currentTimeMillis()),
    @ColumnInfo(name = "storyPoints") var storyPoints: Int? = null,
    @ColumnInfo(name = "lastModifiedBy") var lastModifiedBy: String = createdBy,
    @ColumnInfo(name = "lastModifiedAt") var lastModifiedAt: Date = Date(System.currentTimeMillis()),
    @ColumnInfo(name = "dueDate") var dueDate: Date? = null,
    @ColumnInfo(name = "estimate") var estimate: Double = 0.0,
    @ColumnInfo(name = "partOf") var partOf: String? = null,
    @ColumnInfo(name = "blocks") var blocks: String? = null,
    @ColumnInfo(name = "linkedTo") var linkedTo: String? = null,
    @ColumnInfo(name = "sprintId") var sprintId: String,
    @ColumnInfo(name = "priority") var priority: String,
    @ColumnInfo(name = "status") var status: String,
    @ColumnInfo(name = "logged") var logged: Double = 0.0,
    @ColumnInfo(name = "type") var type: String,
    @ColumnInfo(name = "subtaskOf") var subtaskOf: String? = null,
    @ColumnInfo(name = "parent") var parent: String? = null,
    @ColumnInfo(name = "project") var project: String,
    @ColumnInfo(name = "epic") var epic: String? = null,
    @ColumnInfo(name = "isEpic") var isEpic: Boolean = false
): Serializable