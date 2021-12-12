package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

val coffeeList = mutableListOf<Customer>()


object Coffee : Table("coffee") {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 50)
    val price: Column<Double> = double("price")
    val category: Column<Int> = reference("category_id", Categories.id)
    override val primaryKey = PrimaryKey(id, name = "PK_Coffee_Id")
}


@Serializable
data class Coffeee (
    val id: String,
    val name: String,
    val price: Int,
    val category: Int
)