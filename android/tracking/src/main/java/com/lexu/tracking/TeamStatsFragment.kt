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
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.lexu.models.Status
import com.lexu.models.Type
import com.lexu.tracking.delegates.TeamStatsContract
import com.lexu.tracking.models.TeamTask

class TeamStatsFragment: Fragment(), TeamStatsContract.TeamStatsView, ITrackingTask {

    private lateinit var rootView: View

    private lateinit var statsChartView: PieChart
    private lateinit var loadingContainer: FrameLayout
    private lateinit var loadingView: ProgressBar
    private lateinit var errorMessageLabel: TextView

    private var delegate: TeamStatsContract.TeamStatsDelegate? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_team_stats, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statsChartView = rootView.findViewById(R.id.teamStatsChartView) as PieChart
        loadingContainer = rootView.findViewById(R.id.teamStatsLoadingContainer) as FrameLayout
        loadingView = rootView.findViewById(R.id.teamStatsProgressBar) as ProgressBar
        errorMessageLabel = rootView.findViewById(R.id.teamStatsErrorMessage) as TextView

        loadingContainer.visibility = View.VISIBLE
        loadingView.visibility = View.VISIBLE
        errorMessageLabel.visibility = View.GONE

        configStatsView()

        val tasks = emptyList<TeamTask>()
        val pieDataSet = PieDataSet(generateEntries(tasks), "")

        updateUI(pieDataSet, false)
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
        statsChartView.setTouchEnabled(false)
        statsChartView.setOnClickListener { delegate?.onCategorySelected(Type.TASK) }
    }

    private fun generateEntries(tasks: List<TeamTask>): List<PieEntry> {
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

        return arrayListOf(
            PieEntry(openTasks.toFloat(), if(openTasks != 0) "Open" else ""),
            PieEntry(onHold.toFloat(), if(onHold != 0) "On Hold" else ""),
            PieEntry(inProgress.toFloat(), if(inProgress != 0) "In Progress" else ""),
            PieEntry(done.toFloat(), if(done != 0) "Done" else ""),
            PieEntry(reopened.toFloat(), if(reopened != 0) "Reopened" else "")
        )
    }

    private fun updateUI(pieDataSet: PieDataSet, dismissLoading: Boolean = true) {
        pieDataSet.valueTextSize = 18F
        pieDataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String =
                if (value.toInt() != 0) value.toInt().toString()
                else ""
        }

        setDataSetColors(pieDataSet)

        if(dismissLoading) {
            dismissLoading()
            if(pieDataSet.entryCount == 0) showError()
        }

        statsChartView.data = PieData(pieDataSet)
        statsChartView.invalidate()
    }

    private fun setDataSetColors(dataSet: PieDataSet) = context?.let {
        dataSet.valueTextColor = ResourcesCompat.getColor(it.resources, android.R.color.white, it.theme)
        dataSet.colors = arrayListOf(
            ResourcesCompat.getColor(it.resources, R.color.open_type_color, it.theme),
            ResourcesCompat.getColor(it.resources, R.color.on_hold_type_color, it.theme),
            ResourcesCompat.getColor(it.resources, R.color.in_progress_type_color, it.theme),
            ResourcesCompat.getColor(it.resources, R.color.done_type_color, it.theme),
            ResourcesCompat.getColor(it.resources, R.color.reopened_type_color, it.theme)
        )
    }

    override fun updateStats(stats: List<TeamTask>) {
        val newEntries = generateEntries(stats)
        updateUI(PieDataSet(newEntries, ""))
    }

    override fun registerDelegate(delegate: TeamStatsContract.TeamStatsDelegate) {
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