package com.example.riot_elo.ui.screem.historyScreen.component

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
fun itemHostory(
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
                    LoadImage(match.imageChampion)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.padding(top = 10.dp)
                ){
                    Text(
                        text = "KDA "+"${match.kills}/${match.deaths}/${match.assists}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Vị trí: ${match.position}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.DarkGray
                    )
                }




                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = if (match.isWin) "Victory" else "Defeated",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = if (match.isWin) Color.Green else Color.Red
                    )
                }


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
