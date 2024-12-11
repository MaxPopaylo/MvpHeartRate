package com.example.mvpheartrate.presentation.screen.homepage.pulse_measurment.composable

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvpheartrate.R
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography

@Composable
fun HeartRateDisplay(
    modifier: Modifier = Modifier,
    bpmCounter: String
) {
    val bpm by animateIntAsState(
        targetValue = if (bpmCounter.isBlank()) 0 else bpmCounter.toInt(),
        animationSpec = tween(durationMillis = 600),
        label = ""
    )
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(R.drawable.heart_background),
                contentDescription = "HeartBackground"
            )
            Image(
                modifier = Modifier
                    .wrapContentHeight()
                    .offset(y = Dp(50f))
                    .fillMaxWidth()
                    .blur(36.dp),
                painter = painterResource(R.drawable.ellipse_shadow),
                contentDescription = "BlurredBackground"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (bpmCounter.isBlank()) "--" else "$bpm",
                color = colors.tertiaryText,
                style = typography.w700.copy(
                    fontSize = 62.sp
                ),
                textAlign = TextAlign.Center
            )
            Text(
                text = "bpm",
                color = colors.tertiaryText,
                style = typography.w400.copy(
                    fontSize = 22.sp
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}