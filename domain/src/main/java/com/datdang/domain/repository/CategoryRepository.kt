package com.datdang.domain.repository

import com.datdang.domain.model.category.Category

interface CategoryRepository {
    suspend fun insertAllCategoriesData(category: Category): Boolean

    suspend fun getAllCategoriesData(): List<Category>

}