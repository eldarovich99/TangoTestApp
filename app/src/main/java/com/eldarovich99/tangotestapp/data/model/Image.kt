package com.eldarovich99.tangotestapp.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("image")
    val image: String,
    @SerialName("order")
    val order: Int
)