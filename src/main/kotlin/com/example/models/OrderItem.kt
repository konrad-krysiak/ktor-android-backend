package com.example.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table


// On cascade delete no need. Data is good
object OrderItems: Table("orderItems") {
    val order_id: Column<Int> = reference("order_id", Orders.id)
    val category_id: Column<Int> = reference("category_id", Categories.id)
    val product_id: Column<Int> = reference("product_id", Products.id)
    val amount: Column<Int> = integer("amount")
    override val primaryKey = PrimaryKey(order_id, category_id, product_id, name = "PK_OrderItems")
}

data class OrderItem(
    val order_id: Int,
    val category_id: Int,
    val product_id: Int,
    val amount: Int) {
    companion object {
        fun fromRow(resultRow: ResultRow) = OrderItem(
            order_id = resultRow[OrderItems.order_id],
            category_id = resultRow[OrderItems.category_id],
            product_id = resultRow[OrderItems.product_id],
            amount = resultRow[OrderItems.amount]
        )
    }
}