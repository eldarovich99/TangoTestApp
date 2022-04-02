package com.eldarovich99.tangotestapp.data

import com.eldarovich99.tangotestapp.data.model.ProductNetworkModelItem
import io.reactivex.Single

interface IProductRepository {
    fun retreiveProducts(): Single<List<ProductNetworkModelItem>>
}