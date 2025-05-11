package com.example.riot_elo.models

data class MatchInfo(
    val id: String,
    val matchId: String,
    val userName: String,
    val champion: String,
    val imageChampion:String,
    val position: String,
    val gold: String,
    val kills: String,
    val deaths: String,
    val assists: String,
    val isWin : Boolean,
)

fun getTeamsWinners(matches: List<MatchInfo>): List<MatchInfo> {
    return matches.filter { it.isWin }
}
fun getTeamsLosers(matches: List<MatchInfo>): List<MatchInfo> {
    return matches.filter { !it.isWin }
}

fun totalGold(matches: List<MatchInfo>): Int {
    return matches.sumOf { it.gold.toIntOrNull() ?: 0 }
}

fun totalKills(matches: List<MatchInfo>): Int {
    return matches.sumOf { it.kills.toIntOrNull() ?: 0 }
}

fun totalAssists(matches: List<MatchInfo>): Int {
    return matches.sumOf { it.assists.toIntOrNull() ?: 0 }
}

fun totalDeaths(matches: List<MatchInfo>): Int {
    return matches.sumOf { it.deaths.toIntOrNull() ?: 0 }
}




val dummyMatchInfoList = listOf(
    MatchInfo(
        id = "1",
        matchId = "MATCH_001",
        userName = "PlayerOne",
        champion = "Ahri",
        imageChampion = "https://cdnphoto.dantri.com.vn/MqUVuRtIdoo7LlU9oTTM1qybuMU=/2023/10/01/004834524797835225919c73401k166971614833-1696176422301.jpg",
        position = "mid",
        gold = "12500",
        kills = "8",
        deaths = "2",
        assists = "10",
        isWin = false
    ),
    MatchInfo(
        id = "2",
        matchId = "MATCH_002",
        userName = "PlayerTwo",
        champion = "Garen",
        imageChampion = "https://cdnphoto.dantri.com.vn/MqUVuRtIdoo7LlU9oTTM1qybuMU=/2023/10/01/004834524797835225919c73401k166971614833-1696176422301.jpg",
        position = "Top",
        gold = "11000",
        kills = "5",
        deaths = "4",
        assists = "7",
        isWin = false
    ),
    MatchInfo(
        id = "3",
        matchId = "MATCH_003",
        userName = "PlayerThree",
        champion = "Jinx",
        imageChampion = "https://example.com/champions/jinx.png",
        position = "ADC",
        gold = "13500",
        kills = "12",
        deaths = "3",
        assists = "9",
        isWin = true
    ),
    MatchInfo(
        id = "4",
        matchId = "MATCH_004",
        userName = "PlayerFour",
        champion = "Leona",
        imageChampion = "https://example.com/champions/leona.png",
        position = "Support",
        gold = "8500",
        kills = "2",
        deaths = "5",
        assists = "15",
        isWin = true
    ),
    MatchInfo(
        id = "5",
        matchId = "MATCH_005",
        userName = "PlayerFive",
        champion = "Zed",
        imageChampion = "https://example.com/champions/zed.png",
        position = "Jungle",
        gold = "14000",
        kills = "10",
        deaths = "1",
        assists = "6",
        isWin = true
    )
)