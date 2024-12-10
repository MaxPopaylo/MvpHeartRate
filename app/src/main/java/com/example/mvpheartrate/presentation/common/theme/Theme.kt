package com.example.mvpheartrate.presentation.common.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mvpheartrate.R

@Composable
fun MvpHeartRateTheme(
    content: @Composable () -> Unit
) {

    val colors = baseColorPalette

    val baseTextStyle = TextStyle(
        fontFamily = HeartRateFonts.rubikFamily,
        letterSpacing = 0.5.sp
    )
    val typography = HeartRateTypography(
        w400 = baseTextStyle.copy(
            fontWeight = FontWeight.W400
        ),
        w500 = baseTextStyle.copy(
            fontWeight = FontWeight.W500
        ),
        w600 = baseTextStyle.copy(
            fontWeight = FontWeight.W600
        ),
        w700 = baseTextStyle.copy(
            fontWeight = FontWeight.W700
        ),
    )

    val images =  HeartRateImages(
        logo = painterResource(R.drawable.heart),
    )

    CompositionLocalProvider(
        LocalHeartRateColors provides colors,
        LocalHeartRateTypography provides typography,
        LocalHeartRateImages provides  images,
        content = content
    )
}