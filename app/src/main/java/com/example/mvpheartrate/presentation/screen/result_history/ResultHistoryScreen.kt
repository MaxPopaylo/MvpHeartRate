package com.example.mvpheartrate.presentation.screen.result_history


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mvpheartrate.presentation.common.composable.HeartRateButton
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.colors
import com.example.mvpheartrate.presentation.common.theme.HeartRateTheme.typography
import com.example.mvpheartrate.presentation.screen.result_history.composable.ResultListCard

import kotlin.math.min

@Composable
fun ResultHistoryScreen(
    viewModel: ResultHistoryViewModel = hiltViewModel()
) {
    val resultList by viewModel.resultList.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.updateResultList()
    }

    Box {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                state = lazyListState,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = spacedByWithFooter(14.dp)
            ) {
                items(resultList) { data ->
                    ResultListCard(
                        bpmData = data
                    )
                }
                item {
                    if (resultList.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Тут нічого немає ще..",
                                color = colors.primaryText,
                                style = typography.w600.copy(
                                    fontSize = 24.sp
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                item {
                    HeartRateButton(
                        text = "Очистити історію",
                        onClick = {
                            viewModel.clearAllResultList()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(4.dp))
            CustomVerticalScrollbar(
                modifier = Modifier
                    .fillMaxHeight(0.95f),
                listState = lazyListState
            )

        }

    }


}

@Composable
fun CustomVerticalScrollbar(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    progressColor: Color = colors.primaryTint,
    backgroundColor: Color = colors.secondaryTint,
    clipShape: Shape = RoundedCornerShape(16.dp)
) {
    val scrollbarState by remember {
        derivedStateOf {
            val totalItems = listState.layoutInfo.totalItemsCount
            val visibleItems = listState.layoutInfo.visibleItemsInfo.size
            val viewportHeight = listState.layoutInfo.viewportSize.height.toFloat()

            val maxScrollOffset = totalItems - visibleItems

            val firstVisibleItemIndex = listState.firstVisibleItemIndex
            val firstVisibleItemScrollOffset = listState.firstVisibleItemScrollOffset.toFloat()

            val scrollProgress = ((firstVisibleItemIndex * viewportHeight + firstVisibleItemScrollOffset) /
                    (maxScrollOffset * viewportHeight)).coerceIn(0f, 1f)

            val thumbSize = (visibleItems.toFloat() / totalItems).coerceIn(0f, 1f)

            Pair(scrollProgress, thumbSize)
        }
    }

    val scrollProgress = scrollbarState.first
    val thumbSize = scrollbarState.second

    Box(
        modifier = modifier
            .clip(clipShape)
            .width(10.dp)
            .background(backgroundColor)
    ) {

        Box(
            modifier = Modifier
                .clip(clipShape)
                .fillMaxWidth()
                .fillMaxHeight(thumbSize)
                .align(Alignment.TopStart)
                .offset(y = (scrollProgress * (1 - thumbSize)).dp)
                .background(progressColor)
        )
    }
}



fun spacedByWithFooter(space: Dp) = object : Arrangement.Vertical {
    override val spacing = space

    override fun Density.arrange(
        totalSize: Int,
        sizes: IntArray,
        outPositions: IntArray,
    ) {
        if (sizes.isEmpty()) return
        val spacePx = space.roundToPx()

        var occupied = 0
        var lastSpace = 0

        sizes.forEachIndexed { index, size ->

            if (index == sizes.lastIndex) {
                outPositions[index] = totalSize - size
            } else {
                outPositions[index] = min(occupied, totalSize - size)
            }
            lastSpace = min(spacePx, totalSize - outPositions[index] - size)
            occupied = outPositions[index] + size + lastSpace
        }
        occupied -= lastSpace
    }
}


