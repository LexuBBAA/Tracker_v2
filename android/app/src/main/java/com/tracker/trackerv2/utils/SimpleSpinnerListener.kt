package com.tracker.trackerv2.utils

import android.widget.AdapterView

interface SimpleSpinnerListener : AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
        //  not used
    }
}