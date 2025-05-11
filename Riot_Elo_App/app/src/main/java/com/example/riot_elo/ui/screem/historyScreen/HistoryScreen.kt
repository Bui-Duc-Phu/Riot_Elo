package com.example.riot_elo.ui.screem.historyScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.riot_elo.models.dummyMatchInfoList
import androidx.compose.runtime.*
import com.example.riot_elo.navigation.Route

import com.example.riot_elo.ui.screem.historyScreen.component.BodyHistory
import com.example.riot_elo.ui.screem.historyScreen.component.itemHostory

@Composable
fun HistoryScreen(
    navController: NavController
    ,viewmodel: HistoryViewmodel = hiltViewModel()
){
    val listMatche by viewmodel.listMatches.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){

        BodyHistory(
            listMatche,
            onClick = {
                navController.navigate(Route.DetailMatches.createRoute(it))
            }
        )

    }

}