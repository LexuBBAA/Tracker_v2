package com.tracker.trackerv2

import android.app.Application
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId

class TrackerV2Application: Application() {

    private var deviceId: String? = null

    override fun onCreate() {
        super.onCreate()

        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { task ->
            if(!task.isSuccessful) {
                Log.e(TrackerV2Application::class.java.simpleName, "FCM failed to generate deviceId", task.exception)
                return@addOnCompleteListener
            }

            deviceId = task.result?.token
            Log.e(TrackerV2Application::class.java.simpleName, ">>> DeviceId: ")
            Log.e(TrackerV2Application::class.java.simpleName, deviceId ?: "null")
        }
    }

    fun getDeviceId(): String? = deviceId
}