package com.example.routes

import com.example.models.Customer
import com.example.models.Customers
import com.example.models.Order
import com.example.models.Orders
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


fun Route.orderRouting() {
    route("/orders") {
        get {
            val orders = transaction {
                Orders.selectAll().map{ Order.fromRow(it) }
            }
            call.respond(orders)
        }

        get("{id}") {
            val id = call.parameters["id"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest, "No id parameter provided.")
            val result = transaction {
                Orders.select { Orders.id eq id }.map { Order.fromRow(it) }
            }
            call.respond(result)
        }

        post {
            val order = call.receive<Order>()
            val id = transaction {
                Orders.insert {
                    it[customer_id] = order.customer_id
                } get Orders.id
            }
            call.respond(id)
        }

        delete("{id}") {
            val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest, "No id parameter provided.")
            val success = transaction {
                Orders.deleteWhere { Orders.id eq id }
            }
            call.respond(success)
        }
    }
}

fun Application.registerOrderRoutes() {
    routing {
        orderRouting()
    }
}