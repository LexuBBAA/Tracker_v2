/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.tracker.trackerv2

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FcmNotificationService: FirebaseMessagingService() {

    override fun onNewToken(token: String?) {
        Log.i(FcmNotificationService::class.simpleName, "New Token: $token")

        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.i(FcmNotificationService::class.simpleName, "New Message: ${remoteMessage?.toString()}")

        super.onMessageReceived(remoteMessage)
    }
}