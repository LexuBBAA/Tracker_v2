package com.lexu.tracking.models

import com.lexu.tracking.utils.DayLog
import java.util.Calendar

data class DashboardMembersProgressItem(
    val userId: String,
    val username: String,
    val userIconUrl: String? = null,
    val loggedTime: DayLog = DayLog(
        Calendar.getInstance()[Calendar.DAY_OF_WEEK],
        0.0
    )
)