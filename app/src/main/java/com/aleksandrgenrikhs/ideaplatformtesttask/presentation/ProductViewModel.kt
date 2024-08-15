package com.aleksandrgenrikhs.ideaplatformtesttask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleksandrgenrikhs.ideaplatformtesttask.domain.ProductModel
import com.aleksandrgenrikhs.ideaplatformtesttask.domain.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel
@Inject constructor(
    private val productRepository: ProductRepository,
    private val uiStateMapper: UIStateMapper
) : ViewModel() {

    private val searchRequest: MutableStateFlow<String> = MutableStateFlow("")
    private val _uiState: MutableStateFlow<ProductUIState> =
        MutableStateFlow(ProductUIState())
    val uiState: StateFlow<ProductUIState> = combine(
        searchRequest,
        productRepository.getAllProducts()
    ) { request, words ->
        words.filterByRequest(request = request)
    }.map { products ->
        val uiProduct = uiStateMapper.map(products)
        _uiState.update { uiState ->
            uiState.copy(
                id = uiProduct.id,
                name = uiProduct.name,
                time = uiProduct.time,
                tags = uiProduct.tags,
                amount = uiProduct.amount
            )
        }
        _uiState.value
    }.stateIn(viewModelScope, SharingStarted.Eagerly, initialValue = ProductUIState())

    fun onSearchQuery(query: String) {
        searchRequest.value = query
        println("query = $query")
    }

    private fun List<ProductModel>.filterByRequest(request: String): List<ProductModel> {
        return if (request.isEmpty()) {
            this
        } else {
            this.filter { product ->
                product.name.contains(request, ignoreCase = true)
            }
        }
    }

    fun updateAmount(id: Int, amount: Int) {
        viewModelScope.launch {
            productRepository.updateProductAmount(id, amount)
        }
    }

    fun deleteProduct(id: Int) {
        viewModelScope.launch {
            productRepository.deleteProduct(id)
        }
    }
}