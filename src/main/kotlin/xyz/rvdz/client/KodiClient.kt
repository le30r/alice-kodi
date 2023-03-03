package xyz.rvdz.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*

class KodiClient {
    val client = HttpClient(CIO)

    suspend fun setVolume(amount: Int) =
        client.post<String>("http://10.8.0.2:8080/jsonrpc") {
            contentType(ContentType.Application.Json)
            body = """
                {
                    "jsonrpc": "2.0",
                    "method": "Application.SetVolume",
                    "params" : {
                    "volume": $amount
                },
                    "id": 1
                }
            """
        }

    suspend fun mute(isMuted: Boolean) =
        client.post<String>("http://10.8.0.2:8080/jsonrpc") {
            contentType(ContentType.Application.Json)
            body = """
                {
                "jsonrpc": "2.0",
                "method": "Application.SetMute",
                "params" : {
                "mute": $isMuted
                },
                "id": 1
                }
            """
        }

    suspend fun input(direction: String) =
        client.post<String>("http://10.8.0.2:8080/jsonrpc") {
            contentType(ContentType.Application.Json)
            body = """
               {
                    "jsonrpc": "2.0",
                    "method": "Input.$direction",
                    "id": 1
                }
            """
        }

    suspend fun togglePause() =
        client.post<String>("http://10.8.0.2:8080/jsonrpc") {
            contentType(ContentType.Application.Json)
            body = """
               {
                    "jsonrpc": "2.0",
                    "method": "Player.PlayPause",
                    "params":{
                        "playerid": 1,
                        "play": "toggle"
                    },
                    "id": 1
               }
            """
        }


}
