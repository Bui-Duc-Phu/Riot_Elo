package com.example.riot_elo.api.parse

import com.example.riot_elo.models.MatchInfo
import com.example.riot_elo.models.User
import com.example.riot_elo.models.UserDetial
import org.json.JSONArray
import org.json.JSONObject


fun parseMatchInfo(jsonArray: JSONArray): List<MatchInfo> {
    val list = mutableListOf<MatchInfo>()
    for (i in 0 until jsonArray.length()) {
        val obj = jsonArray.getJSONObject(i)
        val matchInfo = MatchInfo(
            id = obj.getString("id"),
            matchId = obj.getString("matchId"),
            userName = obj.getString("userName"),
            champion = obj.getString("champion"),
            imageChampion = obj.getString("imageChampion"),
            position = obj.getString("position"),
            gold = obj.getString("gold"),
            kills = obj.getString("kills"),
            deaths = obj.getString("deaths"),
            assists = obj.getString("assists"),
            isWin = if(obj.getString("isWin").equals("1")) true else false
        )
        list.add(matchInfo)
    }
    return list
}


fun parseUser(jsonArray: JSONArray): List<User> {
    val list = mutableListOf<User>()
    for (i in 0 until jsonArray.length()) {
        val obj = jsonArray.getJSONObject(i)
        val user = User(
            id = obj.optString("id"),
            name = obj.optString("name"),
            kElo = obj.optString("kElo"),  // Đây là String?, nên có thể trả về null nếu không có giá trị
            lp = obj.optString("lp"),  // Cũng có thể null
            win = obj.optString("win"),
            rankLever = obj.optString("rankLever"),
            imageUrl = obj.optString("imageUrl"),
            rank = obj.optString("rank")
        )
        list.add(user)
    }
    return list
}

fun parseUserDetail(jsonArray: JSONArray): UserDetial {

    if (jsonArray.length() == 0) {
        throw IllegalArgumentException("Không có dữ liệu người dùng trong JSON.")
    }
    val obj = jsonArray.getJSONObject(0)

    return UserDetial(
        id = obj.optString("id"),
        name = obj.optString("name"),
        kElo = obj.optString("kElo"),
        lp = obj.optString("lp"),
        win = obj.optString("win"),
        rankLever = obj.optString("rankLever"),
        imageUrl = obj.optString("imageUrl"),
        rank = obj.optString("rank"),
        totalMatches = obj.optString("totalMatches"),
        totalWins = obj.optString("totalWins"),
        winRate = obj.optString("winRate")
    )
}



