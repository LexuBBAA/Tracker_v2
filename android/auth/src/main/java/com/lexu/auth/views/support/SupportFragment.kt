/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.auth.views.support

import android.view.View
import com.lexu.auth.delegates.NavigationDelegate

open class SupportFragment<AbstractPresenter : NavigationDelegate.NavigationPresenter>(
    presenter: AbstractPresenter
) :
    AbstractFragment<AbstractPresenter>() {
    lateinit var rootView: View

    init {
        this.presenter = presenter
    }

    override fun onResumeView() {
        //  implement if state change needs to be handled
    }

    override fun onPauseView() {
        //  implement if state change needs to be handled
    }
}