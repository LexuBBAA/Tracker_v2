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

class ResetFragment(presenter: NavigationDelegate.NavigationPresenter) :
    SupportFragment<NavigationDelegate.NavigationPresenter>(presenter) {

    private lateinit var emailInputContainer: TextInputLayout
    private lateinit var emailInputField: TextInputEditText

    private lateinit var submitButton: AppCompatImageButton
    private lateinit var cancelButton: AppCompatImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_reset_password, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailInputContainer = rootView.findViewById(R.id.resetPasswordEmailInputContainer)
        emailInputField = rootView.findViewById(R.id.resetPasswordEmailInputField)

        cancelButton = rootView.findViewById(R.id.resetPasswordBackButton)
        submitButton = rootView.findViewById(R.id.resetPasswordActionButton)

        //  TODO: refactor this
        cancelButton.setOnClickListener { presenter.onNavigateToLogin() }
        submitButton.setOnClickListener { presenter.onNavigateToLogin() }
    }
}