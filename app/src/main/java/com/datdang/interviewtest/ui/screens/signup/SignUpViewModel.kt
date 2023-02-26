package com.datdang.interviewtest.ui.screens.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.datdang.data.storage.Const
import com.datdang.data.storage.NormalSharedPreferences
import com.datdang.interviewtest.extension.isEmailValid
import com.datdang.interviewtest.ui.base.BaseViewModel
import com.datdang.interviewtest.utils.DispatchersProvider
import com.datdang.domain.usecase.AccountUseCase
import com.datdang.domain.usecase.utils.UseCaseResult
import com.datdang.interviewtest.ui.base.NavigationEvent
import com.datdang.interviewtest.ui.base.setToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    dispatchers: DispatchersProvider,
    private val getUsersUseCase: AccountUseCase,
    val sharedPreferences: NormalSharedPreferences,
) : BaseViewModel(dispatchers) {

    val email = MutableLiveData<String>("")
    val emailError = MutableLiveData<Any?>()

    val password = MutableLiveData<String>("")
    val passwordError = MutableLiveData<Any?>()
    val passwordStrength = MutableLiveData(-2)

    val textPasswordStrength = MutableLiveData<String>("")

    val lengthRegex = Regex(".{6,18}.")
    val letterRegex = Regex("(.*[a-z].*)(.*[A-Z].*)")
    val numericRegex = Regex("(.*\\d.*)")
    val symbolRegex = Regex("(.*\\W.*)")

    fun validateEmail(): Boolean {
        email.value.orEmpty().also {
            return when {
                it.isBlank() -> {
                    emailError.value = "The email is required"
                    false
                }
                !it.isEmailValid() -> {
                    emailError.value = "The email is valid"
                    false
                }
                else -> {
                    emailError.value = null
                    true
                }
            }
        }
    }

    fun validateStrongPassword() {
        passwordError.value = null
        var passwordStrengthLevel = 0
        password.value?.let {
            if (!it.matches(lengthRegex))
                return passwordStrength.postValue(-1)
            if (it.matches(letterRegex))
                passwordStrengthLevel += 1
            if (it.matches(numericRegex))
                passwordStrengthLevel += 1
            if (it.matches(symbolRegex))
                passwordStrengthLevel += 1
        }
        passwordStrength.postValue(passwordStrengthLevel)
    }

    fun validatePassword(): Boolean {
        password.value.orEmpty().also {
            return when {
                it.isBlank() -> {
                    passwordError.value = "The email is required"
                    false
                }
                else -> {
                    passwordError.value = null
                    true
                }
            }
        }
    }

    fun signUp() {
        if (validateEmail() and validatePassword()) {
            execute {
                val  inputData: MutableMap<String, String> = mutableMapOf()
                inputData["firstName"] = "Dang Bao"
                inputData["lastName"] = "Dat"
                inputData["email"] = email.value.orEmpty()
                inputData["password"] = password.value.orEmpty()

                when (val result = getUsersUseCase.executeRegisterAccount(inputData)) {
                    is UseCaseResult.Success -> {
                        sharedPreferences.setToken(result.data.token)
                        viewModelScope.launch {
                            _navigator.emit(NavigationEvent.Categories)
                        }
                    }
                    is UseCaseResult.NetworkError -> {}
                    is UseCaseResult.Error -> {}
                }
            }
        }
    }
}
