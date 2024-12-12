package com.example.mvpheartrate.presentation.common.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mvpheartrate.presentation.screen.homepage.HomepageScreen
import com.example.mvpheartrate.presentation.screen.loading.LoadingScreen
import com.example.mvpheartrate.presentation.screen.onboarding.OnboardingScreen

@Composable
fun GlobalNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavScreens.LoadingScreen
    ) {
        composable<NavScreens.LoadingScreen>(
            enterTransition = {
                return@composable  fadeIn(
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                fadeOut(tween(500))
            }
        ) {
            LoadingScreen(
                navController = navController
            )
        }

        composable<NavScreens.OnboardingScreen>(
            enterTransition = {
                return@composable  fadeIn(
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                fadeOut(tween(500))
            }
        ) {
            OnboardingScreen(
                navController = navController
            )
        }

        composable<NavScreens.HomepageScreen>(
            enterTransition = {
                return@composable  fadeIn(
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                fadeOut(tween(500))
            }
        ) {
            HomepageScreen()
        }
    }
}