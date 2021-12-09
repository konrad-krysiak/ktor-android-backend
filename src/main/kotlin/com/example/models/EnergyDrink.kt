package com.example.models

import kotlinx.serialization.Serializable

val energyDrinkList = mutableListOf<Customer>()

@Serializable
data class EnergyDrink(
    val id: String,
    val name: String,
    val type: String,
    val price: Int,
    val capacity: Int
)