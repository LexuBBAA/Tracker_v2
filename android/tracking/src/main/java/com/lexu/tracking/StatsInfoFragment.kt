package com.lexu.tracking

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.lexu.tracking.models.StatsInfoTaskData

class StatsInfoFragment: Fragment() {
    private lateinit var rootView: View

    private lateinit var statsInfoTotalAssignedTasksValue: TextView
    private lateinit var statsInfoClosedTasksValue: TextView
    private lateinit var statsInfoTotalEstimatesValue: TextView
    private lateinit var statsInfoTotalLoggedTimeValue: TextView
    private lateinit var statsInfoAverageLoggedPerDayValue: TextView

    private lateinit var statsInfoLoadingContainer: FrameLayout
    private lateinit var statsInfoProgressBar: ProgressBar
    private lateinit var statsInfoErrorMessage: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_stats_info, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statsInfoTotalAssignedTasksValue = rootView.findViewById(R.id.statsInfoTotalAssignedTasksValue)
        statsInfoClosedTasksValue = rootView.findViewById(R.id.statsInfoClosedTasksValue)
        statsInfoTotalEstimatesValue = rootView.findViewById(R.id.statsInfoTotalEstimatesValue)
        statsInfoTotalLoggedTimeValue = rootView.findViewById(R.id.statsInfoTotalLoggedTimeValue)
        statsInfoAverageLoggedPerDayValue = rootView.findViewById(R.id.statsInfoAverageLoggedPerDayValue)

        statsInfoLoadingContainer = rootView.findViewById(R.id.statsInfoLoadingContainer)
        statsInfoProgressBar = rootView.findViewById(R.id.statsInfoProgressBar)
        statsInfoErrorMessage = rootView.findViewById(R.id.statsInfoErrorMessage)

        configView()
    }

    private fun configView() {
        updateUI()
    }

    fun updateUI(taskData: StatsInfoTaskData? = null, dismissLoading: Boolean = false) {
        if (dismissLoading) {
            dismissLoading()
        }

        taskData?.apply {
            statsInfoTotalAssignedTasksValue.text = totalAssignedTasks.toString()
            statsInfoClosedTasksValue.text = totalClosedTasks.toString()
            statsInfoTotalEstimatesValue.text = getFormattedEstimates()
            statsInfoTotalLoggedTimeValue.text = getFormattedLoggedTime()
            statsInfoAverageLoggedPerDayValue.text = getFormattedAverageLoggedTimePerDay()
        }?.also {
            statsInfoAverageLoggedPerDayValue.setTextColor(Color.parseColor(
                if(it.averageLoggedTimePerDay > 8 || it.averageLoggedTimePerDay < 4) "#DC0803"
                else "#FFFFFF"
            ))
        }
    }

    private fun dismissLoading() {
        statsInfoLoadingContainer.visibility = View.GONE
    }
}