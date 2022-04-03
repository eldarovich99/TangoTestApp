package com.eldarovich99.tangotestapp.ui

import com.eldarovich99.tangotestapp.data.ProductRepository
import com.eldarovich99.tangotestapp.mvi.BaseViewModel
import com.eldarovich99.tangotestapp.mvi.Reducer
import com.eldarovich99.tangotestapp.mvi.UiEvent
import com.eldarovich99.tangotestapp.mvi.UiState
import com.eldarovich99.tangotestapp.ui.mapper.ProductUiModelMapper
import com.eldarovich99.tangotestapp.ui.model.ProductUiModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class ProductViewModel : BaseViewModel<ProductState, ProductEvent>() {
    private val repository = ProductRepository()
    private val reducer = ProductReducer(ProductState.default())
    private val productMapper = ProductUiModelMapper

    override val state: StateFlow<ProductState>
        get() = reducer.state

    init {
        loadProducts()
    }

    private fun loadProducts() {
        reducer.sendEvent(ProductEvent.Loading)
        dataScope.launch {
            try {
                val products = repository.loadProducts().map(productMapper::map)
                reducer.sendEvent(ProductEvent.Loaded(products))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private class ProductReducer(initialValue: ProductState) :
        Reducer<ProductState, ProductEvent>(initialValue) {
        override fun reduce(oldState: ProductState, event: ProductEvent) {
            when (event) {
                is ProductEvent.Loaded -> setState(ProductState.Success(event.data))
                is ProductEvent.Loading -> setState(ProductState.Loading)
            }
        }
    }
}

sealed class ProductState : UiState {
    class Success(val data: List<ProductUiModel>) : ProductState()
    object Loading : ProductState()

    companion object {
        fun default() = Loading
    }
}

sealed class ProductEvent : UiEvent {
    class Loaded (val data: List<ProductUiModel>): ProductEvent()
    object Loading: ProductEvent()
}