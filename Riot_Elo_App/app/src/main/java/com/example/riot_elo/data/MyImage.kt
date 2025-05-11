package com.example.riot_elo.data

import com.example.riot_elo.models.User

object MyImage {
    private const val imageFaker = "https://cdnphoto.dantri.com.vn/MqUVuRtIdoo7LlU9oTTM1qybuMU=/2023/10/01/004834524797835225919c73401k166971614833-1696176422301.jpg"

    val userList = listOf(
        User(
            id = "1",
            name = "Faker",
            kElo = null,
            lp = "1200",
            win = "0",
            rankLever = "Kim Cương I",
            imageUrl = imageFaker
        ),
        User(
            id = "2",
            name = "Chovy",
            kElo = null,
            lp = "1150",
            win = "0",
            rankLever = "Kim Cương I",
            imageUrl = "https://kenh14cdn.com/203336854389633024/2021/9/26/photo-1-1632633089708495215047.jpg"
        ),
        User(
            id = "3",
            name = "Knight",
            kElo = null,
            lp = "1100",
            win = "0",
            rankLever = "Bạc I",
            imageUrl = "https://gamek.mediacdn.vn/133514250583805952/2024/1/14/knight-lpl-2-1705233292317922667852.png"
        ),
        User(
            id = "4",
            name = "ShowMaker",
            kElo = null,
            lp = "1050",
            win = "0",
            rankLever = "Vàng I",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLpRJNJCNH-3ZJpQ0LBYYcxeEBLfMDvx_GCw&s"
        ),
        User(
            id = "5",
            name = "Caps",
            kElo = null,
            lp = "1025",
            win = "0",
            rankLever = "Bạch Kim III",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTeH2-Mfnvy1d4SWUrQxfE2Laf09Xi5iQYLxg&s"
        )
    )
}
