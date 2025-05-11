package online.devphu.truyenhub.Api.config

import android.content.Context
import com.example.riot_elo.api.config.ktorRestApi
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod

import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class KtorDriver @Inject constructor(
    private val client: HttpClient,
    @ApplicationContext private val context: Context
) {
    val baseUrl = "http://192.168.100.160:3001"
    suspend fun Res(
        method: HttpMethod = HttpMethod.Post,
        endpoint: String,
        jsonBody: String? = null,
        params: Map<String, String>? = null
    ): JSONArray? {
        val res = ktorRestApi(
            client = client,
            method = method,
            baseUrl = baseUrl,
            endpoint = endpoint,
            jsonBody = jsonBody,
            params = params
        )

        return try {
            val mainObject = res?.getJSONObject(0)
            println("data $mainObject")

            val result = mainObject?.get("result")
            when (result) {
                is JSONArray -> result
                is JSONObject -> JSONArray().put(result)
                else -> null
            }
        } catch (e: Exception) {
            println("Bug Res fun : "+ e.message)
            e.printStackTrace()
            null
        }
    }

}