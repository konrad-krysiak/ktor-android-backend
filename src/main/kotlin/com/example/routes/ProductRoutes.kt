package com.example.routes

import com.example.models.Customer
import com.example.models.Customers
import com.example.models.Product
import com.example.models.Products
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.productRouting() {
    route("/products") {
        get {
            val products = transaction {
                Products.selectAll().map { Product.fromRow(it) }
            }
            call.respond(products)
        }

        get("{id}") {
            val id = call.parameters["id"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest, "No parameters found.")
            val product = transaction {
                Products.select { Products.id eq id }.map { Product.fromRow(it) }
            }
            call.respond(product)
        }

        post {
            val product = call.receive<Product>()
            val id = transaction {
                Products.insert {
                    it[name] = product.name
                    it[price] = product.price
                    it[category_id] = product.category_id
                } get Products.id
            }
            call.respond(id)
        }

        delete("{id}") {
            val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest, "No id parameter provided.")
            val success = transaction {
                Products.deleteWhere { Products.id eq id }
            }
            call.respond(success)
        }

    }
}

fun Application.registerProductRoutes() {
    routing {
        productRouting()
    }
}