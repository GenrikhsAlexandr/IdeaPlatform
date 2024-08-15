package com.aleksandrgenrikhs.ideaplatformtesttask.domain

data class ProductModel(
    val id: Int,
    val name: String,
    val time: String,
    val tags: List<String>,
    val amount: Int,
)