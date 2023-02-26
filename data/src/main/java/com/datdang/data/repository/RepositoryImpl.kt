package com.datdang.data.repository


import com.datdang.data.service.ApiService
import com.datdang.domain.model.RegisterData
import com.datdang.domain.repository.Repository

class RepositoryImpl(private val apiService: ApiService) : Repository {

    override suspend fun registerAccount(params: MutableMap<String, String>): RegisterData =
        apiService.registerAccount(params).toData()

}
