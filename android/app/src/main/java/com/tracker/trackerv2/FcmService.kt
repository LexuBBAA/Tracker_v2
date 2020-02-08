package com.tracker.trackerv2

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FcmService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage?) {
        Log.d(FcmService::class.java.simpleName, message?.toString() ?: "null")
        super.onMessageReceived(message)
    }
}