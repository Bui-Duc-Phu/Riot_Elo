package com.example.riot_elo.ui.screem.historyScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun HistoryScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){
        Text(
            text = "hello "
        )
    }

}