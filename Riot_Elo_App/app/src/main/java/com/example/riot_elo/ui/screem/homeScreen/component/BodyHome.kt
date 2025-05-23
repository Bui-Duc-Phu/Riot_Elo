package com.example.riot_elo.ui.screem.homeScreen.component

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.riot_elo.models.User
import com.example.riot_elo.ui.component.LoadImage
import com.example.riot_elo.ui.component.RankingBox

@Composable
fun BodyHome(
    list: List<User>,
    onClick: ((User) -> Unit)
) {
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
            list.forEachIndexed { index, user ->
                RankItem(
                    index = index + 1, // Hiển thị thứ hạng bắt đầu từ 1
                    user = user,
                    onClick = {
                        onClick(user)
                    }
                )
            }
        }
    }
}


@Composable
fun RankItem(
    index:Int,
    user: User,
    onClick: (() -> Unit)) {
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
        Row (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray.copy(alpha = 0.1f))
        ){
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(Color.LightGray.copy(alpha = 0.1f))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.width(50.dp).height(50.dp)
                ){
                    LoadImage(user.imageUrl.toString())
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = user.name, // Dynamic name, e.g., "Player 1", "Player 2"
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = user.rankLever.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.DarkGray
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Box(
                        modifier = Modifier
                            .width(45.dp)
                            .height(38.dp),
                        contentAlignment = Alignment.Center
                    ){

                        LoadImage(getRankImage(user.rank!!.toInt()))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "LP: "+user.lp+" Điểm", // Dynamic name, e.g., "Player 1", "Player 2"
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.DarkGray
                    )
                }



            }
            Spacer(modifier = Modifier.width(10.dp))
            Box(

            ){
                RankingBox(index.toString())
            }
            Spacer(modifier = Modifier.width(20.dp))
        }

    }

}
