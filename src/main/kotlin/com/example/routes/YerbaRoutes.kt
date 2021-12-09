package com.example.routes

import io.ktor.application.*
import io.ktor.routing.*

fun Route.yerbaRouting() {
    route("/yerba") {
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

fun Application.registerYerbaRoutes() {
    routing {
        yerbaRouting()
    }
}