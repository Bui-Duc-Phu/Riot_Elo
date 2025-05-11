package com.example.riot_elo.ui.screem.homeScreen.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*

import com.example.riot_elo.ui.component.CustomModalBottomSheet
import com.example.riot_elo.ui.screem.homeScreen.HomeViewmodel

@Composable
fun DetailBottonSheet(
    showSheet: Boolean,
    onDismiss: () -> Unit,
    onClickHistory: ()-> Unit,
    viewmodel: HomeViewmodel

) {
   val userDetail by viewmodel.userDetail.collectAsState()

    CustomModalBottomSheet(
        showBottomSheet = showSheet,
        onDismiss = onDismiss
    ) {
        DetaiUser(
            userDetail,
            onClickHistory = {
                onClickHistory()
            }
        )
    }
}

