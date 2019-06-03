/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.tracking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.lexu.tracking.utils.MockData
import com.lexu.tracking.utils.Status

class TeamStatsFragment : Fragment() {

    private lateinit var rootView: View
    private lateinit var statsChartView: PieChart

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_team_stats, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statsChartView = rootView.findViewById(R.id.teamStatsChartView) as PieChart
        configStatsView()

        val tasks = MockData.lastTeamTasks()
        var openTasks = 0
        var inProgress = 0
        var onHold = 0
        var done = 0
        var reopened = 0
        tasks.forEach { teamTask ->
            when (teamTask.status) {
                Status.OPEN -> openTasks++
                Status.IN_PROGRESS -> inProgress++
                Status.ON_HOLD -> onHold++
                Status.DONE -> done++
                Status.REOPENED -> reopened++
                else -> {
                    //  do nothing
                }
            }
        }
        val entries = ArrayList<PieEntry>()
        if (openTasks != 0) entries.add(PieEntry(openTasks.toFloat(), "Open"))
        if (onHold != 0) entries.add(PieEntry(onHold.toFloat(), "On Hold"))
        if (inProgress != 0) entries.add(PieEntry(inProgress.toFloat(), "In Progress"))
        if (done != 0) entries.add(PieEntry(done.toFloat(), "Done"))
        if (reopened != 0) entries.add(PieEntry(reopened.toFloat(), "Reopened"))

        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.valueTextSize = 18F
        pieDataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String =
                if (value != 0F) "${value.toInt()}"
                else ""
        }
        context?.let {
            pieDataSet.valueTextColor = ResourcesCompat.getColor(it.resources, android.R.color.white, it.theme)
            pieDataSet.colors = arrayListOf(
                ResourcesCompat.getColor(it.resources, R.color.open_type_color, it.theme),
                ResourcesCompat.getColor(it.resources, R.color.on_hold_type_color, it.theme),
                ResourcesCompat.getColor(it.resources, R.color.in_progress_type_color, it.theme),
                ResourcesCompat.getColor(it.resources, R.color.done_type_color, it.theme),
                ResourcesCompat.getColor(it.resources, R.color.reopened_type_color, it.theme)
            )
        }
        statsChartView.data = PieData(pieDataSet)
        statsChartView.invalidate()
    }

    private fun configStatsView() {
        statsChartView.holeRadius = 30F
        statsChartView.setDrawSlicesUnderHole(false)
        statsChartView.transparentCircleRadius = 0F
        context?.let {
            statsChartView.setHoleColor(
                ResourcesCompat.getColor(
                    it.resources,
                    R.color.mtrl_btn_transparent_bg_color,
                    it.theme
                )
            )
        }
        statsChartView.description.isEnabled = false
        statsChartView.legend.isEnabled = false
    }
}