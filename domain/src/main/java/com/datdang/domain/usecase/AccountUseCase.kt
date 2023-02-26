package com.datdang.domain.usecase


import com.datdang.domain.model.RegisterData
import com.datdang.domain.repository.Repository
import com.datdang.domain.usecase.utils.UseCaseResult
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject


class AccountUseCase @Inject constructor(private val repository: Repository) {

    suspend fun executeRegisterAccount(params: MutableMap<String, String>): UseCaseResult<RegisterData> {
        return try {
            val response = repository.registerAccount(params)
            UseCaseResult.Success(response)
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException -> UseCaseResult.NetworkError
                is ConnectException -> UseCaseResult.NetworkError
                else -> UseCaseResult.Error(e)
            }
        }
    }

}
