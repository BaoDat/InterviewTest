package com.datdang.interviewtest.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.datdang.data.database.AppDatabase
import com.datdang.data.storage.NormalSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    companion object {

        @Provides
        fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.invoke(context)


        @Provides
        fun provideDefaultSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)

        @Provides
        fun provideNormalLocalStorage(
            @ApplicationContext context: Context,
            defaultSharedPreferences: SharedPreferences
        ) = NormalSharedPreferences(context, defaultSharedPreferences)
    }
}
