package com.example.mvpheartrate.presentation.screen.loading

import android.annotation.SuppressLint
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mvpheartrate.presentation.common.composable.HeartRateLinearProgressIndicator
import com.example.mvpheartrate.presentation.common.composable.ScreenBackground
import com.example.mvpheartrate.presentation.common.navigation.NavScreens
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.images
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.delay
import kotlin.random.Random

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LoadingScreen(
    navController: NavHostController,
    viewModel: LoadingViewModel = hiltViewModel()
) {
    val progress = viewModel.loadingProgress
    val nextDestination = viewModel.nextDestination
    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    LaunchedEffect(Unit) {
        viewModel.updateDestinationRoute()
    }

    LaunchedEffect(cameraPermissionState.status) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    LaunchedEffect(key1 = Unit) {
        while (progress.value < 1f) {
            viewModel.updateLoadingProgress(progress.value + 0.01f)
            delay(Random.nextLong(10, 201))
        }

        if (progress.value > 0.99f) {
            navController.navigate(nextDestination.value) {
                popUpTo(NavScreens.LoadingScreen) {
                    inclusive = true
                }
                launchSingleTop = true
            }
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
                    progress = progress.value,
                )
            }

        }
    }
}
