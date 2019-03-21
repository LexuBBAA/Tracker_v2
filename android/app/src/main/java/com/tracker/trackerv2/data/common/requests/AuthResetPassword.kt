package com.tracker.trackerv2.data.common.requests

import com.tracker.trackerv2.data.common.networking.utils.BaseRequest

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/06/2018
 */
 
class AuthResetPassword(email: String) : BaseRequest<AuthResetPassword.Payload>(Payload(email)) {

    class Payload(private val email: String) : Any()

}
