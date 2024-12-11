package com.example.mvpheartrate.presentation.screen.loading

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvpheartrate.presentation.common.composable.HeartRateLinearProgressIndicator
import com.example.mvpheartrate.presentation.common.composable.ScreenBackground
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.images
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun LoadingScreen() {
    var progress by remember {  mutableFloatStateOf(0.01f) }
    LaunchedEffect(key1 = Unit) {
        while (progress < 1f) {
            progress += 0.01f
            delay(Random.nextLong(10, 201))
        }
    }
    ScreenBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    painter = images.logo,
                    contentDescription = "Logo"
                )
                Text(
                    text = "Heart Rate",
                    color = colors.primaryText,
                    style = typography.w600.copy(
                        fontSize = 50.sp
                    )
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                HeartRateLinearProgressIndicator(
                    progress = progress,
                )
            }

        }
    }
}
