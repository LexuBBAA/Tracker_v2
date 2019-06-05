/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.tracker.trackerv2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lexu.tracking.PersonalStatsFragment
import com.lexu.tracking.TeamStatsFragment
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.coroutines.*

class DashboardActivity : AppCompatActivity() {

    private val mockDataParser = MockDataParser(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        CoroutineScope(Dispatchers.IO).async {
            val stats = mockDataParser.getPersonalWeekStats()
            delay(1500)

            runBlocking {
                runOnUiThread {
                    (dashboardPersonalStatsFragment as PersonalStatsFragment).updateStats(stats)
                }
            }
        }

        CoroutineScope(Dispatchers.IO).async {
            val stats = mockDataParser.getTeamWeekStats()
            delay(2000)

            runBlocking {
                runOnUiThread {
                    (dashboardTeamStatsFragment as TeamStatsFragment).updateStats(stats)
                }
            }
        }
    }
}
