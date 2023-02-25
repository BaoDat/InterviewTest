package com.datdang.interviewtest.ui.screens

import com.datdang.interviewtest.ui.base.BaseViewModel
import com.datdang.interviewtest.utils.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers)
