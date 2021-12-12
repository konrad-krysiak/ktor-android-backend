package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull
import org.jetbrains.exposed.sql.Table

//val orderStorage = listOf(
//    com.example.models.Order(
//        "2020-04-06-01", listOf(
//            OrderItem("Ham Sandwich", 2, 5.50),
//            OrderItem("Water", 1, 1.50),
//            OrderItem("Beer", 3, 2.30),
//            OrderItem("Cheesecake", 1, 3.75)
//        )
//    ),
//    com.example.models.Order(
//        "2020-04-03-01", listOf(
//            OrderItem("Cheeseburger", 1, 8.50),
//            OrderItem("Water", 2, 1.50),
//            OrderItem("Coke", 2, 1.76),
//            OrderItem("Ice Cream", 1, 2.35)
//        )
//    )
//)

object Orders : Table("orders") {
    val id: Column<Int> = integer("id").autoIncrement()
    val customer_id : Column<Int> = reference("customer_id", Customers.id)
    override val primaryKey = PrimaryKey(id, name = "PK_Orders_Id")
}

object OrderItems: Table("orderItems") {
    val order_id: Column<Int> = reference("order_id", Orders.id)
    val category_id: Column<Int> = reference("category_id", Categories.id)
    val product_id: Column<Int> = reference("product_id", Products.id)
    val amount: Column<Int> = integer("amount")
    override val primaryKey = PrimaryKey(order_id, category_id, product_id, name = "PK_OrderItems")
}


data class OrderItem(val order_id: Int, val category_id: Int, val product_id: Int, val amount: Int)


data class Order(val number: String, val contents: List<OrderItem>)
