package com.datdang.interviewtest.ui.base

import com.datdang.data.storage.Const
import com.datdang.data.storage.NormalSharedPreferences


// Setup user data
fun NormalSharedPreferences.setToken(token: String?) = setValue(Const.USER_TOKEN, token ?: "")

fun NormalSharedPreferences.getToken() = get<String>(Const.USER_TOKEN)

fun NormalSharedPreferences.isLogged(): Boolean =
    get<String>(Const.USER_TOKEN).orEmpty().isNotEmpty()
