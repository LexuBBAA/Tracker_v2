package com.tracker.trackerv2.utils

import android.text.TextWatcher

interface SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //  not used
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        //  not used
    }
}