package com.tracker.trackerv2.data.common.requests

import com.tracker.trackerv2.data.common.networking.utils.BaseRequest

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/06/2018
 */
 
class AuthLoginRequest(userName: String, password: String) : BaseRequest<AuthLoginRequest.Payload>(Payload(userName, password)) {

    class Payload(val userName: String, val password: String) : Any()

}