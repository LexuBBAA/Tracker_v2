package com.tracker.trackerv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SprintDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sprint_details)
    }

    companion object {
        const val KEY_SPRINT_ID_EXTRA = "sprint_id_extra"
    }
}
