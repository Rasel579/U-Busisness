package app.u_business.presentation.utils

import android.content.Context

private const val APP_PREFERENCES = "prefs"

class SharedPreferencesHelper(ctx: Context) {
    private val prefs = ctx.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    companion object {
        private const val FIRST_OPEN_KEY = "first_open"
        private const val IS_AUTHED_KEY = "isAuthed"
        private const val ACCESS_TOKEN = "accessToken"
        private const val REG_BODY = "registrationBody"
        private const val USER_ID = "user_id"
    }

    var userId: String? = null
        set(value) {
            field = value
            prefs.edit().putString(USER_ID, value).apply()
        }
        get() = prefs.getString(USER_ID, null)

    var isFirstOpen = true
        set(value) {
            field = value
            prefs.edit().putBoolean(FIRST_OPEN_KEY, value).apply()
        }
        get() = prefs.getBoolean(FIRST_OPEN_KEY, true)

    var isAuthed = false
        set(value) {
            field = value
            prefs.edit().putBoolean(IS_AUTHED_KEY, value).apply()
        }
        get() = prefs.getBoolean(IS_AUTHED_KEY, false)

    var accessToken: String? = null
        set(value){
            field = value
            prefs.edit().putString(ACCESS_TOKEN, value).apply()

        }
        get() = prefs.getString(ACCESS_TOKEN, null)

    var registrationBody: String? = null
           set(value) {
               field = value
               prefs.edit().putString(REG_BODY, value).apply()
           }
        get() = prefs.getString(REG_BODY,null)

}