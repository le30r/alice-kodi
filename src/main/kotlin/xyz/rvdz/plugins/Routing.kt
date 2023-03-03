package xyz.rvdz.plugins

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.catchall.CatchAllActivator
import com.justai.jaicf.activator.event.BaseEventActivator
import com.justai.jaicf.activator.event.event
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.http.httpBotRouting
import com.justai.jaicf.channel.yandexalice.AliceChannel
import com.justai.jaicf.channel.yandexalice.alice
import com.justai.jaicf.channel.yandexalice.model.AliceEvent
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {
    routing {
        httpBotRouting("/" to AliceChannel(skill))
        get("/") {
            call.respondText("Hello, world!")
        }
    }
}
val MainScenario = Scenario {
    state("main") {
        activators {
            event(AliceEvent.START)
        }
        action {
            reactions.say(request.input)
        }
    }
}

val skill = BotEngine(
    scenario = MainScenario,
    activators = arrayOf(
        RegexActivator,
        BaseEventActivator,
        CatchAllActivator
    )
)


