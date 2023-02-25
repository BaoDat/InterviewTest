package com.datdang.data.storage

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class NormalSharedPreferences @Inject constructor(
    private val applicationContext: Context,
    private val defaultSharedPreferences: SharedPreferences
) : BaseSharedPreferences() {

    init {
        useDefaultSharedPreferences()
    }

    fun useDefaultSharedPreferences() {
        sharedPreferences = defaultSharedPreferences
    }

    fun <T> setValue(key: String, value: T) {
        set(key, value)
    }

    fun setValueInt(key: String, value: Int) {
        setInt(key, value)
    }

    inline fun <reified T> getValue(key: String): T? = get(key) as T?
}
