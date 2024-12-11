package com.example.mvpheartrate.presentation.screen.homepage.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvpheartrate.R
import com.example.mvpheartrate.presentation.common.composable.HeartRateLinearProgressIndicator
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography
import com.example.mvpheartrate.presentation.common.theme.MvpHeartRateTheme
import com.example.mvpheartrate.presentation.screen.homepage.HomepageScreen

@Composable
fun PulseMeasurementScreen() {
   Box(
       modifier = Modifier
           .fillMaxSize(),
       contentAlignment = Alignment.TopEnd
   ) {
       IconButton(
           onClick = { /*TODO*/ }
       ) {
           Icon(
               imageVector = Icons.Filled.Close,
               tint = colors.secondaryText,
               contentDescription = "Close"
           )
       }
       Column {
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .weight(5f)
                   .padding(16.dp)
                   .padding(vertical = 16.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.SpaceBetween
           ) {
               PulseHeader(fingerDetected = true)

               HeartRateDisplay(
                   modifier = Modifier
                   .fillMaxWidth(0.9f)
               )

               if (true) {
                   Image(
                       painter = painterResource(R.drawable.hand_plus_phone),
                       contentDescription = "HandPlusPhone",
                       modifier = Modifier
                           .fillMaxWidth(0.4f)
                           .offset(x = 15.dp),
                   )
               } else {
                   Row(
                       Modifier
                           .fillMaxWidth()
                           .fillMaxHeight(0.3f),
                       verticalAlignment = Alignment.Top
                   ) {
                       HeartRateLinearProgressIndicator(
                           progress = 0.53f
                       )
                   }
               }
           }

           Box(
               modifier = Modifier
                   .fillMaxWidth()
                   .weight(1f)
           )
       }
   }
}

@Composable
fun PulseHeader(fingerDetected: Boolean) {
    val titleText = if (fingerDetected) "Йде Вимірювання."
    else "Палець не виявлено"

    val descriptionText = if (fingerDetected) "Визначаємо ваш пульс. Утримуйте!"
    else "Щільно прикладіть палець до камери"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(42.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFF4CB8FF),
                    shape = CircleShape
                )
                .background(Color.Red)
        )
        Text(
            text = titleText,
            color = colors.primaryText,
            style = typography.w600.copy(
                fontSize = 18.sp
            ),
            textAlign = TextAlign.Center
        )
        Text(
            text = descriptionText,
            color = Color(0xFFEAF6FF),
            style = typography.w400.copy(
                fontSize = 16.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun HeartRateDisplay(
    modifier: Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(R.drawable.heart_background),
            contentDescription = "HeartBackground"
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "--",
                color = colors.tertiaryText,
                style = typography.w700.copy(
                    fontSize = 62.sp
                ),
                textAlign = TextAlign.Center
            )
            Text(
                text = "bpm",
                color = colors.tertiaryText,
                style = typography.w400.copy(
                    fontSize = 22.sp
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun HomepageScreenPrew() {
    MvpHeartRateTheme {
        HomepageScreen()
    }
}