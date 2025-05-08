package com.example.riot_elo.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier

fun Modifier.safeClickable(interval: Long = 500L, onClick: () -> Unit): Modifier {
    var lastClickTime = 0L
    return this.then(
        Modifier.clickable {
            val now = System.currentTimeMillis()
            if (now - lastClickTime > interval) {
                lastClickTime = now
                onClick()
            }
        }
    )
}
