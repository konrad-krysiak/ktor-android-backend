package com.example.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table


object Products : Table("products") {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 50)
    val price: Column<Double> = double("price")
    val category: Column<Int> = reference("category_id", Categories.id)

    override val primaryKey = PrimaryKey(id, name = "PK_Products")
}

data class Product (
    val id: String,
    val name: String,
    val price: Int,
    val category: Int
)