package com.tracker.trackerv2.data.room.repositories

import android.arch.lifecycle.LiveData
import com.tracker.trackerv2.data.room.dao.UserPositionDao
import com.tracker.trackerv2.data.room.entities.UserPosition

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/04/2018
 */

class UserPositionsRepository(private val userPositionDao: UserPositionDao) : IUserPositionsRepository {

    override fun get(id: Long): LiveData<UserPosition> = userPositionDao.get(id)

    override fun getAll(): LiveData<List<UserPosition>> = userPositionDao.getAll()

    override fun getLike(query: String): LiveData<List<UserPosition>> = userPositionDao.getLike(query)

    override suspend fun insert(data: UserPosition): LiveData<UserPosition> {
        val posId: Long = userPositionDao.insert(data)
        return get(posId)
    }

    override suspend fun update(data: UserPosition): LiveData<UserPosition> {
        val posId: Long? = data.id
        userPositionDao.update(data)

        return get(posId ?: -1)
    }

    override suspend fun delete(id: Long) = userPositionDao.delete(id)

    override suspend fun deleteAll() = userPositionDao.deleteAll()

}