package com.tracker.trackerv2.data.room.repositories

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/06/2018
 */
 
interface IAbstractRepository<T> {

    fun get(id: Long) : LiveData<T>

    fun getAll() : LiveData<List<T>>

    @WorkerThread
    suspend fun insert(data: T) : LiveData<T>

    @WorkerThread
    suspend fun insert(data: List<T>) : LiveData<List<T>> {
        data.forEach { insert(it) }
        return getAll()
    }

    @WorkerThread
    suspend fun update(data: T) : LiveData<T>

    @WorkerThread
    suspend fun update(data: List<T>) : LiveData<List<T>> {
        data.forEach { update(it) }
        return getAll()
    }

    @WorkerThread
    suspend fun delete(id: Long)

    @WorkerThread
    suspend fun deleteAll()

}