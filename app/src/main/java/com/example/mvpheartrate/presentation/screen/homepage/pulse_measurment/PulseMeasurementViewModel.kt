package com.example.mvpheartrate.presentation.screen.homepage.pulse_measurment

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PulseMeasurementViewModel @Inject
constructor(): ViewModel() {

    private val _bpmCounter: MutableState<String> = mutableStateOf("")
    val bpmCounter: State<String> = _bpmCounter

    private val _fingerDetected: MutableState<Boolean> = mutableStateOf(false)
    val fingerDetected: State<Boolean> = _fingerDetected

    private val _heartAnimated: MutableState<Boolean> = mutableStateOf(false)
    val heartAnimated: State<Boolean> = _heartAnimated

    fun updateBpmCounter(value: String) {
        _bpmCounter.value = value
    }

    fun fingerDetected(value: Boolean) {
        _fingerDetected.value = value
    }

    fun updateHeartAnimated(value: Boolean) {
        _heartAnimated.value = value
    }
}