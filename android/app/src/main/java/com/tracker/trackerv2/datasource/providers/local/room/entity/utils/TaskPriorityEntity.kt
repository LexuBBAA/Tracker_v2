package com.tracker.trackerv2.datasource.providers.local.room.entity.utils

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class TaskPriorityEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "priority") val priority: String
)