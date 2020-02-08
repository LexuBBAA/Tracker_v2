package com.lexu.tracking.models

data class StatsInfoTaskData(
    val totalAssignedTasks: Int = 0,
    val totalClosedTasks: Int = 0,
    val totalEstimates: Double = 0.0,
    val totalLoggedTime: Double = 0.0,
    val averageLoggedTimePerDay: Double = 0.0
) {
    fun getFormattedEstimates(): String = format(totalEstimates)

    fun getFormattedLoggedTime(): String = format(totalLoggedTime)

    fun getFormattedAverageLoggedTimePerDay(): String = format(averageLoggedTimePerDay)

    private fun format(value: Double): String = when {
        value >= HOURS_IN_WEEK -> "${String.format("%.2f", value / HOURS_IN_WEEK + value % HOURS_IN_WEEK)} weeks"
        value >= HOURS_IN_DAY -> "${String.format("%.2f", value / HOURS_IN_DAY + value % HOURS_IN_DAY)} days"
        else -> "${String.format("%.2f", value)} hours"
    }

    companion object {
        private const val HOURS_IN_DAY = 24.0
        private const val HOURS_IN_WEEK = 7 * HOURS_IN_DAY
    }
}