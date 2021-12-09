package com.example.routes

import io.ktor.application.*
import io.ktor.routing.*


fun Route.orderRouting() {
    route("/order") {
        get {

        }
        get("{id") {

        }
        post {

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