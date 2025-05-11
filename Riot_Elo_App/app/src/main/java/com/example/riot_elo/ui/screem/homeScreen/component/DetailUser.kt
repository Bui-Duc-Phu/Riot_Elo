package com.example.riot_elo.ui.screem.homeScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.riot_elo.MyBase.getRankImage
import com.example.riot_elo.R
import com.example.riot_elo.models.User
import com.example.riot_elo.ui.component.LoadImage
import com.example.riot_elo.ui.component.safeClickable
import java.nio.file.WatchEvent

@Composable
fun DetaiUser(
    user: User,
    onClickHistory: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                ) {
                    LoadImage(user.imageUrl.toString())
                }
                Spacer(modifier = Modifier.width(15.dp))
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = user.name,
                        fontSize = 20.sp,
                        color = Color.Black, fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Điểm LP : " + user.lp,
                        fontSize = 14.sp,
                        color = Color.Black, fontWeight = FontWeight.W400
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Điểm K_Elo : " + user.kElo,
                        fontSize = 14.sp,
                        color = Color.Black, fontWeight = FontWeight.W400
                    )
                }


            }

            Row(


            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 30.dp, start = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Mức Rank: " + "Đại cao thủ",
                        fontSize = 16.sp,
                        color = Color.Black, fontWeight = FontWeight.W400
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Tổng trận win: " + "120" + " trận",
                        fontSize = 16.sp,
                        color = Color.Black, fontWeight = FontWeight.W400
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Tỷ lệ thắng: " + "50" + "%",
                        fontSize = 16.sp,
                        color = Color.Black, fontWeight = FontWeight.W400
                    )

                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .width(130.dp)
                            .height(100.dp),

                        ) {
                        LoadImage(getRankImage(user.lpRank()))
                    }
                    Text(
                        text = "III",
                        fontSize = 20.sp,
                        color = Color.Black, fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
            }


            Spacer(modifier = Modifier.height(50.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onClickHistory() }
                        .clip(RoundedCornerShape(16.dp)) // Bo góc với bán kính 16dp
                        .background(Color.LightGray) // Set background color
                        .padding(16.dp) // Thêm padding để nội dung không sát viền
                ) {
                    Text(
                        text = "Lịch sử đấu",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.W400,
                        modifier = Modifier.align(alignment = Alignment.Center)
                    )
                }

            }


        }


    }
}

