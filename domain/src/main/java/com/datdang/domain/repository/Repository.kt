package com.datdang.domain.repository

import com.datdang.domain.model.RegisterData


interface Repository {

    suspend fun registerAccount(params: MutableMap<String, String>): RegisterData

}
