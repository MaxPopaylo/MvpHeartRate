package com.example.mvpheartrate.presentation.common.composable

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors

@Composable
fun PermissionAlertDialog(
    onDismiss: () -> Unit,
    getPermission: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Додаток потребує доступу до камери для коректного виконання програми",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colors.primaryText,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = "",
                fontSize = 16.sp,
                color = colors.secondaryText
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    getPermission()
                    onDismiss()
                 },
                colors = ButtonDefaults.buttonColors(
                    contentColor = colors.primaryText,
                    containerColor = Color.Transparent
                )
            ) {
                Text("Дати доступ")
            }
        },
        containerColor = colors.primaryBackground
    )
}