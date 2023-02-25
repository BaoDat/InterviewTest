package com.datdang.interviewtest.ui.base

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

interface BaseNavigator {

    val navHostFragmentId: Int

    fun findNavController(): NavController?

    fun navigate(event: NavigationEvent)

    fun navigateUp()

    fun popBack()
}

abstract class BaseNavigatorImpl(
    protected val fragment: Fragment
) : BaseNavigator {

    private var navController: NavController? = null

    override fun findNavController(): NavController? {
        return navController ?: try {
            fragment.findNavController().apply {
                navController = this
            }
        } catch (e: IllegalStateException) {
            null
        }
    }

    override fun navigateUp() {
        findNavController()?.navigateUp()
    }

    protected fun popBackTo(@IdRes destinationId: Int, inclusive: Boolean = false) {
        findNavController()?.popBackStack(destinationId, inclusive)
    }

    override fun popBack() {
        findNavController()?.popBackStack()
    }

}
