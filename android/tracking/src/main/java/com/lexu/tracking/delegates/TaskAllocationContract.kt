package com.lexu.tracking.delegates

import com.lexu.tracking.models.TaskAllocationDataModel

interface TaskAllocationContract {
    interface TaskAllocationDelegate {
        fun onItemSelected(item: TaskAllocationDataModel)

        fun onItemDeselected(item: TaskAllocationDataModel)
    }

    interface TaskAllocationView {
        fun registerDelegate(delegate: TaskAllocationDelegate)
        fun unregisterDelegate()

        fun loadData(chartData: List<TaskAllocationDataModel>)
    }
}