package com.example.mvpheartrate.presentation.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography

@Composable
fun HeartRateLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    progressColor: Color = colors.primaryTintColor,
    backgroundColor: Color = colors.secondaryTintColor,
    clipShape: Shape = RoundedCornerShape(16.dp)
) {
    Box(
        modifier = modifier
            .clip(clipShape)
            .height(20.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFFF4B4B),
                shape = clipShape
            )
            .background(backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .clip(clipShape)
                .fillMaxHeight()
                .fillMaxWidth(progress)
                .background(progressColor)
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${(progress * 100).toInt()}%",
                style = typography.w400.copy(
                    fontSize = 14.sp
                ),
                color = colors.tertiaryText
            )
        }
    }
}