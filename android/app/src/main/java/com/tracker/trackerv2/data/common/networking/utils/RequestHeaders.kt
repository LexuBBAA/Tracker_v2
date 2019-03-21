package com.tracker.trackerv2.data.common.networking.utils

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/05/2018
 */
 
class RequestHeaders(private val headers: ArrayList<Pair<String, String>>? = ArrayList()) {

    fun getHeaderPairs() : ArrayList<Pair<String, String>> = headers ?: ArrayList()

    fun addRequestHeader(key: String, value: String) : RequestHeaders {
        headers?.add(Pair(key, value))
        return this
    }

    companion object {

        fun buildRequestHeaders() : RequestHeaders = RequestHeaders()

    }

}