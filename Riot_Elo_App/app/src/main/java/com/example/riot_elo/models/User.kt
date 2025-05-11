package com.example.riot_elo.models

data class User(
    val id: String,
    val name: String,
    val kElo: String?,
    val lp: String?,
    val win: String,
    val rankLever: String?,
    val imageUrl: String?,
    val rank:String?,

) {
    fun lpRank(): Int {
        val lpValue = lp?.toIntOrNull() ?: return -1
        return when (lpValue) {
            in 0..200 -> 8   // Đồng
            in 201..400 -> 7 // Bạc
            in 401..600 -> 6 // Vàng
            in 601..1000 -> 5 // Bạch Kim
            in 1000..2000 -> 4 // Kim Cương
            in 2000..10000 -> 3 // Cao Thủ
            in 1201..1400 -> 2 // Đại Cao Thủ
            in 1401..Int.MAX_VALUE -> 1 // Thách Đấu
            else -> 8
        }
    }

}


fun setRankForUsers(users: List<User>): List<User> {
    // Chuyển LP sang số
    val usersWithLp = users.map { it.copy(lp = (it.lp?.toIntOrNull() ?: 0).toString()) }

    // Sắp xếp người dùng theo LP giảm dần
    val sortedUsers = usersWithLp.sortedByDescending { it.lp }

    // Phân loại Đại Cao Thủ và Thách Đấu
    val top30 = sortedUsers.take(30)  // 30 người cao nhất là Đại Cao Thủ
    val top10 = top30.take(10)        // 10 người cao nhất trong Đại Cao Thủ là Thách Đấu

    return usersWithLp.map { user ->
        val rank = when {
            user.lp!!.toInt() <= 200 -> "Đồng ${getRankLevel(user.lp, 0..200, 3)}"
            user.lp.toInt() <= 500 -> "Bạc ${getRankLevel(user.lp, 201..500, 4)}"
            user.lp.toInt() <= 1000 -> "Vàng ${getRankLevel(user.lp, 501..1000, 5)}"
            user.lp.toInt() <= 2000 -> "Bạch Kim ${getRankLevel(user.lp, 1001..2000, 4)}"
            user.lp.toInt() <= 3000 -> "Kim Cương ${getRankLevel(user.lp, 2001..3000, 5)}"
            user.lp.toInt() > 3000 -> "Cao Thủ}"
            top30.contains(user) -> "Đại Cao Thủ"
            top10.contains(user) -> "Thách Đấu"
            else -> "Không xác định"
        }

        user.copy(rankLever = rank)  // Gán rank cho mỗi người
    }
}

// Hàm trợ giúp để tính cấp rank dựa trên LP và phân chia mức rank
fun getRankLevel(lp: String?, range: IntRange, totalLevels: Int): String {
    val rangeSize = range.last - range.first
    val step = rangeSize / totalLevels
    val lpInt = lp?.toIntOrNull() ?: 0  // Convert LP to integer

    val level = ((lpInt - range.first) / step) + 1
    return "$level"
}







