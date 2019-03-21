package com.tracker.trackerv2.data.common.networking.cache

import android.support.annotation.Nullable
import com.tracker.trackerv2.data.common.networking.RemoteApi
import com.tracker.trackerv2.data.common.networking.utils.BaseResponse

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/06/2018
 */
 
interface ICacheDataManager {

    fun update(remoteApi: RemoteApi, data: BaseResponse<Any>)

    @Nullable
    fun get(remoteApi: RemoteApi) : BaseResponse<Any>?

}