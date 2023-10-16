package com.utkarsha.lotteryapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log


object SharedPrefsUtil {

    fun saveString(name : String, key: String, value: String, context: Context) {
        Log.d("Testlog", "Saving $value to $key")
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(name : String, key: String, context: Context) : String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "")
    }

    fun deleteString(name : String, key: String, context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }
}