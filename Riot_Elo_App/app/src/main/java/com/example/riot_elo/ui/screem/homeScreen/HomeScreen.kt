package com.example.riot_elo.ui.screem.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

import com.example.riot_elo.ui.screem.homeScreen.component.BodyHome
import com.example.riot_elo.ui.screem.homeScreen.component.DetailBottonSheet
import com.example.riot_elo.ui.screem.homeScreen.component.ToolbarHome
import androidx.compose.runtime.*

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.riot_elo.models.setRankForUsers
import com.example.riot_elo.navigation.Route


@Composable
fun HomeScreen(
    navController: NavController,
    viewmodel: HomeViewmodel = hiltViewModel()
) {

    val listUser by viewmodel.listUser.collectAsState()

    var showSheet by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            ToolbarHome()
            BodyHome(
                list = listUser,
                onClick ={ user->
                    viewmodel.fetchUserDetailById(user.id)
                   if(user != null){
                       showSheet = true
                   }

                }
            )


        }
        DetailBottonSheet(
            showSheet = showSheet,
            onDismiss = { showSheet = false },
            viewmodel = viewmodel,
            onClickHistory = {
                showSheet = false
                navController.navigate(Route.HistoryScreen.route)
            }
        )

    }
}