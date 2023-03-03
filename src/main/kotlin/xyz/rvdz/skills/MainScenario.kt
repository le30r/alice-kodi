package xyz.rvdz.skills

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.catchall.CatchAllActivator
import com.justai.jaicf.activator.event.BaseEventActivator
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.yandexalice.activator.AliceIntentActivator
import com.justai.jaicf.channel.yandexalice.activator.alice
import com.justai.jaicf.channel.yandexalice.model.AliceEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import xyz.rvdz.client.KodiClient

class MainScenario {
    val mainScenario = Scenario {
        state("start") {
            activators {
                event(AliceEvent.START)
            }
            action {
                reactions.say("Привет! Я помогу вам управлять вашим плеером на Коди")
            }

        }

        state("volume") {
            activators {
                intent("setvol")
            }

            action {
                //reactions.say("Ок")
                activator.alice?.run {
                    val firstAmount = slots["to"]?.value
                    val value = Json.decodeFromString<Int>(firstAmount.toString())

                    runBlocking {
                        println("устанавливаю громкость на $firstAmount")
                        launch {
                            KodiClient().setVolume(value)
                        }
                        println("Громкость установлена")
                    }
                }
            }
        }


        state("mute") {
            activators {
                regex("выключи звук")
            }

            action {
              //  reactions.say("Ок")
                runBlocking {
                    KodiClient().mute(true)
                }

            }
        }

        state("unmute") {
            activators {
                regex("включи звук")
            }

            action {
               // reactions.say("Ок")
                runBlocking {
                    KodiClient().mute(false)
                }

            }
        }

        state("up") {
            activators {
                regex("вверх")
            }

            action {
              //  reactions.say("Ок")
                runBlocking {
                    KodiClient().input("Up")
                }
            }
        }
        state("down") {
            activators {
                regex("вниз")
            }

            action {
              //  reactions.say("Ок")
                runBlocking {
                    KodiClient().input("Down")
                }
            }
        }

        state("left") {
            activators {
                regex("влево")
            }

            action {
              //  reactions.say("Ок")
                runBlocking {
                    KodiClient().input("Left")
                }
            }
        }

        state("right") {
            activators {
                regex("вправо")
            }

            action {
             //   reactions.say("Ок")
                runBlocking {
                    KodiClient().input("Right")
                }
            }
        }

        state("select") {
            activators {
                regex("выбрать")
                regex("выбор")
            }

            action {
              //  reactions.say("Ок")
                runBlocking {
                    KodiClient().input("Select")
                }
            }
        }

        state("info") {
            activators {
                regex("инфо.+")

            }

            action {
               // reactions.say("Ок")
                runBlocking {
                    KodiClient().input("Info")
                }
            }
        }

        state("home") {
            activators {
                regex("главная")

            }

            action {
               // reactions.say("Ок")
                runBlocking {
                    KodiClient().input("Home")
                }
            }
        }

        state("back") {
            activators {
                regex("назад")

            }

            action {
               // reactions.say("Ок")
                runBlocking {
                    KodiClient().input("Back")
                }
            }
        }

        state("play") {
            activators {
                regex("играй")
                regex("пауза")
            }

            action {
                //reactions.say("Ок")
                runBlocking {
                    KodiClient().togglePause()
                }
            }
        }

    }


    val skill = BotEngine(
        scenario = mainScenario, activators = arrayOf(
            RegexActivator, BaseEventActivator, CatchAllActivator, AliceIntentActivator
        )
    )
}

