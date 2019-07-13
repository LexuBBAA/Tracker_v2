/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.auth.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.lexu.auth.R
import com.lexu.auth.delegates.NavigationDelegate
import com.lexu.auth.models.LoginBody
import com.lexu.auth.views.support.SupportFragment

class LoginFragment(presenter: NavigationDelegate.NavigationPresenter) :
    SupportFragment<NavigationDelegate.NavigationPresenter>(presenter) {

    private lateinit var usernameInputContainer: TextInputLayout
    private lateinit var usernameInputField: TextInputEditText
    private lateinit var passwordInputContainer: TextInputLayout
    private lateinit var passwordInputField: TextInputEditText

    private lateinit var resetPasswordLabel: AppCompatTextView
    private lateinit var registerButton: AppCompatButton
    private lateinit var loginActionButton: AppCompatImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_login, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameInputContainer = rootView.findViewById(R.id.loginUsernameInputContainer)
        usernameInputField = rootView.findViewById(R.id.loginUsernameInputField)
        passwordInputContainer = rootView.findViewById(R.id.loginPasswordInputContainer)
        passwordInputField = rootView.findViewById(R.id.loginPasswordInputField)

        resetPasswordLabel = rootView.findViewById(R.id.loginResetPasswordLabel)
        registerButton = rootView.findViewById(R.id.loginSignUpButton)
        loginActionButton = rootView.findViewById(R.id.loginActionButton)

        resetPasswordLabel.setOnClickListener {
            passwordInputField.setText("")

            presenter.onNavigateToResetPass()
        }
        registerButton.setOnClickListener {
            passwordInputField.setText("")

            presenter.onNavigateToRegister()
        }
        loginActionButton.setOnClickListener {
            val validData = !usernameInputField.text?.toString().isNullOrEmpty() &&
                !passwordInputField.text?.toString().isNullOrEmpty()

            if(validData) presenter.onLoginClicked(LoginBody(
                username = usernameInputField.text.toString(),
                password = passwordInputField.text.toString()
            ))
        }
    }
}