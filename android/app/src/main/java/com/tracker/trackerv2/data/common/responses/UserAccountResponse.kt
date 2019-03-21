package com.tracker.trackerv2.data.common.responses

import com.tracker.trackerv2.data.common.networking.ServerStatus
import com.tracker.trackerv2.data.common.networking.utils.BaseResponse
import com.tracker.trackerv2.data.room.entities.UserAccount

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/04/2018
 */
 
class UserAccountResponse(data: UserAccount, status: ServerStatus) : BaseResponse<UserAccount>(data, status)