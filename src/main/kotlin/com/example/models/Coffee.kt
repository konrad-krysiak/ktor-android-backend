package com.example.models

import kotlinx.serialization.Serializable

val coffeeList = mutableListOf<Customer>()

@Serializable
data class Coffee(
    val id: String,
    val name: String,
    val type: String,
    val price: Int,
    val weight: Int
)