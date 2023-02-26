package com.datdang.interviewtest.ui.screens.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCategoriesBinding
        get() = { inflater, container, attachToParent ->
            FragmentCategoriesBinding.inflate(inflater, container, attachToParent)
        }

    override fun setupView() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.fetchData()

    }

    override fun bindViewModel() {
        viewModel.navigator bindTo navigator::navigate

    }

    override fun bindViewEvents() {
        super.bindViewEvents()

    }

}