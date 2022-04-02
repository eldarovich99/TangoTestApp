package com.eldarovich99.tangotestapp.data.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductNetworkModelItem(
    @SerialName("gross_price")
    val grossPrice: String,
    @SerialName("id")
    val id: String,
    @SerialName("images")
    val images: List<Image>,
    @SerialName("manufacturer")
    val manufacturer: Manufacturer,
    @SerialName("product_name")
    val productName: String,
    @SerialName("sku")
    val sku: String,
    @SerialName("slug")
    val slug: String
)