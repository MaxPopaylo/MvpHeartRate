package com.example.mvpheartrate.presentation.screen.homepage.pulse_measurment.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mvpheartrate.R
import com.example.mvpheartrate.presentation.common.composable.HeartRateLinearProgressIndicator

@Composable
fun MeasurementInfo(
    showResultState: Boolean,
    fingerDetected: Boolean,
    progress: Float
) {
    if (!showResultState) {
//        AnimatedVisibility(
//            visible = fingerDetected,
//            enter = fadeIn(animationSpec = tween(300)),
//            exit = fadeOut(animationSpec = tween(500))
//        ) {
//            Row(
//                Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight(0.3f),
//                verticalAlignment = Alignment.Top
//            ) {
//                HeartRateLinearProgressIndicator(
//                    progress = progress
//                )
//            }
//        }
//
//        AnimatedVisibility(
//            visible = !fingerDetected,
//            enter = fadeIn(animationSpec = tween(300)),
//            exit = fadeOut(animationSpec = tween(500))
//        ) {
//            Image(
//                painter = painterResource(R.drawable.hand_plus_phone),
//                contentDescription = "HandPlusPhone",
//                modifier = Modifier
//                    .fillMaxWidth(0.4f)
//                    .offset(x = 15.dp),
//            )
//        }
        if (fingerDetected) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f),
                verticalAlignment = Alignment.Top
            ) {
                HeartRateLinearProgressIndicator(
                    progress = progress
                )
            }
        } else {
            Image(
                painter = painterResource(R.drawable.hand_plus_phone),
                contentDescription = "HandPlusPhone",
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .offset(x = 15.dp),
            )
        }
    } else {
        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            verticalAlignment = Alignment.Top
        ) {

        }
    }
}