package com.example.routes

import io.ktor.application.*
import io.ktor.routing.*

fun Route.energyDrinkRouting() {
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

fun Application.registerEnergyDrinkRoutes() {
    routing {
        energyDrinkRouting()
    }
}