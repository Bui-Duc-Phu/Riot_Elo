package com.example.riot_elo.ui.screem.historyScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.riot_elo.data.MyImage
import com.example.riot_elo.ui.screem.historyScreen.component.BodyHistory
import com.example.riot_elo.ui.screem.historyScreen.component.itemHostory

@Composable
fun HistoryScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){

        BodyHistory(
            list = MyImage.userList,
            onClick = {

            }
        )

    }

}