package com.example.mvpheartrate.presentation.screen.onboarding

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mvpheartrate.presentation.common.composable.HeartRateButton
import com.example.mvpheartrate.presentation.common.composable.ScreenBackground
import com.example.mvpheartrate.presentation.screen.onboarding.composable.OnboardingPagerIndicator
import com.example.mvpheartrate.presentation.screen.onboarding.composable.OnboardingPagerScreen
import com.example.mvpheartrate.presentation.screen.onboarding.model.OnboardingPage
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    navController: NavHostController
) {
    val pages = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third
    )
    val pagerState = rememberPagerState (pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()
    val buttonText = when (pages[pagerState.currentPage]) {
        OnboardingPage.First -> "Почати!"
        OnboardingPage.Second -> "Продовжити"
        OnboardingPage.Third -> "Почати!"
    }

    ScreenBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f),
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { position ->
                OnboardingPagerScreen(page = pages[position])
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                OnboardingPagerIndicator(pagerState)

                Spacer(modifier = Modifier.height(16.dp))
                HeartRateButton(
                    text = buttonText,
                    onClick = {
                        coroutineScope.launch {
                            if (pagerState.currentPage != pagerState.pageCount - 1) {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1,
                                    animationSpec = tween(
                                        durationMillis = 500,
                                        easing = LinearOutSlowInEasing
                                    )
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}


