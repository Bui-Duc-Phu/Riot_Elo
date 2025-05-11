package com.example.riot_elo.navigation

sealed class Route(val route: String) {
    object DetailMatches : Route("detail_matches/{id}") {
        fun createRoute(id: String) = "detail_matches/$id"
    }
    object HistoryScreen : Route("HistoryScreen")
    object HomeScreen : Route("HomeScreen")
}
