package com.example.mvpheartrate.presentation.screen.loading
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mvpheartrate.presentation.common.navigation.NavScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel
@Inject constructor(): ViewModel() {

    private val isOnboardingViewed: MutableState<Boolean> = mutableStateOf(false)

    private val _nextDestination: MutableState<NavScreens> = mutableStateOf(NavScreens.OnboardingScreen)
    val nextDestination: State<NavScreens> = _nextDestination

    private val _loadingProgress: MutableState<Float> = mutableStateOf(0.01f)
    val loadingProgress: State<Float> = _loadingProgress

    init {
        updateDestinationRoute()
    }

    fun updateLoadingProgress(value: Float) {
        _loadingProgress.value = value
    }

    private fun updateDestinationRoute() {
        updateIsOnboardingViewed()
        if (!isOnboardingViewed.value) {
            _nextDestination.value = NavScreens.OnboardingScreen
        } else {
            _nextDestination.value = NavScreens.HomepageScreen
        }
    }

    private fun updateIsOnboardingViewed() {}

}