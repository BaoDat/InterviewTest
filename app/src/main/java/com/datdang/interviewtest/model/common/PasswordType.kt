package com.datdang.interviewtest.model.common

object PasswordType {
  object Strength {
    const val TOO_SHORT = -1
    const val WEAK = 0
    const val FAIR = 1
    const val GOOD = 2
    const val STRONG = 3
  }
}