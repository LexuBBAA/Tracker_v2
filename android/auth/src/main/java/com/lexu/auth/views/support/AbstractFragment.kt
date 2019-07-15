/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.auth.views.support

import androidx.fragment.app.Fragment
import com.lexu.auth.delegates.NavigationDelegate

abstract class AbstractFragment<AbstractPresenter : NavigationDelegate.NavigationPresenter> : Fragment(),
    NavigationDelegate.NavigationView {
    protected lateinit var presenter: AbstractPresenter
}