package com.example.mvpheartrate.presentation.screen.homepage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject
constructor(): ViewModel() {

    private val _useTopInnerPadding: MutableState<Boolean> = mutableStateOf(true)
    val useTopInnerPadding: State<Boolean> = _useTopInnerPadding

    fun updateUseTopInnerPaddingState(value: Boolean) {
        _useTopInnerPadding.value = value
    }
}