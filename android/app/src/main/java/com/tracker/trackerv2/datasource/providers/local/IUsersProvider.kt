package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.UserEntity

interface IUsersProvider {
    suspend fun getAll(): List<UserEntity>
    suspend fun getDetails(userId: String): UserEntity?
    suspend fun getCreatedBy(createdBy: String): List<UserEntity>
    suspend fun create(newUser: UserEntity): UserEntity?
    suspend fun update(user: UserEntity)
    suspend fun delete(user: UserEntity)
}