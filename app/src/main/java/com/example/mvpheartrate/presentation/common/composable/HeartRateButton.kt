package com.example.mvpheartrate.presentation.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography

@Composable
fun HeartRateButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.primaryTint,
                shape = RoundedCornerShape(25.dp)
            ),
        onClick = { onClick() },
        colors = ButtonColors(
            containerColor = colors.primaryTint,
            contentColor = colors.tertiaryText,
            disabledContainerColor = colors.primaryTint,
            disabledContentColor = colors.tertiaryText
        ),
    ) {
        Text(
            text = text,
            style = typography.w500.copy(
                fontSize = 16.sp
            )
        )
    }
}
