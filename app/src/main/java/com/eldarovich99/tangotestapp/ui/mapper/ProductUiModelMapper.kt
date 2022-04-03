package com.eldarovich99.tangotestapp.ui.mapper

import com.eldarovich99.tangotestapp.data.model.ProductNetworkModelItem
import com.eldarovich99.tangotestapp.ui.model.ProductUiModel

object ProductUiModelMapper {
    fun map(networkModel: ProductNetworkModelItem): ProductUiModel {
        return ProductUiModel(
            grossPrice = networkModel.grossPrice,
            id = networkModel.id,
            images = networkModel.images,
            manufacturer = networkModel.manufacturer,
            productName = networkModel.productName,
            sku = networkModel.sku,
            slug = networkModel.slug
        )
    }
}