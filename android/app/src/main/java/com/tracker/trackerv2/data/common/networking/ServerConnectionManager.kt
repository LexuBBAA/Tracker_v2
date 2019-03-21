package com.tracker.trackerv2.data.common.networking

import com.tracker.trackerv2.data.common.networking.cache.CacheDataManager
import com.tracker.trackerv2.data.common.networking.cache.ICacheDataManager
import com.tracker.trackerv2.data.common.networking.utils.BaseRequest
import okhttp3.Call
import okhttp3.OkHttpClient

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/04/2018
 */
 
class ServerConnectionManager(
    private val okHttpClient: OkHttpClient = OkHttpClient()
): IServerConnectionManager {

    private val cacheDataManager : ICacheDataManager = CacheDataManager.getInstance()

    companion object {

        private var INSTANCE : ServerConnectionManager? = null

        fun getInstance() : ServerConnectionManager {
            return INSTANCE ?: synchronized(this) {
                ServerConnectionManager()
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }

    override fun buildCall(route: RemoteApi, request: BaseRequest<Any>): Call
            = okHttpClient.newCall(request.toHttpRequest("${RemoteApi.BASE_URL}${route.endpoint}"))

}