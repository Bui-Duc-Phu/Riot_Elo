package com.example.riot_elo.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomModalBottomSheet(
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    // Đợi animation đóng hoàn tất
                    sheetState.hide()
                    onDismiss()
                }
            },
            sheetState = sheetState,
            modifier = Modifier.fillMaxWidth(),
            content = {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    content()
                }
            }
        )
    }
}
