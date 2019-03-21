package com.tracker.trackerv2.data.room.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.tracker.trackerv2.data.room.entities.UserPosition

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/04/2018
 */

@Dao
interface UserPositionDao {

    @Query("SELECT * FROM UserPosition")
    fun getAll(): LiveData<List<UserPosition>>

    @Query("SELECT * FROM UserPosition WHERE id = :id")
    fun get(id: Long): LiveData<UserPosition>

    @Query("SELECT * FROM UserPosition WHERE title LIKE :query")
    fun getLike(query: String) : LiveData<List<UserPosition>>

    @Insert(onConflict = REPLACE)
    fun insert(userPosition: UserPosition) : Long

    @Transaction
    fun insertAll(userPositions: List<UserPosition>) = userPositions.forEach { insert(it) }

    @Update
    fun update(userPosition: UserPosition)

    @Transaction
    fun update(userPositions: List<UserPosition>) = userPositions.forEach { update(it) }

    @Query("DELETE FROM UserPosition WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM UserPosition")
    fun deleteAll()

}