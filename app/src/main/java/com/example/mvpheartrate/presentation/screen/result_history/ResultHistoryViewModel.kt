package com.example.mvpheartrate.presentation.screen.result_history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvpheartrate.domain.models.BpmData
import com.example.mvpheartrate.domain.repository.ResultHistoryManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultHistoryViewModel @Inject constructor(
    private val resultHistoryManager: ResultHistoryManager
): ViewModel() {

    private val _resultList: MutableStateFlow<List<BpmData>> = MutableStateFlow(emptyList())
    val resultList = _resultList.asStateFlow()

    fun updateResultList() {
        viewModelScope.launch {
            resultHistoryManager.getAll().collect { data ->
                _resultList.update { data }
            }
        }
    }
    fun clearAllResultList() {
        viewModelScope.launch {
            resultHistoryManager.clearAll()
        }
    }

}