package online.devphu.truyenhub.Api.repository

import io.ktor.http.HttpMethod
import online.devphu.truyenhub.Api.config.KtorDriver
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KtorRepository @Inject constructor(
      private  val ktorDriver: KtorDriver
) {
    suspend fun getAllUser() : JSONArray?{
        return ktorDriver.Res(
            HttpMethod.Get,
            "/main/getAllUser"
        )
    }

    suspend fun getDetialByID(id: String): JSONArray? {
        val jsonBody = JSONObject().apply {
            put("id", id)
        }.toString()
        println("Sending request with body: $jsonBody")  // Add this line
        return ktorDriver.Res(
            method = HttpMethod.Post,
            "/main/getUserDetailById",
            jsonBody = jsonBody
        )
    }

    suspend fun getAllMatchesByIdUser(id: String): JSONArray? {
        val jsonBody = JSONObject().apply {
            put("id", id)
        }.toString()
        println("Sending request with body: $jsonBody")
        return ktorDriver.Res(
            method = HttpMethod.Post,
            "/main/getAllMatchesByIdUser",
            jsonBody = jsonBody
        )
    }

    suspend fun getAllTeamsMatcherByIdMatch(id: String): JSONArray? {
        val jsonBody = JSONObject().apply {
            put("id", id)
        }.toString()
        println("Sending request with body: $jsonBody")
        return ktorDriver.Res(
            method = HttpMethod.Post,
            "/main/getAllTeamsMatcherByIdMatch",
            jsonBody = jsonBody
        )
    }






}
