package com.example.models

import kotlinx.serialization.Serializable

val customerStorage = mutableListOf<Customer>(
    Customer("1", "Konrad", "K", "konrad@1"),
    Customer("2", "Ala", "T", "ala@1"),
    Customer("3", "Kuba", "K", "kuba@1")
)

@Serializable
data class Customer(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String
)