package com.example

import com.example.models.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseInitializer {

    fun eraseSchema() {
        transaction {
            SchemaUtils.drop(Orders, Categories, Customers, Products, OrderItems)
        }
    }

    fun createSchema() {
        transaction {
            SchemaUtils.create(Orders, Categories, Customers, Products, OrderItems)
        }
    }

    fun feedDatabase() {
        transaction {
            Categories.insert { it[name] = "Coffee" }
            Categories.insert { it[name] = "Tea" }

            val customers = listOf(
                Customer(1, "Konrad", "K", "asd"),
                Customer(2, "Ala", "T", "asd2"),
                Customer(3, "Kapi", "k", "asd3"))
            Customers.batchInsert(customers) { customer: Customer ->
                this[Customers.id] = customer.id
                this[Customers.name] = customer.name
                this[Customers.surname] = customer.surname
                this[Customers.email] = customer.email
            }

            val orders = listOf(
                Order(1, 1),
                Order(2, 1),
                Order(3, 2),
            )
            Orders.batchInsert(orders) { order: Order ->
                this[Orders.id] = order.id
                this[Orders.customer_id] = order.customer_id
            }

            val orderitems = listOf(
                OrderItem(1, 1, 1, 4),
                OrderItem(2, 1, 3, 2),
                OrderItem(1, 2, 1, 8),
            )
            OrderItems.batchInsert(orderitems) { item ->
                this[OrderItems.order_id] = item.order_id
                this[OrderItems.category_id] = item.category_id
                this[OrderItems.product_id] = item.product_id
                this[OrderItems.amount] = item.amount
            }

            val products = listOf(
                Product(1, "Black Coffee", 11.50, 1),
                Product(2, "Red coffee", 9.70, 1),
                Product(3, "Ice coffee", 12.00, 1),
                Product(4, "Starbucks coffee", 11.00, 1),
                Product(5, "Ice tea", 7.00, 2),
                Product(6, "Green tea", 6.00, 2),
                Product(7, "Black tea", 5.50, 2)
            )
            Products.batchInsert(products) { i ->
                this[Products.id] = i.id
                this[Products.name] = i.name
                this[Products.price] = i.price
                this[Products.category_id] = i.category_id
            }
        }
    }

}