package com.example.riot_elo.ui.screem.detailUserScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.riot_elo.ui.screem.detailUserScreen.component.BodyDetailMatches
import com.example.riot_elo.ui.screem.historyScreen.HistoryViewmodel

@Composable
fun DetailMatches(
    navController: NavController,
    id:String,
    viewmodel: DetailViewmodel = hiltViewModel()
) {

    LaunchedEffect(key1 = id) {
        viewmodel.fetchAllMatchesByIdUser(id)
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){
        BodyDetailMatches(
            viewmodel,
        )
    }

}