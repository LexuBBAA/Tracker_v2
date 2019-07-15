package com.tracker.trackerv2.datasource.providers.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "userId") val userId: String? = null,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone") val phone: String? = null,
    @ColumnInfo(name = "firstname") var firstname: String? = null,
    @ColumnInfo(name = "lastname") var lastname: String? = null,
    @ColumnInfo(name = "createdAt") val createdAt: Date = Date(System.currentTimeMillis()),
    @ColumnInfo(name = "createdBy") val createdBy: String? = null,
    @ColumnInfo(name = "avatarUrl") var avatarUrl: String? = null,
    @ColumnInfo(name = "password") var password: String
)