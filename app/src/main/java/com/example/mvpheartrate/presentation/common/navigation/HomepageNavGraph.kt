package com.example.mvpheartrate.presentation.common.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mvpheartrate.presentation.screen.homepage.pulse_measurment.PulseMeasurementScreen
import com.example.mvpheartrate.presentation.screen.homepage.waiting.WaitingScreen
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
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                fadeOut(tween(500))
            }
        ) {
            WaitingScreen(
                navController = navController
            )
        }

        composable<HomePageScreens.PulseMeasurementScreen>(
            enterTransition = {
                return@composable  fadeIn(
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                fadeOut(tween(500))
            }
        ) {

            PulseMeasurementScreen(
                navController = navController
            )
        }

        composable<HomePageScreens.ResultScreen>(
            enterTransition = {
                return@composable  fadeIn(
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                fadeOut(tween(500))
            }
        ) {

        }

        composable<HomePageScreens.ResultListScreen>(
            enterTransition = {
                return@composable  fadeIn(
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                fadeOut(tween(500))
            }
        ) {

        }
    }
}