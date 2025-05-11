package com.example.riot_elo.ui.screem.detailUserScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.riot_elo.models.dummyMatchInfoList
import com.example.riot_elo.ui.screem.detailUserScreen.DetailViewmodel
import androidx.compose.runtime.*
import com.example.riot_elo.models.getTeamsLosers
import com.example.riot_elo.models.getTeamsWinners

@Composable
fun BodyDetailMatches(
    viewmodel: DetailViewmodel,
) {

    val listMatch by viewmodel.listMatches.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            Header(
                team1 = getTeamsWinners(listMatch),
                team2 = getTeamsLosers(listMatch),
            )

            Spacer(modifier = Modifier.height(20.dp))

            DetailTeams(
                team1 = getTeamsWinners(listMatch),
                team2 = getTeamsLosers(listMatch),
                onclick = {
                }
            )
        }
    }
}

