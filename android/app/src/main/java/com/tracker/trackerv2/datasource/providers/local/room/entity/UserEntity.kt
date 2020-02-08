package com.tracker.trackerv2.datasource.providers.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "userId") var userId: String? = null,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "phone") var phone: String? = null,
    @ColumnInfo(name = "firstname") var firstname: String? = null,
    @ColumnInfo(name = "lastname") var lastname: String? = null,
    @ColumnInfo(name = "createdAt") var createdAt: Date = Date(System.currentTimeMillis()),
    @ColumnInfo(name = "createdBy") var createdBy: String? = null,
    @ColumnInfo(name = "avatarUrl") var avatarUrl: String? = null,
    @ColumnInfo(name = "password") var password: String
)