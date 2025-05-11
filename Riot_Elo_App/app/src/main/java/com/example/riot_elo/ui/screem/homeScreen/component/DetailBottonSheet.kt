package com.example.riot_elo.ui.screem.homeScreen.component

import androidx.compose.runtime.Composable
import com.example.riot_elo.data.MyImage
import com.example.riot_elo.ui.component.CustomModalBottomSheet

@Composable
fun DetailBottonSheet(
    showSheet: Boolean,
    onDismiss: () -> Unit
) {
    CustomModalBottomSheet(
        showBottomSheet = showSheet,
        onDismiss = onDismiss
    ) {
        DetaiUser(
            MyImage.userList[0],
            onClickHistory = {

            }
        )
    }
}

