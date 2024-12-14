package com.example.mvpheartrate.presentation.common.navigation

import com.example.mvpheartrate.domain.models.BpmData
import kotlinx.serialization.Serializable

@Serializable
sealed class NavScreens {
    @Serializable
    data object LoadingScreen: NavScreens()

    @Serializable
    data object OnboardingScreen: NavScreens()

    @Serializable
    data object HomepageScreen: NavScreens()
}

@Serializable
sealed class HomePageScreens {
    @Serializable
    data object WaitingScreen: HomePageScreens()

    @Serializable
    data object PulseMeasurementScreen: HomePageScreens()

    @Serializable
    data class ResultScreen(val result: BpmData) : HomePageScreens()

    @Serializable
    data object ResultListScreen: HomePageScreens()
}