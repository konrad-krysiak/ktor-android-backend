package com.example.models

import kotlinx.serialization.Serializable

val yerbaList = mutableListOf<Customer>()

@Serializable
data class Yerba (
    val id: String,
    val name: String,
    val type: String,
    val price: Int,
    val country: String,
    val weight: Int
)