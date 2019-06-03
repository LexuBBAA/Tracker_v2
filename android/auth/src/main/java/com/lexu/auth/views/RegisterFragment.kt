/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.auth.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.lexu.auth.R
import com.lexu.auth.delegates.NavigationDelegate
import com.lexu.auth.views.support.SupportFragment

class RegisterFragment(presenter: NavigationDelegate.NavigationPresenter) :
    SupportFragment<NavigationDelegate.NavigationPresenter>(presenter) {

    private lateinit var usernameInputContainer: TextInputLayout
    private lateinit var usernameInputField: TextInputEditText
    private lateinit var emailInputContainer: TextInputLayout
    private lateinit var emailInputField: TextInputEditText
    private lateinit var passwordInputContainer: TextInputLayout
    private lateinit var passwordInputField: TextInputEditText
    private lateinit var confirmPasswordInputContainer: TextInputLayout
    private lateinit var confirmPasswordInputField: TextInputEditText

    private lateinit var submitButton: AppCompatImageButton
    private lateinit var cancelButton: AppCompatImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_register, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usernameInputContainer = rootView.findViewById(R.id.registerUsernameInputContainer)
        usernameInputField = rootView.findViewById(R.id.registerUsernameInputField)
        emailInputContainer = rootView.findViewById(R.id.registerEmailInputContainer)
        emailInputField = rootView.findViewById(R.id.registerEmailInputField)
        passwordInputContainer = rootView.findViewById(R.id.registerPasswordInputContainer)
        passwordInputField = rootView.findViewById(R.id.registerPasswordInputField)
        confirmPasswordInputContainer = rootView.findViewById(R.id.registerConfirmPasswordInputContainer)
        confirmPasswordInputField = rootView.findViewById(R.id.registerConfirmPasswordInputField)

        cancelButton = rootView.findViewById(R.id.registerBackButton)
        submitButton = rootView.findViewById(R.id.registerActionButton)

        //  TODO: refactor this
        cancelButton.setOnClickListener { presenter.onNavigateToLogin() }
        submitButton.setOnClickListener { presenter.onRegisterSuccessful() }
    }
}