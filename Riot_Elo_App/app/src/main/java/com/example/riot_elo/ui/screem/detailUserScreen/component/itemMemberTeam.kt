package com.example.riot_elo.ui.screem.detailUserScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.riot_elo.MyBase.getRankImage
import com.example.riot_elo.data.getLaneInmageLink
import com.example.riot_elo.models.MatchInfo
import com.example.riot_elo.models.User
import com.example.riot_elo.ui.component.LoadImage
import com.example.riot_elo.ui.component.LoadImage2
import com.example.riot_elo.ui.component.RankingBox

@Composable
fun ItemMemberTeam(
    match: MatchInfo,
    onClick: (() -> Unit)
) {
    Card(
        modifier = Modifier
            .height(70.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray.copy(alpha = 0.1f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(Color.LightGray.copy(alpha = 0.1f))
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                ) {
                    LoadImage(match.imageChampion.toString())
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = match.userName,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "KDA " + "${match.kills}/${match.deaths}/${match.assists}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }





                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.padding(top = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${match.champion}",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Gold: ${match.gold}", // Dynamic name, e.g., "Player 1", "Player 2"
                            fontSize = 11.sp,
                            fontWeight = FontWeight.W400,
                            color = Color.DarkGray
                        )
                    }

                }


                Spacer(modifier = Modifier.width(20.dp))


                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                ) {
                    LoadImage2(getLaneInmageLink(match.position))
                }

            }

        }

    }
}
