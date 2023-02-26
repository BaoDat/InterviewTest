package com.datdang.interviewtest.extension

import androidx.core.util.PatternsCompat
import java.util.regex.Pattern


fun String.isEmailValid(): Boolean {
    return isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isPasswordValid(): Boolean {
    val userInfoPattern : Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&\\s]).{8,16}")
    return isNotEmpty() && userInfoPattern.matcher(this).matches()
}
