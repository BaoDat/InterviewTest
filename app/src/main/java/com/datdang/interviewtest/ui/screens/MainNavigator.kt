package com.datdang.interviewtest.ui.screens

import androidx.fragment.app.Fragment
import com.datdang.interviewtest.R
import com.datdang.interviewtest.ui.base.BaseNavigator
import com.datdang.interviewtest.ui.base.BaseNavigatorImpl
import com.datdang.interviewtest.ui.base.NavigationEvent

import javax.inject.Inject

interface MainNavigator : BaseNavigator

class MainNavigatorImpl @Inject constructor(
    fragment: Fragment,
) : BaseNavigatorImpl(fragment), MainNavigator {

    override val navHostFragmentId = R.id.navHostFragment

    override fun navigate(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.Categories -> navigateToCategories()
        }
    }

    private fun navigateToCategories() {
        val navController = findNavController()
        when (navController?.currentDestination?.id) {

        }
    }
}
