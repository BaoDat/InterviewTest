package com.datdang.interviewtest.ui.screens.signup

import com.datdang.interviewtest.ui.base.BaseViewModel
import com.datdang.interviewtest.utils.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {
}
