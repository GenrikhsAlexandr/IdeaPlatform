package com.aleksandrgenrikhs.ideaplatformtesttask.presentation

import com.aleksandrgenrikhs.ideaplatformtesttask.domain.ProductModel
import javax.inject.Inject

class UIStateMapper
@Inject constructor() {
    fun map(products: List<ProductModel>): ProductUIState = ProductUIState(
        id = products.map { it.id },
        name = products.map { it.name },
        time = products.map { it.time },
        tags = products.associate { it.id to it.tags },
        amount = products.map { it.amount },
    )
}
