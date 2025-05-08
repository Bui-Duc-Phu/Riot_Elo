package com.example.riot_elo.navigation

sealed class Route(val route: String) {
    object DetailUserScreen : Route("DetailUserScreen")
    object HistoryScreen : Route("HistoryScreen")
    object HomeScreen : Route("HomeScreen")
}
