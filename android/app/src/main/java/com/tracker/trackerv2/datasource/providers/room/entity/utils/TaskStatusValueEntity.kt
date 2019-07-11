package com.tracker.trackerv2.datasource.providers.room.entity.utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskstatusvalues")
data class TaskStatusValueEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "status") val status: String
)