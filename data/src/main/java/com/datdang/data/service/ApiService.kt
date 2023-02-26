package com.datdang.data.service

import com.datdang.data.response.UserRegisterResponse
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("api/auth/signup")
    suspend fun registerAccount(@FieldMap map: Map<String, String>): UserRegisterResponse
}
