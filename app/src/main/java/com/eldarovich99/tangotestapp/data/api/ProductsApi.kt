package com.eldarovich99.tangotestapp.data.api

import com.eldarovich99.tangotestapp.data.model.ProductNetworkModel
import io.reactivex.Single
import retrofit2.http.GET

interface ProductsApi {
    @GET("products")
    fun getProducts(): Single<ProductNetworkModel>
}