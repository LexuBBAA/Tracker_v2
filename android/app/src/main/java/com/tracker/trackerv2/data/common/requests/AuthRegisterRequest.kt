package com.tracker.trackerv2.data.common.requests

import com.tracker.trackerv2.data.common.networking.utils.BaseRequest
import com.tracker.trackerv2.data.room.entities.UserAccount

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/06/2018
 */
 
class AuthRegisterRequest(userAccount: UserAccount) : BaseRequest<AuthRegisterRequest.Payload>(Payload(userAccount)) {

    class Payload(private val userAccount: UserAccount) : Any()

}