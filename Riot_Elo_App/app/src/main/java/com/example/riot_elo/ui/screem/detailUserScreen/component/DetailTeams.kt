package com.example.riot_elo.ui.screem.detailUserScreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.riot_elo.models.MatchInfo
import com.example.riot_elo.models.User
import java.nio.file.WatchEvent

@Composable
fun DetailTeams(
    team1: List<MatchInfo>,
    team2: List<MatchInfo>,
    onclick : (MatchInfo) -> Unit
    ) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = "Team 1",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 20.dp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        team1.forEachIndexed { index, match ->
            ItemMemberTeam(
                match = match,
                onClick = {
                    onclick(match)
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Team 2",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 20.dp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        team2.forEachIndexed { index, match ->
            ItemMemberTeam(
                match = match,
                onClick = {
                    onclick(match)
                }
            )
        }
    }
}