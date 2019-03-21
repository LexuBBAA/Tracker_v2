package com.tracker.trackerv2.data.common.networking.cache

import android.support.annotation.NonNull
import android.support.annotation.Nullable
import com.tracker.trackerv2.data.common.networking.RemoteApi
import com.tracker.trackerv2.data.common.networking.utils.BaseResponse

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/06/2018
 */
 
class CacheDataManager : ICacheDataManager {

    @NonNull
    private val cacheDataMap: HashMap<RemoteApi, BaseResponse<Any>> = HashMap()

    override fun update(
        remoteApi: RemoteApi, data: BaseResponse<Any>
    ) { cacheDataMap[remoteApi] = data }

    @Nullable
    override fun get(remoteApi: RemoteApi) : BaseResponse<Any>?
            = cacheDataMap[remoteApi]

    private fun clear() = cacheDataMap.clear()

    companion object {

        private var INSTANCE : CacheDataManager? = null

        fun getInstance() : ICacheDataManager
                = INSTANCE ?: synchronized(this) { CacheDataManager() }

        fun clearCache() = INSTANCE?.clear()

    }

}