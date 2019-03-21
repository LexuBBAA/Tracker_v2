package com.tracker.trackerv2.data.common.networking

import com.tracker.trackerv2.data.common.networking.utils.BaseRequest
import okhttp3.Call

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/07/2018
 */
 
interface IServerConnectionManager {

    fun buildCall(route: RemoteApi, request: BaseRequest<Any>): Call

}