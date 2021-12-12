package com.example.routes

import com.example.models.Orders
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


fun Route.orderRouting() {
    route("/order") {
        get {
            transaction {
                for(item in Orders.selectAll()) print(item)
            }
        }
        get("{id}") {

        }
        post {
            transaction {
                Orders.insert {  }
            }
        }
        delete("{id}") {

        }
    }
}

fun Application.registerOrderRoutes() {
    routing {
        orderRouting()
    }
}