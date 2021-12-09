package com.example

import com.example.plugins.configureRouting
import com.example.routes.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database

fun main() {

    Database.connect("jdbc:sqlite:file:test?mode=memory&cache=shared", "org.sqlite.JDBC")

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
                disableHtmlEscaping()
            }
        }
        registerCustomerRoutes()
        registerOrderRoutes()
        registerCoffeeRoutes()
        registerEnergyDrinkRoutes()
        registerYerbaRoutes()
        //configureRouting()
    }.start(wait = true)
}
