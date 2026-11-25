package com.example.fundlink.data.session

import android.content.Context
import com.example.fundlink.data.model.User

class SessionManager(context: Context) {
    private val prefs = context.applicationContext
        .getSharedPreferences("fundlink_session", Context.MODE_PRIVATE)

    fun save(user: User) {
        prefs.edit().putLong("uid", user.id).putString("role", user.role).apply()
    }

    fun userId(): Long = prefs.getLong("uid", -1L)
    fun role(): String? = prefs.getString("role", null)
    fun isLoggedIn(): Boolean = userId() != -1L
    fun clear() = prefs.edit().clear().apply()
}
