package com.example.mvpheartrate.presentation.screen.homepage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mvpheartrate.presentation.common.composable.ScreenBackground
import com.example.mvpheartrate.presentation.common.navigation.HomepageNavGraph
import com.example.mvpheartrate.presentation.screen.homepage.composable.HomepageTopAppBar


@Composable
fun HomepageScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: HomepageViewModel = hiltViewModel()
) {
    val useTopInnerPadding = viewModel.useTopInnerPadding
    ScreenBackground {
        Scaffold (
            topBar = {
                HomepageTopAppBar(
                    navController = navController,
                    onTopPaddingChange = { value ->
                        viewModel.updateUseTopInnerPaddingState(value)
                    }
                )
            }
        ) { innerPadding ->
            ScreenBackground {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = if (useTopInnerPadding.value) innerPadding.calculateTopPadding() else 0.dp,
                            start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                            end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                            bottom = innerPadding.calculateBottomPadding()
                        )
                        .padding(16.dp)
                ) {
                    HomepageNavGraph(navController = navController)
                }
            }
        }
    }
}


