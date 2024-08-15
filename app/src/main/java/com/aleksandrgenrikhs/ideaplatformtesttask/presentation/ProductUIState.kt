package com.aleksandrgenrikhs.ideaplatformtesttask.presentation

data class ProductUIState(
    val id: List<Int> = emptyList(),
    val name: List<String> = emptyList(),
    val time: List<String> = emptyList(),
    val tags: Map<Int, List<String>> = emptyMap(),
    val amount: List<Int> = emptyList(),
)