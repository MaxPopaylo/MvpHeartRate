package com.example.mvpheartrate.presentation.screen.loading
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvpheartrate.domain.repository.OnboardingManager
import com.example.mvpheartrate.presentation.common.navigation.NavScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor(
    private val onboardingManager: OnboardingManager
): ViewModel() {

    private val isOnboardingViewed: MutableState<Boolean> = mutableStateOf(false)

    private val _nextDestination: MutableState<NavScreens> = mutableStateOf(NavScreens.OnboardingScreen)
    val nextDestination: State<NavScreens> = _nextDestination

    private val _loadingProgress: MutableState<Float> = mutableFloatStateOf(0.01f)
    val loadingProgress: State<Float> = _loadingProgress

    fun updateLoadingProgress(value: Float) {
        _loadingProgress.value = value
    }

    fun updateDestinationRoute() {
        viewModelScope.launch {
            updateIsOnboardingViewed()
            if (!isOnboardingViewed.value) {
                _nextDestination.value = NavScreens.OnboardingScreen
                onboardingManager.setOnboardingState(true)
            } else {
                _nextDestination.value = NavScreens.HomepageScreen
            }
        }
    }

    private suspend fun updateIsOnboardingViewed() {
        isOnboardingViewed.value = onboardingManager.getOnboardingState()
    }

}