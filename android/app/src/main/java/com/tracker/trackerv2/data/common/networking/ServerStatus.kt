package com.tracker.trackerv2.data.common.networking

import com.google.gson.annotations.SerializedName

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/05/2018
 */

enum class ServerStatus(private val statusCode: Int, private val statusMessage: String) {

    @SerializedName(KEY_RESULT_SUCCESS)
    RESULT_SUCCESS(200, "SUCCESS"),
    @SerializedName(KEY_RESULT_BAD_REQUEST)
    RESULT_BAD_REQUEST(400, "BAD REQUEST"),
    @SerializedName(KEY_RESULT_UNAUTHORIZED)
    RESULT_UNAUTHORIZED(401, "UNAUTHORIZED"),
    @SerializedName(KEY_RESULT_TIMEOUT)
    RESULT_TIMEOUT(408, "TIMEOUT"),

    RESULT_UNKNOWN_ERROR(1001, "ERROR_UNKNOWN"),
    RESULT_LOCAL_ERROR(1002, "ERROR_LOCAL"),
    RESULT_PARSING_ERROR(1004, "ERROR_PARSING_JSON"),
    RESULT_NO_NETWORK(2000, "ERROR_NO_NETWORK");

    companion object {
        const val KEY_RESULT_SUCCESS = "RESULT_SUCCESS"
        const val KEY_RESULT_BAD_REQUEST = "RESULT_BAD_REQUEST"
        const val KEY_RESULT_UNAUTHORIZED = "RESULT_UNAUTHORIZED"
        const val KEY_RESULT_TIMEOUT = "RESULT_TIMEOUT"
    }

    fun getStatusCode(): Int {
        return statusCode
    }

}