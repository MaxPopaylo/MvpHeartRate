package com.example.mvpheartrate.presentation.screen.homepage.pulse_measurment.composable

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvpheartrate.R
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography
import kotlinx.coroutines.delay

@Composable
fun HeartRateDisplay(
    modifier: Modifier = Modifier,
    isAnimated: Boolean,
    bpmCounter: Int
) {
    val bpm by animateIntAsState(
        targetValue = bpmCounter,
        animationSpec = tween(durationMillis = 600),
        label = ""
    )
    var scale by remember { mutableFloatStateOf(1f) }
    val textSize by animateFloatAsState(
        targetValue = if (scale > 1f) 70f else 62f,
        animationSpec = tween(durationMillis = 300),
        label = ""
    )

    LaunchedEffect(isAnimated) {
        while (isAnimated) {
            animate(
                initialValue = 1f,
                targetValue = 1.02f,
                animationSpec = tween(durationMillis = 300),
                block = { value, _ -> scale = value }
            )

            animate(
                initialValue = 1.02f,
                targetValue = 1f,
                animationSpec = tween(durationMillis = 300),
                block = { value, _ -> scale = value }
            )
            delay(500)
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(R.drawable.heart_background),
                contentDescription = "HeartBackground",
                modifier = Modifier.graphicsLayer(scaleX = scale, scaleY = scale)
            )
            Image(
                painter = painterResource(R.drawable.ellipse_shadow),
                contentDescription = "BlurredBackground",
                modifier = Modifier
                    .wrapContentHeight()
                    .offset(y = Dp(50f))
                    .fillMaxWidth()
                    .blur(
                        radius = 36.dp
                    )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (bpmCounter == 0) "--" else "$bpm",
                color = colors.tertiaryText,
                style = typography.w700.copy(
                    fontSize = textSize.sp
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
