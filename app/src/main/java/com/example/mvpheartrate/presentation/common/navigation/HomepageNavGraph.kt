package com.example.mvpheartrate.presentation.common.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.mvpheartrate.domain.models.BpmData
import com.example.mvpheartrate.presentation.screen.homepage.pulse_measurment.PulseMeasurementScreen
import com.example.mvpheartrate.presentation.screen.homepage.waiting.WaitingScreen
import com.example.mvpheartrate.presentation.screen.result.ResultScreen
import com.example.mvpheartrate.presentation.screen.result_history.ResultHistoryScreen
import kotlin.reflect.typeOf

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
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                return@composable fadeOut(
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                return@composable fadeOut(
                    animationSpec = tween(300)
                )
            }
        ) {
            WaitingScreen(
                navController = navController
            )
        }

        composable<HomePageScreens.PulseMeasurementScreen>(
            enterTransition = {
                return@composable  fadeIn(
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                return@composable fadeOut(
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                return@composable fadeOut(
                    animationSpec = tween(300)
                )
            }
        ) {

            PulseMeasurementScreen(
                navController = navController
            )
        }

        composable<HomePageScreens.ResultScreen>(
            enterTransition = {
                return@composable  fadeIn(
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                return@composable fadeOut(
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                return@composable fadeOut(
                    animationSpec = tween(300)
                )
            },
            typeMap = mapOf(
                typeOf<BpmData>() to BpmDataNavType
            )
        ) {
            val args = it.toRoute<HomePageScreens.ResultScreen>()
            ResultScreen(
                bpmData = args.result,
                navController = navController
            )
        }

        composable<HomePageScreens.ResultListScreen>(
            enterTransition = {
                return@composable  fadeIn(
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                return@composable fadeOut(
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                return@composable fadeOut(
                    animationSpec = tween(300)
                )
            }
        ) {
            ResultHistoryScreen()
        }
    }
}