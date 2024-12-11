package com.example.mvpheartrate.presentation.screen.homepage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvpheartrate.presentation.common.composable.ScreenBackground
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography
import com.example.mvpheartrate.presentation.screen.homepage.composable.PulseMeasurementScreen


@Composable
fun HomepageScreen() {
    ScreenBackground {
        Scaffold (
            topBar = { HomepageTopAppBar() }
        ) { systemPadding ->
            ScreenBackground {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(systemPadding)
                        .padding(16.dp)
                ) {
                    PulseMeasurementScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomepageTopAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colors.primaryTint,
            titleContentColor = colors.tertiaryText
        ),
        title = {
            Text(
                text = "",
                color = colors.tertiaryText,
                style = typography.w400.copy(
                    fontSize = 20.sp
                )
            )
        },
        actions = {
            Text(
                text = "Історія",
                color = colors.tertiaryText,
                style = typography.w400.copy(
                    fontSize = 20.sp
                )
            )
        },
//        navigationIcon = {
//            Text(
//                text = "Title",
//                color = colors.tertiaryText,
//                style = typography.w400.copy(
//                    fontSize = 20.sp
//                )
//            )
//        }
    )
}
