package com.datdang.interviewtest.ui.screens.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.datdang.data.storage.NormalSharedPreferences
import com.datdang.interviewtest.R
import com.datdang.interviewtest.databinding.FragmentSignUpBinding
import com.datdang.interviewtest.model.common.PasswordType
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

    }

    override fun bindViewModel() {
        viewModel.navigator bindTo navigator::navigate

        viewModel.email bindTo {
            viewModel.emailError.value = null
        }

        viewModel.password bindTo {
            if (!it.isNullOrEmpty()){
                viewModel.validateStrongPassword()
            }
        }

        viewModel.passwordStrength bindTo { passwordStrength ->
            var color: Int = ContextCompat.getColor(requireContext(), R.color.password_too_short)
            var progress = 0
            when (passwordStrength) {
                PasswordType.Strength.TOO_SHORT -> {
                    color = ContextCompat.getColor(requireContext(), R.color.password_too_short)
                    progress = 0
                    viewModel.textPasswordStrength.value = getString(R.string.text_password_too_short)
                }
                PasswordType.Strength.WEAK -> {
                    color = ContextCompat.getColor(requireContext(), R.color.password_weak)
                    progress = 25
                    viewModel.textPasswordStrength.value = getString(R.string.text_password_weak)
                }
                PasswordType.Strength.FAIR -> {
                    color = ContextCompat.getColor(requireContext(), R.color.password_fair)
                    progress = 50
                    viewModel.textPasswordStrength.value = getString(R.string.text_password_fair)
                }
                PasswordType.Strength.GOOD -> {
                    color = ContextCompat.getColor(requireContext(), R.color.password_good)
                    progress = 75
                    viewModel.textPasswordStrength.value = getString(R.string.text_password_good)
                }
                PasswordType.Strength.STRONG -> {
                    color = ContextCompat.getColor(requireContext(), R.color.password_strong)
                    progress = 100
                    viewModel.textPasswordStrength.value = getString(R.string.text_password_strong)
                }
            }
            indicatePasswordStrength(color, progress)
        }
    }

    override fun bindViewEvents() {
        super.bindViewEvents()
        with(binding) {
            btnSignUp.setOnClickListener {
                this@SignUpFragment.viewModel.apply {
                    signUp()
                }
            }
        }
    }

    private fun indicatePasswordStrength(color: Int, progress: Int) {
        binding.seekbarPassword.progress = progress
        binding.seekbarPassword.progressDrawable.setTint(color)
        binding.textPasswordStrongIndicator.setTextColor(color)
    }
}