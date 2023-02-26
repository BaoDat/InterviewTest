package com.datdang.interviewtest.di.modules

import com.datdang.data.repository.RepositoryImpl
import com.datdang.data.service.ApiService
import com.datdang.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepository(apiService: ApiService): Repository = RepositoryImpl(
        apiService
    )
}
