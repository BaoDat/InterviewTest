package com.datdang.interviewtest.ui.screens.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.datdang.data.storage.NormalSharedPreferences
import com.datdang.interviewtest.databinding.FragmentSignUpBinding
import com.datdang.interviewtest.ui.base.BaseFragment
import com.datdang.interviewtest.ui.screens.MainNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

  @Inject
  lateinit var navigator: MainNavigator

  @Inject
  lateinit var sharedPreferences: NormalSharedPreferences

  private val viewModel: SignUpViewModel by viewModels()

  override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignUpBinding
    get() = { inflater, container, attachToParent ->
      FragmentSignUpBinding.inflate(inflater, container, attachToParent)
    }

  override fun setupView() {
  }

  override fun bindViewModel() {
    viewModel.navigator bindTo navigator::navigate
  }


}