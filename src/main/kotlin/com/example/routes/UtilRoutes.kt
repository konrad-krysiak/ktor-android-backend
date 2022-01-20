package com.example.routes

import com.example.models.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.utilRouting() {

    get("/orders/customers/{id}") {
        val id = call.parameters["id"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest, "No id parameter provided.")
        val result = transaction {
            Orders.select { Orders.customer_id eq id }.map { Order.fromRow(it) }
        }
        call.respond(result)
    }

    get("/orderitems/orders/{id}") {
        val id = call.parameters["id"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest, "No id parameter provided.")
        val result = transaction {
            OrderItems.select { OrderItems.order_id eq id }.map { OrderItem.fromRow(it) }
        }
        call.respond(result)
    }

    get("/orderitems/categories/{id}") {
        val id = call.parameters["id"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest, "No id parameter provided.")
        val result = transaction {
            OrderItems.select { OrderItems.category_id eq id }.map { OrderItem.fromRow(it) }
        }
        call.respond(result)
    }

    get("/orderitems/products/{id}") {
        val id = call.parameters["id"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest, "No id parameter provided.")
        val result = transaction {
            OrderItems.select { OrderItems.product_id eq id }.map { OrderItem.fromRow(it) }
        }
        call.respond(result)
    }

}

fun Application.registerUtilRoutes() {
    routing {
        utilRouting()
    }
}