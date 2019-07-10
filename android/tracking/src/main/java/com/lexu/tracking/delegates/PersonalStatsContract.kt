/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.tracking.delegates

import com.lexu.tracking.utils.DayLog

interface PersonalStatsContract {

    interface PersonalStatsView {
        fun registerDelegate(delegate: PersonalStatsDelegate)
        fun unregisterDelegate()

        fun updateStats(stats: List<DayLog>)
    }

    interface PersonalStatsDelegate {
        fun onWorklogSelected(worklog: DayLog)
        fun onNavigateToWorklogList()
    }

}