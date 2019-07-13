package com.tracker.trackerv2.datasource.providers.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "worklogs")
data class WorklogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "worklogId") val worklogId: String? = null,
    @ColumnInfo(name = "value") val value: Double,
    @ColumnInfo(name = "comment") val comment: String? = null,
    @ColumnInfo(name = "relatesTo") val relatesTo: String,
    @ColumnInfo(name = "createdAt") val createdDate: Date = Date(System.currentTimeMillis()),
    @ColumnInfo(name = "createdBy") val createdBy: String,
    @ColumnInfo(name = "lastModifiedBy") val lastModifiedBy: String = createdBy,
    @ColumnInfo(name = "lastModifiedAt") val lastModifiedAt: Date = Date(System.currentTimeMillis())
)