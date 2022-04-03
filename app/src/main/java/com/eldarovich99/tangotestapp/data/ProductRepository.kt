package com.eldarovich99.tangotestapp.data

import com.eldarovich99.tangotestapp.data.api.ProductsApi
import com.eldarovich99.tangotestapp.data.model.ProductNetworkModelItem

class ProductRepository : IProductRepository {
    private val api = RetrofitService.api.create(ProductsApi::class.java)
    override suspend fun loadProducts(): List<ProductNetworkModelItem> {
        return api.getProducts()
    }
}