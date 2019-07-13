/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.auth.delegates

import com.lexu.auth.models.LoginBody
import com.lexu.auth.models.RegisterBody

interface NavigationDelegate {

    interface NavigationView {
        fun onResumeView()
        fun onPauseView()
    }

    interface NavigationPresenter {
        fun onNavigateToLogin()
        fun onNavigateToRegister()
        fun onNavigateToResetPass()

        fun onLoginClicked(loginBody: LoginBody)
        fun onRegisterClicked(registerBody: RegisterBody)
        fun onResetPassClicked(email: String)

        fun onLoginFailure(exception: Exception)
        fun onRegisterFailure(exception: Exception)
        fun onResetPassFailure(exception: Exception)
    }

}