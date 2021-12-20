package com.example.models


import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table


object Products : Table("products") {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 50)
    val price: Column<Double> = double("price")
    val category_id: Column<Int> = reference("category_id", Categories.id)

    override val primaryKey = PrimaryKey(id, name = "PK_Products")
}

data class Product (
    val id: Int,
    val name: String,
    val price: Double,
    val category_id: Int
) {
    companion object {
        fun fromRow(row: ResultRow) = Product(
                id = row[Products.id],
                name = row[Products.name],
                price = row[Products.price],
                category_id = row[Products.category_id]
        )
    }
}