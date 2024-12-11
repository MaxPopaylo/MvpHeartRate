package com.example.mvpheartrate.presentation.screen.onboarding.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography
import com.example.mvpheartrate.presentation.screen.onboarding.model.OnboardingPage

@Composable
fun OnboardingPagerScreen(
    page: OnboardingPage
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.6f),
            painter = painterResource(page.image),
            contentDescription = "OnBoardingDescription",
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = page.title,
                color = colors.primaryText,
                style = typography.w600.copy(
                    fontSize = 22.sp
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = page.description,
                color = colors.primaryText,
                style = typography.w500.copy(
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}
