package com.example.mvpheartrate.presentation.screen.result_history.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvpheartrate.domain.models.BpmData
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography
import com.example.mvpheartrate.presentation.common.util.LocalDateTimeFormatter

@Composable
fun ResultListCard(
    bpmData: BpmData
) {
    Column(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .fillMaxWidth()
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                color = Color(0xFFECF7FF),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${bpmData.bpm} BPM",
                    color = colors.primaryText,
                    style = typography.w400.copy(
                        fontSize = 30.sp
                    )
                )
            }

            Box (
                modifier = Modifier
                    .fillMaxHeight()
                    .width(5.dp)
                    .background(
                        color = colors.primaryTint,
                        shape = RoundedCornerShape(8.dp)
                    )
            )

            Box(
                Modifier
                    .weight(1f)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {

                Column {
                    Text(
                        text = LocalDateTimeFormatter.getTime(bpmData.time),
                        color = colors.secondaryText,
                        style = typography.w400.copy(
                            fontSize = 18.sp
                        )
                    )
                    Text(
                        text = LocalDateTimeFormatter.getDate(bpmData.time),
                        color = colors.secondaryText,
                        style = typography.w400.copy(
                            fontSize = 18.sp
                        )
                    )
                }
            }

        }

    }
}
