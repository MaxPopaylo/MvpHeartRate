package com.example.mvpheartrate.presentation.screen.homepage.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mvpheartrate.presentation.common.navigation.HomePageScreens
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomepageTopAppBar(
    navController: NavHostController,
    onTopPaddingChange: (Boolean) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val title = when(currentRoute) {
        HomePageScreens.ResultScreen::class.qualifiedName + "/{result}" -> "Результат"
        HomePageScreens.ResultListScreen::class.qualifiedName -> "Історія"
        else -> ""
    }

    val isHaveActions: Boolean = when(currentRoute) {
        HomePageScreens.ResultScreen::class.qualifiedName + "/{result}" -> true
        HomePageScreens.WaitingScreen::class.qualifiedName -> true
        else -> false
    }

    val isHaveNavigationIcon: Boolean = when(currentRoute) {
        HomePageScreens.ResultListScreen::class.qualifiedName -> true
        else -> false
    }

    val topBarDestination = listOf(
        HomePageScreens.WaitingScreen::class.qualifiedName,
        HomePageScreens.ResultScreen::class.qualifiedName + "/{result}",
        HomePageScreens.ResultListScreen::class.qualifiedName
    ).contains(currentRoute)

    onTopPaddingChange(topBarDestination)

    if (topBarDestination) {
        TopAppBar(
            windowInsets  = WindowInsets(
                top = 0.dp,
                bottom = 0.dp
            ),
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = colors.primaryTint,
                titleContentColor = colors.tertiaryText
            ),
            title = {
                AnimatedVisibility(
                    visible = title.isNotBlank(),
                    enter = fadeIn(animationSpec = tween(300)),
                    exit = fadeOut(animationSpec = tween(500))
                ) {
                    Text(
                        text = title,
                        color = colors.tertiaryText,
                        style = typography.w400.copy(
                            fontSize = 20.sp
                        )
                    )
                }
            },
            actions = {
                AnimatedVisibility(
                    visible = isHaveActions,
                    enter = fadeIn(animationSpec = tween(300)),
                    exit = fadeOut(animationSpec = tween(500))
                ) {
                    TextButton(
                        onClick = {
                            navController.navigate(HomePageScreens.ResultListScreen)
                        }
                    ) {
                        Row {
                            Text(
                                text = "Історія",
                                color = colors.tertiaryText,
                                style = typography.w400.copy(
                                    fontSize = 20.sp
                                )
                            )
                            Icon(
                                imageVector = Icons.Filled.Refresh,
                                tint = colors.tertiaryText,
                                contentDescription = "History"
                            )
                        }
                    }
                }
            },
            navigationIcon = {
                AnimatedVisibility(
                    visible = isHaveNavigationIcon,
                    enter = fadeIn(animationSpec = tween(300)),
                    exit = fadeOut(animationSpec = tween(500))
                ) {
                    TextButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = colors.tertiaryText,
                            contentDescription = "History"
                        )
                    }
                }
            }
        )
    }
}