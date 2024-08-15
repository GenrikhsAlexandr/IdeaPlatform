package com.aleksandrgenrikhs.ideaplatformtesttask.di

import android.app.Application
import android.content.Context
import com.aleksandrgenrikhs.ideaplatformtesttask.data.database.AppDatabase
import com.aleksandrgenrikhs.ideaplatformtesttask.data.database.ProductsCatalogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object AppProvidesModule {

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json { ignoreUnknownKeys = true }
    }

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext app: Context): Context = app as Application

    @Provides
    @Singleton
    fun provideWordDao(
        application: Application
    ): ProductsCatalogDao = AppDatabase.getInstance(application).productsCatalog()
}