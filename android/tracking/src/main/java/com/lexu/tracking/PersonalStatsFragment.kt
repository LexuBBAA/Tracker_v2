/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.tracking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.lexu.tracking.utils.MockData
import java.util.*
import kotlin.collections.ArrayList

class PersonalStatsFragment: Fragment() {

    private lateinit var rootView: View

    private lateinit var statsChartView: BarChart

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_personal_stats, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statsChartView = rootView.findViewById(R.id.personalStatsChartView) as BarChart

        configChart()

        val entryValues = MockData.lastWeekTimes()
        val currentDay = Calendar.getInstance()[Calendar.DAY_OF_WEEK]
        val entries = ArrayList<BarEntry>()
        entryValues.forEach { dayLog ->
            Log.e(PersonalStatsFragment::class.simpleName, "$currentDay ? ${dayLog.day}")
            entries.add(BarEntry(
                dayLog.day.toFloat(),
                if(currentDay >= dayLog.day) dayLog.loggedTime
                else 0F
            ))
        }

        val barDataSet = BarDataSet(entries, "")
        context?.let {
            barDataSet.valueTextColor = ResourcesCompat.getColor(it.resources, android.R.color.white, it.theme)
        }

        barDataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String = if(value != 0F) value.toString()
            else ""
        }
        statsChartView.data = BarData(barDataSet)
        statsChartView.invalidate()
    }

    private fun configChart() {
        statsChartView.description.isEnabled = false
        statsChartView.setDrawValueAboveBar(true)
        statsChartView.legend.isEnabled = false

        val xAxis = statsChartView.xAxis
        xAxis.valueFormatter = object: ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return when(value.toInt()) {
                    Calendar.SUNDAY -> "Sun."
                    Calendar.MONDAY -> "Mon."
                    Calendar.TUESDAY -> "Tue."
                    Calendar.WEDNESDAY -> "Wed."
                    Calendar.THURSDAY -> "Thu."
                    Calendar.FRIDAY -> "Fri."
                    Calendar.SATURDAY -> "Sat."
                    else -> "N/A"
                }
            }
        }
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        context?.let {
            val accentColor = ResourcesCompat.getColor(it.resources, R.color.color_accent, it.theme)
            xAxis.textColor = accentColor
            statsChartView.axisLeft.textColor = accentColor
        }

        statsChartView.axisLeft.setDrawZeroLine(false)
        statsChartView.axisLeft.granularity = 3F
        statsChartView.axisLeft.axisMaximum = 9F
        statsChartView.axisRight.isEnabled = false
    }
}