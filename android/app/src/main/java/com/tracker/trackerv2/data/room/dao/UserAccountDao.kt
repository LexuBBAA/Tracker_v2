package com.tracker.trackerv2.data.room.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.tracker.trackerv2.data.room.entities.UserAccount

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/04/2018
 */
 
@Dao
interface UserAccountDao {

    @Query("SELECT * FROM userAccount")
    fun getAll() : LiveData<List<UserAccount>>

    @Query("SELECT * FROM userAccount WHERE id = :id")
    fun get(id: Long) : LiveData<UserAccount>

    @Insert(onConflict = REPLACE)
    fun insert(userAccount: UserAccount) : Long

    @Transaction
    fun insert(userAccounts: List<UserAccount>) = userAccounts.forEach { insert(it) }

    @Update
    fun update(userAccount: UserAccount)

    @Transaction
    fun update(userAccounts: List<UserAccount>) = userAccounts.forEach { update(it) }

    @Query("DELETE FROM userAccount WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM userAccount")
    fun deleteAll()

}