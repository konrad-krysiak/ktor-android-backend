package com.example.routes

import com.example.models.Customer
import com.example.models.Customers
import com.example.models.OrderItem
import com.example.models.OrderItems
import com.example.models.OrderItems.order_id
//import com.example.models.customerStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.orderItemsRouting() {
    route("orderitem") {
        get{
            val order_id = call.parameters.get("order_id")
            val category_id = call.parameters.get("category_id")
            val product_id = call.parameters.get("product_id")
            if(order_id == null || category_id == null || product_id == null) {
                val orderitems = transaction {
                    OrderItems.selectAll().map { OrderItem.fromRow(it) }
                }
                call.respond(orderitems)
            } else {
                val orderitem = transaction {
                    OrderItems.select (
                         (OrderItems.order_id eq order_id.toInt()) and
                                (OrderItems.category_id eq category_id.toInt()) and
                                (OrderItems.product_id eq product_id.toInt())
                    ).map { OrderItem.fromRow(it) }
                }
                call.respond(orderitem)
            }
        }

        post {
            val orderitem = call.receive<OrderItem>()
            transaction {
                OrderItems.insert {
                    it[order_id] = orderitem.order_id
                    it[category_id] = orderitem.category_id
                    it[product_id] = orderitem.product_id
                    it[amount] = orderitem.amount
                }
            }
            call.respond(HttpStatusCode.OK, "OrderItem added.")
        }
        delete {
            val order_id = call.parameters.get("order_id")?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest, "Not enough parameters.")
            val category_id = call.parameters.get("category_id")?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest, "Not enough parameters.")
            val product_id = call.parameters.get("product_id")?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest, "Not enough parameters.")
            transaction {
                OrderItems.deleteWhere {
                            (OrderItems.order_id eq order_id) and
                            (OrderItems.category_id eq category_id) and
                            (OrderItems.product_id eq product_id)
                }
            }
            call.respond(HttpStatusCode.OK, "OrderItem deleted.")
        }
    }
}

fun Application.registerOrderItemsRoutes() {
    routing {
        orderItemsRouting()
    }
}