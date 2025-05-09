package com.example.riot_elo.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Modifier.safeClickable(interval: Long = 500L, onClick: () -> Unit): Modifier {
    var lastClickTime = 0L
    return this.then(
        Modifier.clickable{
            val now = System.currentTimeMillis()
            if (now - lastClickTime > interval) {
                lastClickTime = now
                onClick()
            }
        }
    )
}
