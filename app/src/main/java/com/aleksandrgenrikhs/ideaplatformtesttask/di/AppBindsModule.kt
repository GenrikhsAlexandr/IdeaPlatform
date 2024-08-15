package com.aleksandrgenrikhs.ideaplatformtesttask.di

import com.aleksandrgenrikhs.ideaplatformtesttask.data.ProductRepositoryImpl
import com.aleksandrgenrikhs.ideaplatformtesttask.domain.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface AppBindsModule {

    @Binds
    @Singleton
    fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository
}