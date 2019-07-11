package com.tracker.trackerv2.datasource.providers.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "worklogs")
data class WorklogEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "worklogId") val worklogId: String,
    @ColumnInfo(name = "value") val value: Double,
    @ColumnInfo(name = "comment") val comment: String,
    @ColumnInfo(name = "relatesTo") val relatesTo: String,
    @ColumnInfo(name = "createdAt") val createdDate: Date,
    @ColumnInfo(name = "createdBy") val createdBy: String,
    @ColumnInfo(name = "lastModifiedBy") val lastModifiedBy: String,
    @ColumnInfo(name = "lastModifiedAt") val lastModifiedAt: Date
)