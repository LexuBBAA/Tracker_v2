package com.tracker.trackerv2.utils

class WorklogsFormatter {
    fun format(value: Double): String = when {
        value >= HOURS_IN_WEEK -> "${String.format("%.2f", value / HOURS_IN_WEEK + value % HOURS_IN_WEEK)} weeks"
        value >= HOURS_IN_DAY -> "${String.format("%.2f", value / HOURS_IN_DAY + value % HOURS_IN_DAY)} days"
        else -> "${String.format("%.2f", value)} hours"
    }

    companion object {
        private const val HOURS_IN_DAY = 24.0
        private const val HOURS_IN_WEEK = 7 * HOURS_IN_DAY
    }
}