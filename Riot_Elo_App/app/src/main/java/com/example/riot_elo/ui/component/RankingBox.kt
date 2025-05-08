package com.example.riot_elo.ui.component

import androidx.compose.ui.graphics.Color

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.riot_elo.R


@Composable
fun RankingBox(
    RankIndex: String
) {
    Box(
        modifier = Modifier
            .width(20.dp)
            .height(35.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ranking_icon),
            contentDescription = "icon",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopEnd),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(fillColor(RankIndex))
        )
        Text(
            text = RankIndex,
            fontSize = 12.sp,
            color = Color.White, // Đây là androidx.compose.ui.graphics.Color.White
        )

    }
}

@Composable
fun fillColor(RankIndex: String): androidx.compose.ui.graphics.Color {
    return when (RankIndex) {
        "1" -> androidx.compose.ui.graphics.Color.Red
        "2" -> Color.Blue
        "3" -> Color.DarkGray
        else -> androidx.compose.ui.graphics.Color.Magenta
    }
}

