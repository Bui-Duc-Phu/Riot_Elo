package com.example.riot_elo.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.riot_elo.ui.screem.detailUserScreen.DetailMatches
import com.example.riot_elo.ui.screem.historyScreen.HistoryScreen
import com.example.riot_elo.ui.screem.homeScreen.HomeScreen


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.HistoryScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Route.HomeScreen.route) { HomeScreen(navController) }

        composable(
            Route.DetailUserScreen.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(500)) // Hiệu ứng mở
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(500)) // Hiệu ứng quay lại
            }
        ) { DetailMatches(navController) }

        composable(
            Route.HistoryScreen.route
            , enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(500)) // Hiệu ứng mở
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(500)) // Hiệu ứng quay lại
            }
        ) { HistoryScreen(navController) }









    }
}

