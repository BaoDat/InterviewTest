package com.datdang.interviewtest.di.modules

import com.datdang.interviewtest.ui.screens.MainNavigator
import com.datdang.interviewtest.ui.screens.MainNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class NavigatorModule {

    @Binds
    abstract fun mainNavigator(mainNavigator: MainNavigatorImpl): MainNavigator
}
