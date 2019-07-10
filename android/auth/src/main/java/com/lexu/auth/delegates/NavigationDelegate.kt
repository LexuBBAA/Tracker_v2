/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.auth.delegates

interface NavigationDelegate {

    interface NavigationView {
        fun onResumeView()
        fun onPauseView()
    }

    interface NavigationPresenter {
        fun onNavigateToLogin()
        fun onNavigateToRegister()
        fun onNavigateToResetPass()

        //  TODO: refactor success payload
        fun onLoginSuccessful()
        fun onRegisterSuccessful()
        fun onResetPassSuccessful()

        fun onLoginFailure(exception: Exception)
        fun onRegisterFailure(exception: Exception)
        fun onResetPassFailure(exception: Exception)
    }

}