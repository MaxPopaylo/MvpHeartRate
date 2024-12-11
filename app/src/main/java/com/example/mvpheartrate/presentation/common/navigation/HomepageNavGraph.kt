package com.example.mvpheartrate.presentation.common.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mvpheartrate.presentation.screen.homepage.HomepageScreen
import com.example.mvpheartrate.presentation.screen.homepage.pulse_measurment.PulseMeasurementScreen
import com.example.mvpheartrate.presentation.screen.homepage.waiting.WaitingScreen
import com.example.mvpheartrate.presentation.screen.loading.LoadingScreen
import com.example.mvpheartrate.presentation.screen.onboarding.OnboardingScreen

@Composable
fun HomepageNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomePageScreens.WaitingScreen
    ) {
        composable<HomePageScreens.WaitingScreen>(
            enterTransition = {
                return@composable  fadeIn(
                    animationSpec = tween(1000)
                )
            },
            exitTransition = {
                fadeOut(tween(700))
            }
        ) {
            WaitingScreen(
                navController = navController
            )
        }

        composable<HomePageScreens.PulseMeasurementScreen>(
            enterTransition = {
                return@composable  slideInVertically(
                    initialOffsetY = { -it },
                    animationSpec = tween(1000)
                )
            },
            exitTransition = {
                fadeOut(tween(700))
            }
        ) {
            PulseMeasurementScreen(
                navController = navController
            )
        }

        composable<HomePageScreens.ResultScreen>(
            enterTransition = {
                return@composable  slideInVertically(
                    initialOffsetY = { -it },
                    animationSpec = tween(1000)
                )
            },
            exitTransition = {
                fadeOut(tween(700))
            }
        ) {

        }

        composable<HomePageScreens.ResultListScreen>(
            enterTransition = {
                return@composable  slideInVertically(
                    initialOffsetY = { -it },
                    animationSpec = tween(1000)
                )
            },
            exitTransition = {
                fadeOut(tween(700))
            }
        ) {

        }
    }
}