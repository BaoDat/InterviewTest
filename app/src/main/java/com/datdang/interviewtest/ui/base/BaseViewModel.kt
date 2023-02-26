package com.datdang.interviewtest.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datdang.interviewtest.utils.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@Suppress("PropertyName")
abstract class BaseViewModel(private val dispatchersProvider: DispatchersProvider) : ViewModel() {

    protected val _navigator = MutableSharedFlow<NavigationEvent>()
    val navigator: SharedFlow<NavigationEvent>
        get() = _navigator

    fun execute(coroutineDispatcher: CoroutineDispatcher = dispatchersProvider.io, job: suspend () -> Unit) =
        viewModelScope.launch(coroutineDispatcher) {
            job.invoke()
        }


    fun invoke(coroutineDispatcher: CoroutineDispatcher = dispatchersProvider.io, job: suspend () -> Unit) =
        viewModelScope.launch(coroutineDispatcher) {
            job.invoke()
        }
}
