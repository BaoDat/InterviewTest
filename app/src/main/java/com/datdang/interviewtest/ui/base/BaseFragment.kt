package com.datdang.interviewtest.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


abstract class BaseFragment<VB : ViewBinding> : Fragment(), BaseFragmentCallbacks {

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    private var _binding: ViewBinding? = null

    protected var lazyLoad = false

    @Suppress("UNCHECKED_CAST")
    val binding: VB
        get() = _binding as VB

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            lazyLoad = it.getBoolean(BUNDLE_LAZY_LOAD)
        }
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this as? BaseFragmentCallbacks)?.let { initViewModel() }
    }

    override fun initViewModel() {}

    override fun setupView() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return bindingInflater.invoke(inflater, container, false).apply {
            _binding = this
        }.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (this as? BaseFragmentCallbacks)?.let {
            if (!lazyLoad) {
                setupView()
                bindViewEvents()
                bindViewModel()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @CallSuper
    override fun bindViewEvents() {

    }

    override fun onResume() {
        super.onResume()
    }

    open fun lazyLoadView(){
        if (lazyLoad) {
            setupView()
            bindViewEvents()
            bindViewModel()
            lazyLoad = false
        }
    }

    fun View.hideSoftKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
    }

    protected inline infix fun <T> Flow<T>.bindTo(crossinline action: (T) -> Unit) {
        with(viewLifecycleOwner) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    collect { action(it) }
                }
            }
        }
    }

    protected inline infix fun <T> MutableLiveData<T>.bindTo(crossinline action: (T) -> Unit) {
        observe(viewLifecycleOwner) {
            action(it)
        }
    }

    companion object {
        const val BUNDLE_LAZY_LOAD = "bundle_lazy_load"
    }
}
