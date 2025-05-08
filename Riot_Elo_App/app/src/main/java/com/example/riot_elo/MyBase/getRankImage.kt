package com.example.riot_elo.MyBase


import com.example.riot_elo.R
fun getRankImage(idRank: Int): Int {
    return when (idRank) {
        1 -> R.drawable.rank_thachdau        // rank thấp nhất
        2 -> R.drawable.rank_daicaothu
        3 -> R.drawable.rank_cao_thu
        4 -> R.drawable.rank_kimcuong
        5 -> R.drawable.rank_back_kim
        6 -> R.drawable.rank_vang
        7 -> R.drawable.rank_bac
        8 -> R.drawable.rank_dong

        else -> R.drawable.rank_vang     // fallback nếu không khớp
    }
}
