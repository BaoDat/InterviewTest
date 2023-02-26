package com.datdang.data.repository

import com.datdang.data.database.AppDatabase
import com.datdang.data.database.entity.CategoryEntity
import com.datdang.domain.model.category.Category
import com.datdang.domain.repository.CategoryRepository

class CategoryRepositoryImpl(private val database: AppDatabase): CategoryRepository {

    override suspend fun insertAllCategoriesData(category: Category): Boolean {
        val entity = CategoryEntity.toCategoryEntity(category)
        database.categoryDao().insertSelectedCategory(entity)
        return true
    }

    override suspend fun getAllCategoriesData(): List<Category> {
        val entityList = database.categoryDao().getSelectedCategories()
        return  entityList.map { it.toCategoryData() }
    }
}