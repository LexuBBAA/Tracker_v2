package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.room.dao.UsersDao
import com.tracker.trackerv2.datasource.providers.room.entity.UserEntity

class UsersProvider(private val dao: UsersDao): IUsersProvider {
    override fun getAll(): List<UserEntity> = dao.getAll()

    override fun getDetails(userId: String): UserEntity = dao.getDetails(userId)

    override fun getCreatedBy(createdBy: String): List<UserEntity> = dao.getCreatedBy(createdBy)

    override fun create(newUser: UserEntity) = dao.insert(newUser)

    override fun update(user: UserEntity) = dao.update(user)

    override fun delete(user: UserEntity) = dao.delete(user)
}