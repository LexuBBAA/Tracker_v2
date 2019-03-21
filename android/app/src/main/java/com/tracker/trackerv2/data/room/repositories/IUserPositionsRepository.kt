package com.tracker.trackerv2.data.room.repositories

import android.arch.lifecycle.LiveData
import com.tracker.trackerv2.data.room.entities.UserPosition

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/06/2018
 */
 
interface IUserPositionsRepository : IAbstractRepository<UserPosition> {

    fun getLike(query: String) : LiveData<List<UserPosition>>

}