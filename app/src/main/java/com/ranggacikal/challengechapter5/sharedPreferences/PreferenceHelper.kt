package com.ranggacikal.challengechapter5.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object PreferenceHelper {

    val USER_NAME = "USER_NAME"
    val IS_LOGIN = "IS LOGIN"
    val USER_TOKEN = "USER_TOKEN"
    val IS_OPEN_APP = "IS OPEN APP"

    fun defaultPreference(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.userName
        get() = getString(USER_NAME, "")
        set(value) {
            editMe {
                it.putString(USER_NAME, value)
            }
        }

    var SharedPreferences.isLogin
        get() = getBoolean(IS_LOGIN, false)
        set(value) {
            editMe {
                it.putBoolean(IS_LOGIN, value)
            }
        }

    var SharedPreferences.token
        get() = getString(USER_TOKEN, "")
        set(value) {
            editMe {
                it.putString(USER_TOKEN, value)
            }
        }

    var SharedPreferences.isOpenApp
        get() = getBoolean(IS_OPEN_APP, true)
        set(value) {
            editMe {
                it.putBoolean(IS_OPEN_APP, value)
            }
        }

    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }
}