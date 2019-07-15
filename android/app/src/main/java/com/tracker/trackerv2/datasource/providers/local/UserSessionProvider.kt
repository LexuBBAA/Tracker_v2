package com.tracker.trackerv2.datasource.providers.local

import android.content.Context
import com.lexu.auth.delegates.SessionProvider

class UserSessionProvider(context: Context): SessionProvider {
    private val sharedPreferences = context.getSharedPreferences(RUNTIME_CONFIGS, Context.MODE_PRIVATE)

    override suspend fun getUserId(): String? = synchronized(this) { sharedPreferences.getString(USER_ID_KEY, "") }

    override suspend fun saveSession(userId: String) = synchronized(this) {
        sharedPreferences.edit()
            .putString(USER_ID_KEY, userId)
            .apply()
    }

    companion object {
        private const val RUNTIME_CONFIGS = "tracker_v2_runtime_cfg"
        private const val USER_ID_KEY = "user_id"
    }
}