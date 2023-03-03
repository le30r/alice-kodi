package xyz.rvdz.plugins


import com.justai.jaicf.channel.http.httpBotRouting
import com.justai.jaicf.channel.yandexalice.AliceChannel

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

import xyz.rvdz.skills.MainScenario

fun Application.configureRouting() {
    val scenario = MainScenario()
    routing {
        httpBotRouting("/" to AliceChannel(scenario.skill))
        get("/test") {
            call.respondText("Hello, world!")
        }
    }
}
