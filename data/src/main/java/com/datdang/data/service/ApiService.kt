package com.datdang.data.service

import com.datdang.data.response.CategoriesResponse
import com.datdang.data.response.UserRegisterResponse
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("api/auth/signup")
    suspend fun registerAccount(@FieldMap map: Map<String, String>): UserRegisterResponse

    @GET("api/categories?pageSize=100&pageNumber=0")
    suspend fun getCategories(): CategoriesResponse

}
