package com.example.routes

import com.example.models.*
import com.example.models.Customers.name
//import com.example.models.customerStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.categoryRouting() {
    route("/categories") {
        get {
            val categories = transaction {
                Categories.selectAll().map { Category.fromRow(it) }
            }
            call.respond(categories)
        }

        get("{id}") {
            val id = call.parameters["id"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest, "No parameters found.")
            val category = transaction {
                Categories.select { Categories.id eq id }.map { Category.fromRow(it) }
            }
            call.respond(category)
        }

        post {
            val category = call.receive<Category>()
            val id = transaction {
                Categories.insert {
                    it[name] = category.name
                } get Categories.id
            }
            call.respond(id)
        }

        delete("{id}") {
            val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest, "No id parameter provided.")
            val success = transaction {
                Categories.deleteWhere { Categories.id eq id }
            }
            call.respond(success)
        }
    }
}

fun Application.registerCategoryRoutes() {
    routing {
        categoryRouting()
    }
}