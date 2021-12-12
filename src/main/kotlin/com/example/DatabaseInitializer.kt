package com.example

import com.example.models.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseInitializer {

    fun eraseSchema() {
        transaction {
            SchemaUtils.drop(Orders, Categories, Customers, Coffee, OrderItems)
        }
    }

    fun createSchema() {
        transaction {
            SchemaUtils.create(Orders, Categories, Customers, Coffee, OrderItems)
        }
    }

}