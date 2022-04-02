package com.eldarovich99.tangotestapp.data

import com.eldarovich99.tangotestapp.data.api.ProductsApi
import com.eldarovich99.tangotestapp.data.model.ProductNetworkModelItem
import io.reactivex.Single

class ProductRepository : IProductRepository {
    private val api = RetrofitService.api.create(ProductsApi::class.java)
    override fun retreiveProducts(): Single<List<ProductNetworkModelItem>> {
        return api.getProducts().map { it }
    }
}