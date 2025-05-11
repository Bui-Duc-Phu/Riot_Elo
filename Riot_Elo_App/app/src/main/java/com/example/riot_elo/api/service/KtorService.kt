package online.devphu.truyenhub.Api.service

import com.example.riot_elo.api.parse.parseMatchInfo
import com.example.riot_elo.api.parse.parseUser
import com.example.riot_elo.api.parse.parseUserDetail
import com.example.riot_elo.models.MatchInfo
import com.example.riot_elo.models.User
import com.example.riot_elo.models.UserDetial
import online.devphu.truyenhub.Api.repository.KtorRepository
import org.json.JSONArray
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KtorService @Inject constructor(
    private val ktorRepository: KtorRepository
) {


    suspend fun getAllUser(): List<User>? {
        return parseUser(ktorRepository.getAllUser() ?: JSONArray())
    }

    suspend fun getDetialByID(id:String): UserDetial? {
        return parseUserDetail(ktorRepository.getDetialByID(id) ?: JSONArray())
    }

    suspend fun getAllMatchesByIdUser(id:String): List<MatchInfo>? {
        return parseMatchInfo(ktorRepository.getAllMatchesByIdUser(id) ?: JSONArray())
    }

    suspend fun getAllTeamsMatcherByIdMatch(id:String): List<MatchInfo>? {
        return parseMatchInfo(ktorRepository.getAllTeamsMatcherByIdMatch(id) ?: JSONArray())
    }







}