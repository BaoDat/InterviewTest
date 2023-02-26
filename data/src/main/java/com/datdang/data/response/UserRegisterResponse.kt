package com.datdang.data.response

import com.datdang.domain.model.RegisterData
import com.squareup.moshi.Json

data class UserRegisterResponse(
    @Json(name = "_id") val id: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "firstName") val firstName: String?,
    @Json(name = "lastName") val lastName: String?,
    @Json(name = "displayName") val displayName: String?,
    @Json(name = "token") val token: String?,
    @Json(name = "refreshToken") val refreshToken: String?
) {

    fun toData(): RegisterData {
        return RegisterData(
            id = this.id.orEmpty(),
            token = this.token.orEmpty(),
            refreshToken = this.refreshToken.orEmpty(),
            email = this.email.orEmpty(),
            firstName = this.firstName.orEmpty(),
            lastName = this.lastName.orEmpty(),
            displayName = this.displayName.orEmpty()
        )
    }
}