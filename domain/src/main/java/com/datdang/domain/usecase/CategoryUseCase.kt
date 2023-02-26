package com.datdang.domain.usecase

import com.datdang.domain.model.category.Category
import com.datdang.domain.repository.CategoryRepository
import com.datdang.domain.usecase.utils.UseCaseResult
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val categoryUseCase: CategoryRepository) {

    suspend fun executeSaveCategory(category: Category): UseCaseResult<Boolean> {
        val isSuccessful = categoryUseCase.insertAllCategoriesData(category)
        return if (isSuccessful) UseCaseResult.Success(true)
        else UseCaseResult.Error(Throwable("File Error"))
    }

    suspend fun executeCategoryList(): UseCaseResult.Success<List<Category>> {
        val data = categoryUseCase.getAllCategoriesData()
        return UseCaseResult.Success(data)
    }
}