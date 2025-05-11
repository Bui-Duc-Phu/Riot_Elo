package com.example.riot_elo.api.config

import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import org.json.JSONArray
import org.json.JSONObject

suspend fun ktorRestApi(
    client: HttpClient,
    method: HttpMethod = HttpMethod.Post,
    baseUrl: String,
    endpoint: String,
    jsonBody: String? = null,
    params: Map<String, String>? = null
): JSONArray? {
    val url = "$baseUrl$endpoint"

    try {
        val response = when (method) {
            HttpMethod.Get -> client.get(url) {
                params?.forEach { (key, value) ->
                    parameter(key, value)
                }
            }
            HttpMethod.Post -> client.post(url) {
                headers {
                    append(HttpHeaders.ContentType, "application/json")
                }
                jsonBody?.let { setBody(it) }
            }
            HttpMethod.Put -> client.put(url) {
                headers {
                    append(HttpHeaders.ContentType, "application/json")
                }
                jsonBody?.let { setBody(it) }
            }
            HttpMethod.Delete -> client.delete(url) {
                headers {
                    append(HttpHeaders.ContentType, "application/json")
                }
                jsonBody?.let { setBody(it) }
            }
            else -> throw IllegalArgumentException("Unsupported HTTP method: $method")
        }

        val responseBody = response.bodyAsText()
        val jsonObject = JSONObject(responseBody)

        return if (jsonObject.has("records")) {
            val recordsArray = jsonObject.getJSONArray("records")
            JSONArray().apply {
                for (i in 0 until recordsArray.length()) {
                    put(recordsArray.getJSONObject(i))
                }
            }
        } else {
            JSONArray().apply {
                put(jsonObject)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        println("Error: ${e.message}")
        return null
    }
}
