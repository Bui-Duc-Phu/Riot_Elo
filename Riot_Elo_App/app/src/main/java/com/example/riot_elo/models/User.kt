package com.example.riot_elo.models

data class User(
    val id: String,
    val name: String,
    val kElo: String?,
    val lp: String?,
    val win: String,
    val rankLever: String?,
    val imageUrl: String?
) {
    fun lpRank(): Int {
        val lpValue = lp?.toIntOrNull() ?: return -1
        return when (lpValue) {
            in 0..200 -> 8   // Đồng
            in 201..400 -> 7 // Bạc
            in 401..600 -> 6 // Vàng
            in 601..800 -> 5 // Bạch Kim
            in 801..1000 -> 4 // Kim Cương
            in 1001..1200 -> 3 // Cao Thủ
            in 1201..1400 -> 2 // Đại Cao Thủ
            in 1401..Int.MAX_VALUE -> 1 // Thách Đấu
            else -> 8
        }
    }
}



