package com.example.mvpheartrate.presentation.screen.result.composable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.screen.result.models.HealthStatusSection

@Composable
fun HeartRateBpmHealthStatusIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    sectionList: List<HealthStatusSection>,
    clipShape: Shape = RoundedCornerShape(16.dp)
) {

    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    Box(
        modifier = modifier
            .height(25.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .clip(clipShape)
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            sectionList.forEach { section ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(section.color)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.fillMaxWidth(animatedProgress.value))
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(6.dp)
                    .background(
                        color = colors.primaryBackground,
                        shape = clipShape
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xFFAEAEAE),
                        shape = clipShape
                    )
            )
        }
    }
}



