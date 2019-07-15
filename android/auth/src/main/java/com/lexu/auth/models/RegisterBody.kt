package com.lexu.auth.models

data class RegisterBody(
    val email: String,
    val username: String,
    val password: String
)