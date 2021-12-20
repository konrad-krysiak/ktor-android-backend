package com.example.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Customers : Table("customers") {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 100)
    val surname: Column<String> = varchar("surname", 100)
    val email: Column<String> = varchar("email", 50)
    override val primaryKey = PrimaryKey(id, name = "PK_Customers")
}

data class Customer(
    val id: Int,
    val name: String,
    val surname: String,
    val email: String
)
{
    companion object {
        fun fromRow(resultRow: ResultRow) = Customer(
            id = resultRow[Customers.id],
            name = resultRow[Customers.name],
            surname = resultRow[Customers.surname],
            email = resultRow[Customers.surname]
        )
    }
}