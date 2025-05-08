package com.example.riot_elo.data

import com.example.riot_elo.models.User

object MyImage {
    val imageFaker = "https://cdnphoto.dantri.com.vn/MqUVuRtIdoo7LlU9oTTM1qybuMU=/2023/10/01/004834524797835225919c73401k166971614833-1696176422301.jpg"

    val userList = listOf(
        User(
            name = "Faker", avataImg = imageFaker, LP = 1200, Rank = "Kim Cương I",
            iDRank = "1"
        ),
        User(
            name = "Chovy",
            avataImg = "https://kenh14cdn.com/203336854389633024/2021/9/26/photo-1-1632633089708495215047.jpg",
            LP = 1150, Rank = "Kim Cương I",
            iDRank = "2"
        ),
        User(name = "Knight", avataImg = "https://gamek.mediacdn.vn/133514250583805952/2024/1/14/knight-lpl-2-1705233292317922667852.png", LP = 1100            , Rank = "Bạc I" ,iDRank = "3"),
        User(name = "ShowMaker", avataImg = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLpRJNJCNH-3ZJpQ0LBYYcxeEBLfMDvx_GCw&s", LP = 1050            , Rank = "Vàng I", iDRank = "4"),
        User(name = "Caps", avataImg = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTeH2-Mfnvy1d4SWUrQxfE2Laf09Xi5iQYLxg&s", LP = 1025            , Rank = "Back Kim III", iDRank = "5")
    )

}