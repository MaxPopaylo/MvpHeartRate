package com.example.mvpheartrate.presentation.screen.homepage.pulse_measurment.composable

import android.view.SurfaceView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography

@Composable
fun PulseMeasurementHeader(
    fingerDetected: Boolean,
    onMeasurementStart: (SurfaceView) -> Unit,
    onDispose: () -> Unit
) {
    val titleText = if (fingerDetected) "Йде Вимірювання."
    else "Палець не виявлено"

    val descriptionText = if (fingerDetected) "Визначаємо ваш пульс. Утримуйте!"
    else "Щільно прикладіть палець до камери"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(42.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFF4CB8FF),
                    shape = CircleShape
                )
                .background(Color.Red)
        ) {
            CameraSurfaceView (
                onSurfaceCreated = {
                    onMeasurementStart(it)
                },
                onDispose = {
                    onDispose()
                }
            )
        }
        Text(
            text = titleText,
            color = colors.primaryText,
            style = typography.w600.copy(
                fontSize = 18.sp
            ),
            textAlign = TextAlign.Center
        )
        Text(
            text = descriptionText,
            color = Color(0xFFEAF6FF),
            style = typography.w400.copy(
                fontSize = 16.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}
