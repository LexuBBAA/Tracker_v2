package com.tracker.trackerv2.datasource.providers.room.entity.utils

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class TaskTypeEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "type") val type: String
)