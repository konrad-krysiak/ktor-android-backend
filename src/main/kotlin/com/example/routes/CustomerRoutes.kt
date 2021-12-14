package com.example.routes

import com.example.models.Customer
import com.example.models.Customers
import com.example.models.Customers.name
import com.example.models.OrderItem
//import com.example.models.customerStorage
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

fun Route.customerRouting() {
    route("/customer") {
        get("{id}") {
            val id = call.parameters["id"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest, "No parameters found.")
            val customer = transaction {
                Customers.select { Customers.id eq id }.map { Customer.fromRow(it) }
            }
            call.respond(customer)
        }
        get {
            val customers = transaction {
                Customers.selectAll().map { Customer.fromRow(it) }
            }
            call.respond(customers)
        }
        post {
            val customer = call.receive<Customer>()
            val id = transaction {
                Customers.insert {
                    it[name] = customer.name
                    it[surname] = customer.surname
                    it[email] = customer.email
                } get Customers.id
            }
            call.respond(id)
        }
        delete("{id}") {
            val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest, "No id parameter provided.")
            val success = transaction {
                Customers.deleteWhere { Customers.id eq id }
            }
            call.respond(success)
        }
    }
}

fun Application.registerCustomerRoutes() {
    routing {
        customerRouting()
    }
}