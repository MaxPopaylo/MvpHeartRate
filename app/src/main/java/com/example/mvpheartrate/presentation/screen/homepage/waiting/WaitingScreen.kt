package com.example.mvpheartrate.presentation.screen.homepage.waiting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mvpheartrate.R
import com.example.mvpheartrate.presentation.common.navigation.HomePageScreens
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.images
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography

@Composable
fun WaitingScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
                .padding(16.dp),
            contentAlignment =  Alignment.TopCenter
        ) {
            Text(
                text = "Виконайте своє перше вимірювань!",
                color = colors.primaryText,
                style = typography.w700.copy(
                    fontSize = 26.sp
                ),
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = images.logo,
                    contentDescription = "Logo",

                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                modifier = Modifier
                    .fillMaxSize(),
                onClick = {
                    navController.navigate(HomePageScreens.PulseMeasurementScreen)
                }
            ) {
                Box (
                    modifier = Modifier
                        .fillMaxHeight(0.8f)
                        .wrapContentHeight()
                        .aspectRatio(1.1f)
                        .shadow(
                            elevation = 5.dp,
                            shape = CircleShape
                        )
                )
                Image(
                    modifier = Modifier
                        .fillMaxHeight(1f)
                        .wrapContentHeight()
                        .aspectRatio(1f),
                    painter = painterResource(R.drawable.light_heart_button),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

        }
    }
}
