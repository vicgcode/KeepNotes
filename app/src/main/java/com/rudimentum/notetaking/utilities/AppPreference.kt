package com.rudimentum.notetaking.utilities

import android.content.Context
import android.content.SharedPreferences

object AppPreference {
    private const val INIT_USER = "initUser"
    private const val TYPE_DATABASE = "typeDatabase"
    private const val NAME_PREF = "user_preference"

    private lateinit var mPreferences: SharedPreferences

    fun getPreference(context: Context): SharedPreferences {
        mPreferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
        return mPreferences
    }

    fun setInitUser(init: Boolean) {
        mPreferences.edit().putBoolean(INIT_USER, init).apply()
    }

    fun setTypeDatabase(type: String) {
        mPreferences.edit().putString(TYPE_DATABASE, type).apply()
    }

    fun getInitUser(): Boolean {
        return mPreferences.getBoolean(INIT_USER, false)
    }

    fun getTypeDatabase(): String {
        return mPreferences.getString(TYPE_DATABASE, TYPE_ROOM).toString()
    }

}