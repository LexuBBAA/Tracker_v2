package com.tracker.trackerv2.data.viewmodels

import android.arch.lifecycle.LiveData
import com.tracker.trackerv2.data.room.entities.UserPosition
import kotlinx.coroutines.Job

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/06/2018
 */
 
interface IUserPositionViewModel : IAbstractViewModel<UserPosition> {

    fun getLike(query: String) : LiveData<List<UserPosition>>

}