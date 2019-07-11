package com.tracker.trackerv2.datasource.providers.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "userId") val userId: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "firstname") var firstname: String,
    @ColumnInfo(name = "lastname") var lastname: String,
    @ColumnInfo(name = "createdAt") val createdAt: Date,
    @ColumnInfo(name = "createdBy") val createdBy: String,
    @ColumnInfo(name = "avatarUrl") var avatarUrl: String,
    @ColumnInfo(name = "password") var password: String
)