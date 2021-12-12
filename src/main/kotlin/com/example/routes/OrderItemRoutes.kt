package com.example.routes

import com.example.models.Customer
import com.example.models.OrderItem
import com.example.models.OrderItems
//import com.example.models.customerStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.orderItemsRouting() {
    route("orderitem") {
        get("{id}"){

        }
        get{
            call.respond(HttpStatusCode.OK, OrderItems.selectAll())
        }

        post {
            val data = call.receive<OrderItem>()
            transaction {
                OrderItems.insert {
                    it[order_id] = data.order_id
                    it[category_id] = data.category_id
                    it[product_id] = data.product_id
                    it[amount] = data.amount
                }
            }
            call.respond(HttpStatusCode.OK)
        }
        delete("{id}") {

        }
    }
}

fun Application.registerOrderItemsRoutes() {
    routing {
        orderItemsRouting()
    }
}