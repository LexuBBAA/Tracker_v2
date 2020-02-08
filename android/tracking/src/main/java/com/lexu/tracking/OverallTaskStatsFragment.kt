/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.tracking

import android.os.Bundle
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

class OverallTaskStatsFragment : Fragment(), PersonalStatsContract.PersonalStatsView, ITrackingTask {

    private lateinit var rootView: View

    private lateinit var titleView: TextView
    private lateinit var statsChartView: BarChart
    private lateinit var loadingContainer: FrameLayout
    private lateinit var loadingView: ProgressBar
    private lateinit var errorMessageLabel: TextView

    private var delegate: PersonalStatsContract.PersonalStatsDelegate? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_overall_task_effort, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleView = rootView.findViewById(R.id.taskOverallEffortCardTitle) as TextView
        statsChartView = rootView.findViewById(R.id.taskOverallEffortChartView) as BarChart
        loadingContainer = rootView.findViewById(R.id.taskOverallEffortLoadingContainer) as FrameLayout
        loadingView = rootView.findViewById(R.id.taskOverallEffortProgressBar) as ProgressBar
        errorMessageLabel = rootView.findViewById(R.id.taskOverallEffortErrorMessage) as TextView

        loadingContainer.visibility = View.VISIBLE
        loadingView.visibility = View.VISIBLE
        errorMessageLabel.visibility = View.GONE

        configStatsView()

        val tasks = emptyList<DayLog>()
        val barDataSet = BarDataSet(generateEntries(tasks), "")

        updateUI(barDataSet, false)
    }

    private fun dismissLoading() {
        loadingContainer.visibility = View.GONE
    }

    private fun showError() {
        loadingContainer.visibility = View.VISIBLE
        loadingView.visibility = View.GONE
        errorMessageLabel.visibility = View.VISIBLE
    }

    private fun configStatsView() {
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

        statsChartView.setOnClickListener { delegate?.onNavigateToUserDetails() }

        statsChartView.axisLeft.setDrawZeroLine(false)
        statsChartView.axisLeft.granularity = 3F
        statsChartView.axisRight.isEnabled = false
    }

    private fun generateEntries(tasks: List<DayLog>): List<BarEntry> {
        val entries = ArrayList<BarEntry>()
        tasks.forEach { task ->
            entries.add(BarEntry(task.day.toFloat(), task.loggedTime.toFloat()))
        }

        return entries
    }

    private fun updateUI(barDataSet: BarDataSet, dismissLoading: Boolean = true) {
        barDataSet.valueTextSize = 18F
        barDataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String =
                if (value != 0F) "${value.toInt()}"
                else ""
        }

        configStatsView()

        if (dismissLoading) {
            dismissLoading()
            if (barDataSet.entryCount == 0) showError()
        }

        statsChartView.data = BarData(barDataSet)
        statsChartView.invalidate()
    }

    fun setTitle(title: String) { this.titleView.text = title }

    override fun updateStats(stats: List<DayLog>) {
        val newEntries = generateEntries(stats)
        updateUI(BarDataSet(newEntries, ""))
    }

    override fun registerDelegate(delegate: PersonalStatsContract.PersonalStatsDelegate) {
        this.delegate = delegate
    }

    override fun unregisterDelegate() {
        this.delegate = null
    }

    override fun setLoading() {
        loadingContainer.visibility = View.VISIBLE
        loadingView.visibility = View.VISIBLE
        errorMessageLabel.visibility = View.GONE
    }
}