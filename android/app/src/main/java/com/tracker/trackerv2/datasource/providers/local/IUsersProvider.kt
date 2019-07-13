package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.UserEntity

interface IUsersProvider {
    fun getAll(): List<UserEntity>
    fun getDetails(userId: String): UserEntity?
    fun getCreatedBy(createdBy: String): List<UserEntity>
    fun create(newUser: UserEntity): UserEntity?
    fun update(user: UserEntity)
    fun delete(user: UserEntity)
}