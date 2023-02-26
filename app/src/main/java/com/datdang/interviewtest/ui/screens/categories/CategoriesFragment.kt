package com.datdang.interviewtest.ui.screens.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.datdang.data.storage.NormalSharedPreferences
import com.datdang.interviewtest.databinding.FragmentCategoriesBinding
import com.datdang.interviewtest.ui.base.BaseFragment
import com.datdang.interviewtest.ui.screens.MainNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {

    @Inject
    lateinit var navigator: MainNavigator

    @Inject
    lateinit var sharedPreferences: NormalSharedPreferences

    private val viewModel: CategoriesViewModel by viewModels()
    private lateinit var categoriesAdapter: CategoriesAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCategoriesBinding
        get() = { inflater, container, attachToParent ->
            FragmentCategoriesBinding.inflate(inflater, container, attachToParent)
        }

    override fun setupView() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.fetchData()
        setupDataList()
    }

    override fun bindViewModel() {
        viewModel.navigator bindTo navigator::navigate

        viewModel.resultCategories bindTo { data ->
            with(categoriesAdapter) {
                items = data.toMutableList()
            }
        }

    }

    override fun bindViewEvents() {
        super.bindViewEvents()
        categoriesAdapter.itemClick.bindTo {
            when (it) {
                is CategoriesAdapter.OnItemClick.ItemCategory -> {
                    viewModel.saveData(it.data)
                    if (!it.data.isSelected) {
                        viewModel.selectedCategoryCount.postValue(
                            viewModel.selectedCategoryCount.value?.minus(1)
                        )
                    } else {
                        viewModel.selectedCategoryCount.postValue(
                            viewModel.selectedCategoryCount.value?.plus(1)
                        )
                    }
                }
            }
        }

    }

    private fun setupDataList() {
        with(binding.rvCategories) {
            adapter = CategoriesAdapter().also {
                categoriesAdapter = it
            }
        }
    }
}