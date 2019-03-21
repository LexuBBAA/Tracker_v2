package com.tracker.trackerv2.data.room.repositories

import android.arch.lifecycle.LiveData
import com.tracker.trackerv2.data.room.dao.UserAccountDao
import com.tracker.trackerv2.data.room.entities.UserAccount

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/04/2018
 */

class UserAccountsRepository(private val userAccountDao: UserAccountDao) : IUserAccountsRepository {

    override fun get(id: Long)
            : LiveData<UserAccount> = userAccountDao.get(id)

    override fun getAll() : LiveData<List<UserAccount>> = userAccountDao.getAll()

    override suspend fun insert(data: UserAccount)
            : LiveData<UserAccount> {
        val userId: Long = userAccountDao.insert(data)
        return userAccountDao.get(userId)
    }

    override suspend fun update(data: UserAccount)
            : LiveData<UserAccount> {
        val userId: Long? = data.id
        userAccountDao.update(data)

        return userAccountDao.get(userId ?: -1)
    }

    override suspend fun delete(id: Long) = userAccountDao.delete(id)

    override suspend fun deleteAll() = userAccountDao.deleteAll()

}