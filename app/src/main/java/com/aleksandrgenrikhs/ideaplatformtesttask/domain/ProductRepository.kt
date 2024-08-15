package com.aleksandrgenrikhs.ideaplatformtesttask.domain

import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getAllProducts(): Flow<List<ProductModel>>

    suspend fun updateProductAmount(productId: Int, newAmount: Int)

    suspend fun deleteProduct(productId: Int)
}