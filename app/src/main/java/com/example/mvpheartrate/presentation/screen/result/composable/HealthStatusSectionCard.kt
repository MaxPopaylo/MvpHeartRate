package com.example.mvpheartrate.presentation.screen.result.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography
import com.example.mvpheartrate.presentation.screen.result.models.HealthStatusSection

@Composable
fun HealthStatusSectionCard(
    section: HealthStatusSection,
    itCurrent: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .background(
                    color = colors.secondaryBackground,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(4.dp)
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Box (
                modifier = Modifier
                    .clip(CircleShape)
                    .size(10.dp)
                    .background(
                        color = section.color,
                        shape = CircleShape
                    )
            )
            Text(
                text = section.text,
                color = colors.primaryText,
                style = typography.w500.copy(
                    fontSize = 10.sp
                )
            )
        }

        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = section.bpmRange,
                color = if (itCurrent) colors.primaryText else colors.secondaryText,
                style = typography.w500.copy(
                    fontSize = 12.sp
                )
            )
        }
    }
}