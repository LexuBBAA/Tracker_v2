package com.lexu.auth.models

data class LoginBody(
    val username: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val password: String
)