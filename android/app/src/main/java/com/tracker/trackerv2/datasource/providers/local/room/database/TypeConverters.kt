package com.tracker.trackerv2.datasource.providers.local.room.database

import androidx.room.TypeConverter
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

class TypeConverters {
    private val dateFormatter: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.getDefault())

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun stringToDate(string: String?): Date? {
        return string?.let { Date(dateFormatter.parse(it).time) }
    }

    @TypeConverter
    fun dateToString(date: Date?): String? {
        return date?.let { dateFormatter.format(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}