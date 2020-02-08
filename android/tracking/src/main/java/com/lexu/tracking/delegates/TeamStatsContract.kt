/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.tracking.delegates

import com.lexu.models.Type
import com.lexu.tracking.models.TeamTask

interface TeamStatsContract {

    interface TeamStatsView {
        fun registerDelegate(delegate: TeamStatsDelegate)
        fun unregisterDelegate()

        fun updateStats(stats: List<TeamTask>)
    }

    interface TeamStatsDelegate {
        fun onCategorySelected(taskType: Type)
    }

}