package com.example.routes

import io.ktor.application.*
import io.ktor.routing.*
import kotlin.text.get

fun Route.coffeeRouting() {
    route("/coffee") {
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

fun Application.registerCoffeeRoutes() {
    routing {
        coffeeRouting()
    }
}