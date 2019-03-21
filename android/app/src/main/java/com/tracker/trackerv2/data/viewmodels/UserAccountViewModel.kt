package com.tracker.trackerv2.data.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.tracker.trackerv2.data.room.database.UserAccountDatabase
import com.tracker.trackerv2.data.room.entities.UserAccount
import com.tracker.trackerv2.data.room.repositories.IUserAccountsRepository
import com.tracker.trackerv2.data.room.repositories.UserAccountsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/04/2018
 */
 
class UserAccountViewModel(application: Application)
    : AndroidViewModel(application), IUserAccountViewModel {

    private var parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    private val repository: IUserAccountsRepository

    init {
        val userAccountDao = UserAccountDatabase.getInstance(application)!!.userAccountDataDao()
        repository = UserAccountsRepository(userAccountDao)
    }

    override fun getAll(): LiveData<List<UserAccount>> = repository.getAll()

    override fun get(id: Long): LiveData<UserAccount> = repository.get(id)

    override fun insert(data: UserAccount)
            : Job = scope.launch(Dispatchers.IO) { repository.insert(data) }

    override fun insert(data: List<UserAccount>)
            : Job = scope.launch(Dispatchers.IO) { repository.insert(data) }

    override fun update(data: UserAccount)
            : Job = scope.launch(Dispatchers.IO) { repository.update(data) }

    override fun update(data: List<UserAccount>)
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
