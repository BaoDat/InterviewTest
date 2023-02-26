package com.datdang.domain.repository

import com.datdang.domain.model.RegisterData
import com.datdang.domain.model.category.CategoriesList


interface Repository {

    suspend fun registerAccount(params: MutableMap<String, String>): RegisterData

    suspend fun getListCategories(): CategoriesList

}
