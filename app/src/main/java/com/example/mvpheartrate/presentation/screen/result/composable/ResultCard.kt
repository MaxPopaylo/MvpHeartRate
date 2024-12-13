package com.example.mvpheartrate.presentation.screen.result.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvpheartrate.domain.models.BpmData
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography
import com.example.mvpheartrate.presentation.common.theme.MvpHeartRateTheme
import com.example.mvpheartrate.presentation.common.util.LocalDateTimeFormatter
import com.example.mvpheartrate.presentation.screen.result.models.HealthStatusSection
import java.time.LocalDateTime

@Composable
fun ResultCard(
    bpmData: BpmData,
    sectionList: List<HealthStatusSection>,
    currentSection: HealthStatusSection,
    currentProgress: Float
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
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Ваш результат",
                    color = colors.primaryText,
                    style = typography.w500.copy(
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = currentSection.text,
                    color = currentSection.color,
                    style = typography.w700.copy(
                        fontSize = 24.sp
                    )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Time",
                    tint = colors.secondaryText
                )
                Column {
                    Text(
                        text = LocalDateTimeFormatter.getTime(bpmData.time),
                        color = colors.secondaryText,
                        style = typography.w400.copy(
                            fontSize = 14.sp
                        )
                    )
                    Text(
                        text = LocalDateTimeFormatter.getDate(bpmData.time),
                        color = colors.secondaryText,
                        style = typography.w400.copy(
                            fontSize = 14.sp
                        )
                    )
                }
            }

        }

        HeartRateBpmHealthStatusIndicator(
            progress = currentProgress,
            sectionList = sectionList
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            sectionList.forEach { section ->
                HealthStatusSectionCard(
                    section = section,
                    itCurrent = (section == currentSection)
                )
            }
        }
    }
}

