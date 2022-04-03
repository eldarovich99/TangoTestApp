package com.eldarovich99.tangotestapp.data.api

import com.eldarovich99.tangotestapp.data.model.ProductNetworkModelItem
import retrofit2.http.GET

interface ProductsApi {
    @GET("products")
    suspend fun getProducts(): List<ProductNetworkModelItem>
}