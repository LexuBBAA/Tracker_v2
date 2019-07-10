package com.tracker.trackerv2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { task ->
            if(!task.isSuccessful) {
                Log.e(MainActivity::class.java.simpleName, "FCM failed to generate deviceId", task.exception)
                return@addOnCompleteListener
            }

            Log.e(this::class.java.simpleName, ">>> DeviceId: ")
            Log.e(this::class.java.simpleName, task.result?.token ?: "null")
        }
    }

}
