package com.tracker.trackerv2.data.viewmodels

import android.arch.lifecycle.LiveData
import kotlinx.coroutines.Job

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/06/2018
 */
 
interface IAbstractViewModel<T> {

    fun getAll() : LiveData<List<T>>

    fun get(id: Long) : LiveData<T>

    fun insert(data: T) : Job

    fun insert(data: List<T>) : Job

    fun update(data: T) : Job

    fun update(data: List<T>) : Job

    fun delete(id: Long) : Job

    fun deleteAll() : Job

}