package com.example.mvpheartrate.presentation.screen.homepage.pulse_measurment

import android.view.SurfaceView
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvpheartrate.domain.models.BpmData
import com.example.mvpheartrate.domain.repository.HeartRateMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PulseMeasurementViewModel @Inject constructor(
    private val heartRateMonitor: HeartRateMonitor
): ViewModel() {

    private val _bpmFlow = MutableStateFlow(0)
    val bpmFlow: StateFlow<Int> = _bpmFlow.asStateFlow()

    private val _fingerDetectedFlow = MutableStateFlow(false)
    val fingerDetectedFlow: StateFlow<Boolean> = _fingerDetectedFlow.asStateFlow()

    private val resultChannel = Channel<BpmData>()
    val bpmResult = resultChannel.receiveAsFlow()

    private val _loadingProgress: MutableState<Float> = mutableStateOf(0.01f)
    val loadingProgress: State<Float> = _loadingProgress

    private val _showResultState: MutableState<Boolean> = mutableStateOf(false)
    val showResultState: State<Boolean> = _showResultState

    fun startHeartRateMeasurement(surfaceView: SurfaceView) {
        viewModelScope.launch {
            heartRateMonitor.subscribe(
                coroutineScope = viewModelScope,
                surfaceView = surfaceView,
                onBpmStateChange = { bpm ->
                    _bpmFlow.value = bpm
                },
                onFingerDetectedState = { detected ->
                    _fingerDetectedFlow.value = detected
                },
                onResult = { bpmData ->
                    showResultState(bpmData)
                },
                onTimerProgress = { pair ->
                    updateLoadingProgress(pair)
                }
            )
        }
    }

    private fun updateLoadingProgress(pair: Pair<Long, Int>) {
        val maxTime = pair.first.toFloat()
        val currentProgress = pair.second.toFloat()

        val progress = if (maxTime != 0f) currentProgress / maxTime else 0f

        _loadingProgress.value = progress
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }

    fun stopHeartRateMeasurement() = dispose()

    private fun dispose() {
        viewModelScope.launch {
            heartRateMonitor.unsubscribe()
        }
    }

    private fun showResultState(bpmData: BpmData) {
        viewModelScope.launch {
            _showResultState.value = true
            _bpmFlow.value = bpmData.bpm
            _fingerDetectedFlow.value = false

            delay(2000)

            resultChannel.send(bpmData)
        }
    }

    fun clearStats() {
        _loadingProgress.value = 0.01f
        _bpmFlow.update { 0 }
    }


}