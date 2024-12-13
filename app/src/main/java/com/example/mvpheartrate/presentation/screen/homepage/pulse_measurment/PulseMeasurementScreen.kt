package com.example.mvpheartrate.presentation.screen.homepage.pulse_measurment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mvpheartrate.presentation.common.navigation.HomePageScreens
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.screen.homepage.pulse_measurment.composable.HeartRateDisplay
import com.example.mvpheartrate.presentation.screen.homepage.pulse_measurment.composable.MeasurementInfo
import com.example.mvpheartrate.presentation.screen.homepage.pulse_measurment.composable.PulseMeasurementHeader

@Composable
fun PulseMeasurementScreen(
    navController: NavHostController,
    viewModel: PulseMeasurementViewModel = hiltViewModel()
) {
    val bpmCounter = viewModel.bpmFlow.collectAsState()
    val fingerDetected = viewModel.fingerDetectedFlow.collectAsState()
    val showResultState = viewModel.showResultState
    val progress = viewModel.loadingProgress

    LaunchedEffect(viewModel) {
        viewModel.bpmResult.collect { result ->
            if (result.bpm > 0) {
                navController.navigate(HomePageScreens.ResultScreen) {
                    popUpTo(HomePageScreens.PulseMeasurementScreen) {
                        inclusive = true
                    }
                }
            }
        }
    }

    LaunchedEffect(fingerDetected) {
        if (!fingerDetected.value) {
            viewModel.clearStats()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.stopHeartRateMeasurement()
        }
    }

   Box(
       modifier = Modifier
           .fillMaxSize(),
       contentAlignment = Alignment.TopEnd
   ) {
       IconButton(
           onClick = {
               viewModel.stopHeartRateMeasurement()
               navController.popBackStack()
           }
       ) {
           Icon(
               imageVector = Icons.Filled.Close,
               tint = colors.secondaryText,
               contentDescription = "Close"
           )
       }
       Column {
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .weight(5f)
                   .padding(16.dp)
                   .padding(vertical = 16.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.SpaceBetween
           ) {
               PulseMeasurementHeader(
                   fingerDetected = fingerDetected.value,
                   onMeasurementStart = {
                       viewModel.startHeartRateMeasurement(it)
                   },
                   onDispose = {
                       viewModel.stopHeartRateMeasurement()
                   }
               )

               HeartRateDisplay(
                   bpmCounter = bpmCounter.value,
                   isAnimated = fingerDetected.value
               )

               MeasurementInfo(
                   showResultState = showResultState.value,
                   fingerDetected = fingerDetected.value,
                   progress = progress.value
               )
           }

           Box(
               modifier = Modifier
                   .fillMaxWidth()
                   .weight(1f)
           )
       }
   }
}