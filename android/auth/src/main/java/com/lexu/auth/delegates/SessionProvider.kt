package com.lexu.auth.delegates

interface SessionProvider {
    suspend fun getUserId(): String?
    suspend fun saveSession(userId: String)
}