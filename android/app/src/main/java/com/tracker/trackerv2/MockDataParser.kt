/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.tracker.trackerv2

import android.content.Context
import com.google.gson.Gson
import com.lexu.tracking.utils.DayLog
import com.lexu.tracking.utils.TeamTask
import java.io.IOException

class MockDataParser(private val context: Context) {

    fun getPersonalWeekStats(): List<DayLog> {
        val statsJson = readFromAssets("weeklyStatusMock")
        return if(statsJson.isEmpty()) emptyList()
        else Gson().fromJson(statsJson, Array<DayLog>::class.java).toList()
    }

    fun getTeamWeekStats(): List<TeamTask> {
        val statsJson = readFromAssets("weeklyTeamTaskStatusMock")
        return if(statsJson.isEmpty()) emptyList()
        else Gson().fromJson(statsJson, Array<TeamTask>::class.java).toList()
    }

    private fun readFromAssets(fileName: String): String {
        var json: String? = null
        try {
            val inputStream = context.assets.open(fileName)

            inputStream.bufferedReader().use {
                json = it.readText()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return json ?: ""
    }

}