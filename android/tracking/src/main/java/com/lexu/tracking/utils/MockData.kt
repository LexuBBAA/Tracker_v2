/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.tracking.utils

import java.util.*

object MockData {
    private val currentDate = Calendar.getInstance()[Calendar.DAY_OF_WEEK]

    fun lastWeekTimes() = arrayListOf(
        DayLog(Calendar.SUNDAY, 0F),
        DayLog(Calendar.MONDAY, 8F),
        DayLog(Calendar.TUESDAY, 5.7F),
        DayLog(Calendar.WEDNESDAY, 4.9F),
        DayLog(Calendar.THURSDAY, 7.6F),
        DayLog(Calendar.FRIDAY, 6.35F),
        DayLog(Calendar.SATURDAY, 0F)
    )
}

data class DayLog(val day: Int, val loggedTime: Float)