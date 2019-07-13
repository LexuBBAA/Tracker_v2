package com.lexu.auth.delegates

interface SessionProvider {
    fun getUserId(): String?
    fun saveSession(userId: String)
}