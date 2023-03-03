package xyz.rvdz

import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.jetty.*
import io.ktor.server.netty.*
import xyz.rvdz.plugins.*

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
}
