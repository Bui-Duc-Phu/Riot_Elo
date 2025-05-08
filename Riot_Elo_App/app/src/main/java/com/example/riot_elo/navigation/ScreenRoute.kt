package com.example.riot_elo.navigation

sealed class Route(val route: String) {
    object DetailStory : Route("DetailStoryScreen")
    object Search : Route("SearchScreem")
    object SeeAll : Route("SeeAllScreem")
    object Chapter : Route("ChapterScreem")
}
