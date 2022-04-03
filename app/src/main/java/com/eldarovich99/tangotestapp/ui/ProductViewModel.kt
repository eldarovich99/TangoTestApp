package com.eldarovich99.tangotestapp.ui

import androidx.annotation.StringRes
import com.eldarovich99.tangotestapp.R
import com.eldarovich99.tangotestapp.data.ProductRepository
import com.eldarovich99.tangotestapp.mvi.BaseViewModel
import com.eldarovich99.tangotestapp.mvi.Reducer
import com.eldarovich99.tangotestapp.mvi.UiEvent
import com.eldarovich99.tangotestapp.mvi.UiState
import com.eldarovich99.tangotestapp.ui.mapper.ProductUiModelMapper
import com.eldarovich99.tangotestapp.ui.model.ProductUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : BaseViewModel<ProductState, ProductEvent>() {
    private val repository = ProductRepository()
    private val reducer = ProductReducer(ProductState.default())
    private val productMapper = ProductUiModelMapper

    override val state: StateFlow<ProductState>
        get() = reducer.state

    init {
        loadProducts()
    }

    fun loadProducts() {
        reducer.sendEvent(ProductEvent.Loading)
        dataScope.launch {
            try {
                val products = repository.loadProducts().map(productMapper::map)
                delay(1500) // TODO FIX
                reducer.sendEvent(ProductEvent.Loaded(products))
            } catch (e: Exception) {
                e.printStackTrace()
                reducer.sendEvent(ProductEvent.Error(e))
            }
        }
    }

    private class ProductReducer(initialValue: ProductState) :
        Reducer<ProductState, ProductEvent>(initialValue) {
        override fun reduce(oldState: ProductState, event: ProductEvent) {
            setState(
                when (event) {
                    is ProductEvent.Loaded ->
                        ProductState.Success(
                            event.data,
                            isRefreshing = false
                        )
                    is ProductEvent.Loading -> if (oldState !is ProductState.Success)
                        ProductState.Loading
                    else

                        ProductState.Success(
                            (oldState as? ProductState.Success)?.data ?: emptyList(),
                            isRefreshing = true
                        )
                    is ProductEvent.Error -> if (oldState is ProductState.Success) {
                        throw NotImplementedError("Show toast")
                    } else {
                        ProductState.Error(getErrorText(event.exception))
                    }
                }
            )
        }

        @StringRes
        private fun getErrorText(e: Exception): Int {
            return R.string.network_error
        }
    }
}

sealed class ProductState() : UiState {
    class Success(val data: List<ProductUiModel>, val isRefreshing: Boolean) : ProductState() {
        override fun toString(): String {
            return "${javaClass.name} data: ${data.size}, isRefreshing: ${isRefreshing}"
        }
    }

    object Loading : ProductState() {}
    class Error(@StringRes val textRes: Int) : ProductState()

    companion object {
        fun default() = Loading
    }
}

sealed class ProductEvent : UiEvent {
    class Loaded(val data: List<ProductUiModel>) : ProductEvent()
    class Error(val exception: Exception) : ProductEvent()
    object Loading : ProductEvent()
}