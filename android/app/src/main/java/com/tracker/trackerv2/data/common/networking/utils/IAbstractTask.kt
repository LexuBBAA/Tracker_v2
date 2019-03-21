package com.tracker.trackerv2.data.common.networking.utils

import com.tracker.trackerv2.data.common.networking.IServerConnectionManager
import com.tracker.trackerv2.data.common.networking.RemoteApi
import com.tracker.trackerv2.data.common.networking.ServerConnectionManager
import com.tracker.trackerv2.data.common.networking.ServerStatus
import com.tracker.trackerv2.data.room.entities.fromJson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/07/2018
 */

interface IAbstractTask<T : BaseResponse<T>, C : IAbstractCallback<T>?> : Callback {

    var serverManager: IServerConnectionManager?
    var callback: C?

    fun registerCallback(callback: C) {
        this.serverManager = ServerConnectionManager.getInstance()
        this.callback = callback
    }

    fun unregisterCallback() {
        callback = null
        serverManager = null
    }

    fun sendRequest(route: RemoteApi, request: BaseRequest<Any>?) =
        serverManager?.buildCall(route, request ?: BaseRequest.BaseGetRequest())

    override fun onResponse(call: Call, response: Response) {
        if (response.isSuccessful) {
            response.body()
                ?.string()?.let {
                    //  The response body is not empty
                    //  Trying to parse the JSON into an object of type BaseResponse<T>
                    it.fromJson<BaseResponse<T>>()?.let {
                        //  Parsed BaseResponse<T> != null
                            baseResponse ->
                        callback?.onResponse(response = baseResponse.getData())
                    } ?: run {
                        //  Could not parse JSON to BaseResponse<T>
                        callback?.onFailure(error = BaseResponse.PARSE_ERROR_RESPONSE)
                    }
                } ?: run {
                //  The response body is empty; no data to parse
                callback?.onFailure(error = BaseResponse.EMPTY_SERVER_RESPONSE)
            }
        } else {
            //  TODO - 12/7/2018: Maybe another issue occurred? Check it out.
            callback?.onNoNetworkConnection()
        }
    }

    override fun onFailure(call: Call, e: IOException) {
        callback?.onFailure(
            error = BaseErrorResponse(
                errorMessage = e.localizedMessage,
                status = ServerStatus.RESULT_UNKNOWN_ERROR
            )
        )
    }

}