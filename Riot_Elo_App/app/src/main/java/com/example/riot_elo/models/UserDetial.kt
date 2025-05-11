package com.example.riot_elo.models

data class UserDetial(
    val id: String,
    val name: String,
    val kElo: String?,
    val lp: String?,
    val win: String,
    val rankLever: String?,
    val imageUrl: String?,
    val rank:String?,
    val totalMatches:String?,
    val totalWins:String?,
    val winRate:String?,
    )

val imageFaker = "https://encrypted-tbn0.gstatic.com/licensed-image?q=tbn:ANd9GcQmJ6wBdIGtpfL1o8vEPQ7ywNdBYLbU0rgjtPxiUGQm6ARjkQIGWJiJ3PnmQEouCkaqY5zQtpEchudRGFI"