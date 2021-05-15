package app.u_business.presentation.utils

import android.content.Context

private const val APP_PREFERENCES = "prefs"

class SharedPreferencesHelper(ctx: Context) {
    private val prefs = ctx.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    companion object {
        private const val FIRST_OPEN_KEY = "first_open"
        private const val IS_AUTHED_KEY = "isAuthed"
    }

    var isFirstOpen = true
        set(value) {
            field = value
            prefs.edit().putBoolean(FIRST_OPEN_KEY, value).apply()
        }
        get() = prefs.getBoolean(FIRST_OPEN_KEY, true)

    var isAuthed = false
        set(value) {
            field = value
            prefs.edit().putBoolean(FIRST_OPEN_KEY, value).apply()
        }
        get() = prefs.getBoolean(FIRST_OPEN_KEY, false)


}