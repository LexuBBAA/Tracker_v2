package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.dao.UsersDao
import com.tracker.trackerv2.datasource.providers.local.room.entity.UserEntity

class UsersProvider(private val dao: UsersDao): IUsersProvider {
    override suspend fun getAll(): List<UserEntity> = dao.getAll()

    override suspend fun getDetails(userId: String): UserEntity? = dao.getDetails(userId)

    override suspend fun getCreatedBy(createdBy: String): List<UserEntity> = dao.getCreatedBy(createdBy)

    override suspend fun create(newUser: UserEntity): UserEntity? = dao.getDetails(dao.insert(newUser))

    override suspend fun update(user: UserEntity) = dao.update(user)

    override suspend fun delete(user: UserEntity) = dao.delete(user)
}