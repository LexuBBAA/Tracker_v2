package com.tracker.trackerv2.data.common.networking.utils

import com.google.gson.annotations.SerializedName
import com.tracker.trackerv2.data.common.networking.ServerStatus

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/04/2018
 */

abstract class BaseResponse<T: Any> (
    @SerializedName("data") private val data: T,
    @SerializedName("status") private val status: ServerStatus
) {

    fun getData() : T {
        return data
    }

    fun getStatus() : ServerStatus {
        return status
    }

    companion object {
        val EMPTY_SERVER_RESPONSE = BaseErrorResponse("Empty Response", ServerStatus.RESULT_UNKNOWN_ERROR)
        val PARSE_ERROR_RESPONSE = BaseErrorResponse("Parsing Error", ServerStatus.RESULT_PARSING_ERROR)
    }

}