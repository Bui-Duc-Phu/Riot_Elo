package com.example.riot_elo.ui.screem.homeScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToolbarHome(

) {
    Card(
        modifier = Modifier.height(60.dp).fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(0.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){

            Text(
                text = "Bảng Xếp hạng Rank",
                fontSize = 15.sp,
                fontWeight = FontWeight.W400,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
            Row(
                modifier = Modifier.align (Alignment.CenterEnd),
            ){
                IconButton(
                    onClick = { /* Handle filter click */ },
                ) {

                    androidx.compose.material3.Icon(
                        imageVector = Icons.Outlined.FilterList,
                        modifier = Modifier.size(28.dp),
                        tint = Color.Black,
                        contentDescription = "Dấu lớn hơn"
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
            }



        }
    }


    
}