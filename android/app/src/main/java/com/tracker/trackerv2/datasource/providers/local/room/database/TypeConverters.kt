package com.tracker.trackerv2.datasource.providers.local.room.database

import androidx.room.TypeConverter
import java.sql.Date

class TypeConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}