package com.example.riot_elo.ui.screem.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.riot_elo.data.MyImage
import com.example.riot_elo.ui.screem.homeScreen.component.BodyHome
import com.example.riot_elo.ui.screem.homeScreen.component.ToolbarHome


@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            ToolbarHome()
            BodyHome(
                list = MyImage.userList
            )
        }

    }
}