package com.tracker.trackerv2.datasource.providers.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

@Entity(tableName = "sprints")
data class SprintEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "sprintId") var sprintId: String? = null,
    @ColumnInfo(name = "title") var title: String = "Sprint ${SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date(System.currentTimeMillis()))}",
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "createdBy") var createdBy: String,
    @ColumnInfo(name = "createdDate") var createdDate: Date = Date(System.currentTimeMillis()),
    @ColumnInfo(name = "startDate") var startDate: Date? = null,
    @ColumnInfo(name = "endDate") var endDate: Date? = null,
    @ColumnInfo(name = "project") var project: String,
    @ColumnInfo(name = "status") var status: String
)