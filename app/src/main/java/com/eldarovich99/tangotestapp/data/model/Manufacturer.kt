package com.eldarovich99.tangotestapp.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Manufacturer(
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String
)