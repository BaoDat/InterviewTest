package com.datdang.interviewtest.ui.screens.categories

import androidx.lifecycle.MutableLiveData
import com.datdang.data.storage.NormalSharedPreferences
import com.datdang.domain.model.category.Category
import com.datdang.interviewtest.ui.base.BaseViewModel
import com.datdang.interviewtest.utils.DispatchersProvider
import com.datdang.domain.usecase.AccountUseCase
import com.datdang.domain.usecase.CategoryUseCase
import com.datdang.domain.usecase.utils.UseCaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    dispatchers: DispatchersProvider,
    private val getUsersUseCase: AccountUseCase,
    private val categoryUseCase: CategoryUseCase,
    val sharedPreferences: NormalSharedPreferences,
) : BaseViewModel(dispatchers) {

    val resultCategories = MutableLiveData<List<Category>>()

    val selectedCategoryCount = MutableLiveData<Int>(0)

    fun fetchData() {
        execute {
            when (val result = getUsersUseCase.executeListUserFollow()) {
                is UseCaseResult.Success -> {
                    getItemSelected(result.data.categories)
                }
                is UseCaseResult.NetworkError -> {}
                is UseCaseResult.Error -> {}
            }
        }
    }

    private fun getItemSelected(data: List<Category>) {
        execute {
            val result = categoryUseCase.executeCategoryList()
            selectedCategoryCount.postValue(result.data.size)
            result.data.forEach { categorySelected ->
                data.first { category -> category.id == categorySelected.id }.isSelected =
                    categorySelected.isSelected
            }
            resultCategories.postValue(data)
        }
    }

    fun saveData(category: Category) {
        invoke { categoryUseCase.executeSaveCategory(category) }
    }

}
