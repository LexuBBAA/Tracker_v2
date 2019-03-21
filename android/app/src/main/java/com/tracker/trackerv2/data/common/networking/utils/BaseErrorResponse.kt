package com.tracker.trackerv2.data.common.networking.utils

import com.tracker.trackerv2.data.common.networking.ServerStatus

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/07/2018
 */
 
open class BaseErrorResponse(
    errorMessage: String = UNKNOWN_ERROR_MESSAGE, status: ServerStatus
): BaseResponse<String>(data = errorMessage, status = status) {

    companion object {
        const val UNKNOWN_ERROR_MESSAGE = "An unknown error has occurred. Please try again later."
    }

}