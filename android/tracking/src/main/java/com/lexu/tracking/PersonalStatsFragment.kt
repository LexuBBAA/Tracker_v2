/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.tracking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.lexu.tracking.delegates.PersonalStatsContract
import com.lexu.tracking.utils.DayLog
import java.util.*
import kotlin.collections.ArrayList

class PersonalStatsFragment : Fragment(), PersonalStatsContract.PersonalStatsView {
    private lateinit var rootView: View

    private lateinit var statsChartView: BarChart
    private lateinit var loadingContainer: FrameLayout
    private lateinit var loadingView: ProgressBar
    private lateinit var errorMessageLabel: TextView

    private var delegate: PersonalStatsContract.PersonalStatsDelegate? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_personal_stats, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statsChartView = rootView.findViewById(R.id.personalStatsChartView) as BarChart
        loadingContainer = rootView.findViewById(R.id.personalStatsLoadingContainer) as FrameLayout
        loadingView = rootView.findViewById(R.id.personalStatsProgressBar) as ProgressBar
        errorMessageLabel = rootView.findViewById(R.id.personalStatsErrorMessage) as TextView

        loadingContainer.visibility = View.VISIBLE
        loadingView.visibility = View.VISIBLE
        errorMessageLabel.visibility = View.GONE

        configChart()

        val entryValues = emptyList<DayLog>()
        val barDataSet = BarDataSet(generateEntries(entryValues), "")

        updateUI(barDataSet, false)
    }

    private fun configChart() {
        statsChartView.isClickable = false
        statsChartView.isFocusable = false
        statsChartView.description.isEnabled = false
        statsChartView.setDrawValueAboveBar(false)
        statsChartView.legend.isEnabled = false

        val xAxis = statsChartView.xAxis
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return when (value.toInt()) {
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

        statsChartView.setOnClickListener { delegate?.onNavigateToWorklogList() }

        statsChartView.axisLeft.setDrawZeroLine(false)
        statsChartView.axisLeft.granularity = 3F
        statsChartView.axisRight.isEnabled = false
    }

    private fun updateUI(dataSet: BarDataSet, dismissLoading: Boolean = true) {
        dataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String = if (value != 0F) value.toString()
            else ""
        }

        setDataSetColors(dataSet)

        if (dismissLoading) {
            dismissLoading()
            if (dataSet.entryCountStacks == 0) showError()
        }

        statsChartView.data = BarData(dataSet)
        statsChartView.invalidate()
    }

    private fun dismissLoading() {
        loadingContainer.visibility = View.GONE
    }

    private fun showError() {
        loadingContainer.visibility = View.VISIBLE
        loadingView.visibility = View.GONE
        errorMessageLabel.visibility = View.VISIBLE
    }

    private fun generateEntries(dailyWorklogs: List<DayLog>): List<BarEntry> {
        val currentDay = Calendar.getInstance()[Calendar.DAY_OF_WEEK]
        val entries = ArrayList<BarEntry>()
        dailyWorklogs.forEach { dayLog ->
            Log.e(PersonalStatsFragment::class.simpleName, "$currentDay ? ${dayLog.day}")
            entries.add(
                BarEntry(
                    dayLog.day.toFloat(),
                    if (currentDay >= dayLog.day) dayLog.loggedTime.toFloat()
                    else 0F
                )
            )
        }

        return entries
    }

    private fun setDataSetColors(dataSet: BarDataSet) = context?.let {
        dataSet.valueTextColor = ResourcesCompat.getColor(it.resources, android.R.color.white, it.theme)
    }

    override fun registerDelegate(delegate: PersonalStatsContract.PersonalStatsDelegate) {
        this.delegate = delegate
    }

    override fun unregisterDelegate() {
        this.delegate = null
    }

    override fun updateStats(stats: List<DayLog>) {
        val entries = generateEntries(stats)
        updateUI(BarDataSet(entries, ""))
    }
}