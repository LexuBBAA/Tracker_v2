package com.lexu.auth.delegates

interface EmailValidator {
    fun validate(email: String?): Boolean = if(email.isNullOrEmpty()) false
    else email.matches(Regex("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$"))
}