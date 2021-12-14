package com.example.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Orders : Table("orders") {
    val id: Column<Int> = integer("id").autoIncrement()
    val customer_id : Column<Int> = reference("customer_id", Customers.id)
    override val primaryKey = PrimaryKey(id, name = "PK_Orders_Id")
}

data class Order(val id: Int, val customer_id: Int) {
    companion object {
        fun fromRow(row: ResultRow) = Order(
            id = row[Orders.id],
            customer_id = row[Orders.customer_id]
        )
    }

}
