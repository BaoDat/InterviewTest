package com.datdang.domain.model

data class RegisterData (
    val id: String? = "",
    val token: String? = "",
    val refreshToken: String? = "",
    val email: String? = "",
    val firstName: String? = "",
    val lastName: String? = "",
    val displayName: String? = "",
)