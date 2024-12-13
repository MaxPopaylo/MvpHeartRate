package com.example.mvpheartrate.presentation.screen.result

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvpheartrate.domain.models.BpmData
import com.example.mvpheartrate.domain.repository.ResultHistoryManager
import com.example.mvpheartrate.presentation.screen.result.models.HealthStatusSection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val resultHistoryManager: ResultHistoryManager
): ViewModel() {

    private val _sectionList: MutableState<List<HealthStatusSection>> = mutableStateOf(
        listOf(
            HealthStatusSection(
                text = "Уповільнений",
                color = Color(0xFF21D7E2),
                "<60 BPM"
            ),
            HealthStatusSection(
                text = "Звичайний",
                color = Color(0xFF1FF19B),
                "60-100 BPM"
            ),
            HealthStatusSection(
                text = "Прискорений",
                color = Color(0xFFFF4C4C),
                ">100 BPM"
            )
        )
    )
    val sectionList: State<List<HealthStatusSection>> = _sectionList

    private val _progress: MutableState<Float> = mutableFloatStateOf(0.01f)
    val progress: State<Float> = _progress

    private val _currentSection: MutableState<HealthStatusSection> = mutableStateOf(sectionList.value[2])
    val currentSection: State<HealthStatusSection> = _currentSection

    fun updateCurrentSectionState(bpmData: BpmData) {
        _currentSection.value = checkCurrentSection(bpmData.bpm)
    }

    fun updateProgressState(bpmData: BpmData) {
        _progress.value = calculatePercentage(bpmData.bpm)
    }

    private fun calculatePercentage(value: Int): Float {
        return value / 150f
    }

    private fun checkCurrentSection(value: Int): HealthStatusSection {
        return when (value) {
            in 0..60 -> _sectionList.value[0]
            in 61..100 -> _sectionList.value[1]
            in 101..150 -> _sectionList.value[2]
            else -> _sectionList.value[2]
        }
    }


    fun saveResult(bpmData: BpmData) {
        viewModelScope.launch {
            resultHistoryManager.saveResult(bpmData)
        }
    }

}