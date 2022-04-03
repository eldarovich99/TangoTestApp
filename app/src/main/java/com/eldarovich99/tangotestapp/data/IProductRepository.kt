package com.eldarovich99.tangotestapp.data

import com.eldarovich99.tangotestapp.data.model.ProductNetworkModelItem

interface IProductRepository {
    suspend fun loadProducts(): List<ProductNetworkModelItem>
}