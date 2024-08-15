package com.aleksandrgenrikhs.ideaplatformtesttask.data

import com.aleksandrgenrikhs.ideaplatformtesttask.data.database.ProductsCatalogDao
import com.aleksandrgenrikhs.ideaplatformtesttask.data.mapper.ProductCatalogMapper
import com.aleksandrgenrikhs.ideaplatformtesttask.domain.ProductModel
import com.aleksandrgenrikhs.ideaplatformtesttask.domain.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl
@Inject constructor(
    private val productDao: ProductsCatalogDao,
    private val productCatalogMapper: ProductCatalogMapper,
) : ProductRepository {
    override fun getAllProducts(): Flow<List<ProductModel>> {
        val products = productDao.getAllProducts().map { listProductsDb ->
            productCatalogMapper.map(listProductsDb)
        }
        return products
    }

    override suspend fun updateProductAmount(productId: Int, newAmount: Int) {
        productDao.updateProductAmount(productId, newAmount)
    }

    override suspend fun deleteProduct(productId: Int) {
        productDao.deleteProductById(productId)
    }
}