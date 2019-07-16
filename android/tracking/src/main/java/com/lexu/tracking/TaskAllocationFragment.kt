package com.lexu.tracking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.lexu.tracking.delegates.TaskAllocationContract
import com.lexu.tracking.models.TaskAllocationDataModel

class TaskAllocationFragment : Fragment(), TaskAllocationContract.TaskAllocationView {
    private lateinit var root : View

    private lateinit var title : TextView
    private lateinit var chart : BarChart
    private lateinit var loadingView : FrameLayout

    private var delegate: TaskAllocationContract.TaskAllocationDelegate? = null

    private var chartValues : List<TaskAllocationDataModel> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_task_allocation, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = root.findViewById(R.id.title)
        chart = root.findViewById(R.id.taskAllocationChart)
        loadingView = root.findViewById(R.id.loadingView)

        chart.visibility = View.GONE
        loadingView.visibility = View.VISIBLE

        chart.isDoubleTapToZoomEnabled = false
        chart.setPinchZoom(false)
        chart.isClickable = false
        chart.isFocusable = false
        chart.legend.isEnabled = false
        chart.description.isEnabled = false

        val xAxis = chart.xAxis
        xAxis.labelRotationAngle = -45F
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String = chartValues[value.toInt()].username
        }

        context?.let {
            val accentColor = ResourcesCompat.getColor(it.resources, R.color.color_accent, it.theme)
            xAxis.textColor = accentColor
            chart.axisLeft.textColor = accentColor
            chart.axisRight.textColor = accentColor
        }

        chart.axisLeft.granularity = 1F
        chart.axisRight.granularity = 1F
        chart.axisLeft.mAxisMinimum = 0F
        chart.setFitBars(true)

        val barDataSet = BarDataSet(generateValues(), "")
        updateUI(barDataSet, false)
    }

    private fun generateValues(): List<BarEntry> = chartValues
        .mapIndexed { index, taskAllocationDataModel ->
            BarEntry(index.toFloat(), taskAllocationDataModel.tasks.size.toFloat())
        }

    private fun updateUI(dataSet: BarDataSet, dismissLoading: Boolean = true) {
        setDataColors(dataSet)

        dataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String = value.toInt().toString()
        }

        if(dismissLoading) dismissLoading()

        chart.data = BarData(dataSet)
        chart.invalidate()
    }

    private fun setDataColors(dataSet: BarDataSet) = context?.let {
        context?.let {
            dataSet.color = ResourcesCompat.getColor(it.resources, R.color.color_accent, it.theme)
        }
        dataSet.valueTextColor = ResourcesCompat.getColor(it.resources, android.R.color.white, it.theme)
    }

    private fun dismissLoading() {
        loadingView.visibility = View.GONE
        chart.visibility = View.VISIBLE
    }

    override fun registerDelegate(delegate: TaskAllocationContract.TaskAllocationDelegate) {
        this.delegate = delegate
    }

    override fun unregisterDelegate() {
        this.delegate = null
    }

    override fun loadData(chartData: List<TaskAllocationDataModel>) {
        this.chartValues = chartData.sortedBy { it.username }
        val barDataSet = BarDataSet(generateValues(), "")
        updateUI(barDataSet)
    }
}