package com.tracker.trackerv2.data.common.networking.utils

import com.tracker.trackerv2.data.room.entities.toJson
import okhttp3.MultipartBody
import okhttp3.Request

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/05/2018
 */

abstract class BaseRequest<T : Any>(
    payload: T? = null, private val headers: RequestHeaders? = null
) {

    private val requestBody = payload?.let {
        MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("payload", it.toJson())
            .build()
    }

    private fun getRequestHeaders(): RequestHeaders = headers ?: RequestHeaders.buildRequestHeaders()

    fun toHttpRequest(url: String): Request = requestBody?.let {
        Request.Builder()
            .url(url)
            .fromHeaderPairs(getRequestHeaders().getHeaderPairs())
            .post(it)
            .build()
    } ?: Request.Builder()
        .url(url)
        .fromHeaderPairs(getRequestHeaders().getHeaderPairs())
        .get()
        .build()

    class BaseGetRequest: BaseRequest<Any>()

}

fun Request.Builder.fromHeaderPairs(headerPairs: ArrayList<Pair<String, String>>? = ArrayList()): Request.Builder {
    headerPairs?.forEach { addHeader(it.first, it.second) }
    return this
}