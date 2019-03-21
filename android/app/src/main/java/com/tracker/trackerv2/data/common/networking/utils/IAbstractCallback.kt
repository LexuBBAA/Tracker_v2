package com.tracker.trackerv2.data.common.networking.utils

import com.tracker.trackerv2.data.common.networking.ServerStatus

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/07/2018
 */
 
interface IAbstractCallback<T> {

    fun onResponse(response: T)

    fun onFailure(error: BaseResponse<String> = BaseErrorResponse(status = ServerStatus.RESULT_UNKNOWN_ERROR))

    fun onNoNetworkConnection()

}