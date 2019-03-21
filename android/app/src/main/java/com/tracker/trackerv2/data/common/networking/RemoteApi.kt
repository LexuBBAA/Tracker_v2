package com.tracker.trackerv2.data.common.networking

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/04/2018
 */

enum class RemoteApi(val endpoint: String, val type: RemoteApi.RequestType) {

    LOGIN("/auth", RequestType.POST),
    REGISTER("/auth/reg", RequestType.POST),
    RESET_PASSWORD("/auth/reset", RequestType.POST),
    GET_USERS("/users", RequestType.GET);

    companion object {

        //TODO:     12/04/2018 > Change LOCAL_SERVER_IP_ADDRESS & LOCAL_SERVER_PORT_NO to
        //TODO: match the local server's host
        private val LOCAL_SERVER_IP_ADDRESS = "127.0.0.1"
        private val LOCAL_SERVER_PORT_NO = "8080"

        @JvmField
        val BASE_URL = "http://$LOCAL_SERVER_IP_ADDRESS:$LOCAL_SERVER_PORT_NO"
    }

    enum class RequestType {
        GET,
        POST
    }

}