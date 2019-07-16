package com.tracker.trackerv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TeamDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)
    }

    companion object {
        const val KEY_TEAM_ID_EXTRA = "team_id_extra"
    }
}
