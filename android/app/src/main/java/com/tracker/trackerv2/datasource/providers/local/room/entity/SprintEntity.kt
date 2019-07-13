package com.tracker.trackerv2.datasource.providers.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

@Entity(tableName = "sprints")
data class SprintEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "sprintId") val sprintId: String? = null,
    @ColumnInfo(name = "title") val title: String = "Sprint ${SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date(System.currentTimeMillis()))}",
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "createdBy") val createdBy: String,
    @ColumnInfo(name = "createdDate") val createdDate: Date = Date(System.currentTimeMillis()),
    @ColumnInfo(name = "startDate") val startDate: Date? = null,
    @ColumnInfo(name = "endDate") val endDate: Date? = null,
    @ColumnInfo(name = "project") val project: String,
    @ColumnInfo(name = "status") val status: String
)