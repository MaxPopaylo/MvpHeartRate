package com.example.mvpheartrate.presentation.screen.result

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mvpheartrate.domain.models.BpmData
import com.example.mvpheartrate.presentation.common.composable.HeartRateButton
import com.example.mvpheartrate.presentation.common.navigation.HomePageScreens
import com.example.mvpheartrate.presentation.screen.result.composable.ResultCard
import kotlinx.coroutines.delay

@Composable
fun ResultScreen(
    bpmData: BpmData,
    navController: NavHostController,
    viewModel: ResultViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        if (bpmData.bpm > 0) {
            viewModel.saveResult(bpmData)
        }

        viewModel.updateCurrentSectionState(bpmData)
        delay(1000)
        viewModel.updateProgressState(bpmData = bpmData)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f),
            contentAlignment = Alignment.Center
        ){
            ResultCard(
                bpmData = bpmData,
                sectionList =  viewModel.sectionList.value,
                currentSection =  viewModel.currentSection.value,
                currentProgress = viewModel.progress.value
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {

            HeartRateButton(
                text = "Готово",
                onClick = {
                    navController.navigate(HomePageScreens.WaitingScreen) {
                        popUpTo(HomePageScreens.ResultListScreen) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }

}

