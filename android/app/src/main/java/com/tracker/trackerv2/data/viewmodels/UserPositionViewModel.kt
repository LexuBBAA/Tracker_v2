package com.tracker.trackerv2.data.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.tracker.trackerv2.data.room.database.UserPositionDatabase
import com.tracker.trackerv2.data.room.entities.UserPosition
import com.tracker.trackerv2.data.room.repositories.IUserPositionsRepository
import com.tracker.trackerv2.data.room.repositories.UserPositionsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/04/2018
 */
 
class UserPositionViewModel(application: Application)
    : AndroidViewModel(application), IUserPositionViewModel {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: IUserPositionsRepository

    init {
        val userPositionDao = UserPositionDatabase.getInstance(application)!!.userPositionDao()
        repository = UserPositionsRepository(userPositionDao)
    }

    override fun getAll(): LiveData<List<UserPosition>> = repository.getAll()

    override fun get(id: Long): LiveData<UserPosition> = repository.get(id)

    override fun getLike(query: String): LiveData<List<UserPosition>> = repository.getLike(query)

    override fun insert(data: UserPosition)
            : Job = scope.launch(Dispatchers.IO) { repository.insert(data) }

    override fun insert(data: List<UserPosition>)
            : Job = scope.launch(Dispatchers.IO) { repository.insert(data) }

    override fun update(data: UserPosition)
            : Job = scope.launch(Dispatchers.IO) { repository.update(data) }

    override fun update(data: List<UserPosition>)
            : Job = scope.launch(Dispatchers.IO) { repository.update(data) }

    override fun delete(id: Long)
            : Job = scope.launch(Dispatchers.IO) { repository.delete(id) }

    override fun deleteAll()
            : Job = scope.launch(Dispatchers.IO) { repository.deleteAll() }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}