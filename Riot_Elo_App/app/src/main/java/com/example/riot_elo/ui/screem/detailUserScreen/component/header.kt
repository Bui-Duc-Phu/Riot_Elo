package com.example.riot_elo.ui.screem.detailUserScreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.riot_elo.models.MatchInfo
import com.example.riot_elo.models.totalAssists
import com.example.riot_elo.models.totalDeaths
import com.example.riot_elo.models.totalGold
import com.example.riot_elo.models.totalKills
import okhttp3.internal.http2.Header


@Composable
fun Header(
    team1: List<MatchInfo>,
    team2: List<MatchInfo>,
) {
    Box {
        Row {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Text(
                    text = "Victory Teams",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Tổng vàng: "+ totalGold(team1),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Text(
                    text = "KDA ${totalKills(team1)}/ ${totalDeaths(team1)}/${totalAssists(team1)}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )

            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Text(
                    text = "Defeat Teams",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Tổng vàng: "+totalGold(team2),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Text(
                    text = "KDA ${totalKills(team2)}/ ${totalDeaths(team2)}/${totalAssists(team2)}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )

            }
        }
    }

    
}