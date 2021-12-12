package com.example.routes

import com.example.models.Customer
import com.example.models.OrderItem
//import com.example.models.customerStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

val customerStorage = ArrayList<Int>()


fun Route.customerRouting() {
    route("/customer") {
        get {
            if(customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText("No customers found!", status = HttpStatusCode.NotFound)
            }
        }
        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val customer =
                call.respondText(" correctly", status = HttpStatusCode.Accepted)
            call.respond(customer)
        }
        post {
            val customer = call.receive<Customer>()
            customerStorage.add(1)
            call.respondText("Customer stored successfully", status = HttpStatusCode.Created)
        }
        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            call.respondText("Customer removed correctly", status = HttpStatusCode.Accepted)
        }
    }
}

fun Application.registerCustomerRoutes() {
    routing {
        customerRouting()
    }
}