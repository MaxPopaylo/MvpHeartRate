package com.example.mvpheartrate.presentation.screen.homepage.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvpheartrate.R
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.images
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography
import com.example.mvpheartrate.presentation.common.theme.MvpHeartRateTheme
import com.example.mvpheartrate.presentation.screen.homepage.HomepageScreen

@Composable
fun WaitingScreen() {
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
                onClick = { /*TODO*/ }
            ) {
                Box (
                    modifier = Modifier
                        .fillMaxHeight(0.8f)
                        .aspectRatio(1.1f)
                        .shadow(
                            elevation = 5.dp,
                            shape = CircleShape
                        )
                )
                Image(

                    painter = painterResource(R.drawable.light_heart_button),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

        }
    }
}

@Preview
@Composable
fun WaitingScreenPre() {
    MvpHeartRateTheme {
        HomepageScreen()
    }
}