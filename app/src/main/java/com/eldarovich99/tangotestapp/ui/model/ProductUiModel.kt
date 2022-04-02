package com.eldarovich99.tangotestapp.ui.model

import com.eldarovich99.tangotestapp.data.model.Image
import com.eldarovich99.tangotestapp.data.model.Manufacturer

data class ProductUiModel(
    val grossPrice: String,
    val id: String,
    val images: List<Image>,
    val manufacturer: Manufacturer,
    val productName: String,
    val sku: String,
    val slug: String
)