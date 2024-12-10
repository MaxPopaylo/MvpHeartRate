package com.example.mvpheartrate.presentation.common.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.MvpHeartRateTheme

@Composable
fun ScreenBackground(content: @Composable () -> Unit) {
    val secondaryBackgroundColor = colors.secondaryBackground
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.primaryBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f)
                .background(
                    color = secondaryBackgroundColor
                )
        )
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .offset(
                    x = 0.dp,
                    y = (-60).dp
                )
        ) {
            drawArc(
                color = secondaryBackgroundColor,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true
            )
        }

        Box(modifier = Modifier.weight(1f))
    }
    content()
}

@Preview
@Composable
fun ScreenBackgroundPre() {
    MvpHeartRateTheme {
        ScreenBackground{}
    }
}