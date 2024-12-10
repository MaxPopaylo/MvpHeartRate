package com.example.mvpheartrate.presentation.common.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.mvpheartrate.R

data class HeartRateColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val tertiaryText: Color,
    val primaryTintColor: Color,
    val secondaryTintColor: Color,
)

data class HeartRateTypography(
    val w400: TextStyle,
    val w500: TextStyle,
    val w600: TextStyle,
    val w700: TextStyle
)

data class HeartRateImages(
    val logo: Painter
)

object HeartRateFonts {
    val rubikFamily: FontFamily by lazy {
        FontFamily(
            Font(R.font.rubik_font_regular_w400, FontWeight.W400),
            Font(R.font.rubik_font_medium_w500, FontWeight.W500),
            Font(R.font.rubik_font_semibold_w600, FontWeight.W600),
            Font(R.font.rubik_font_bold_w700, FontWeight.W700)
        )
    }

}

object HeartRateTheme {

    internal val colors: HeartRateColors
        @Composable @ReadOnlyComposable get() = LocalHeartRateColors.current

    internal val typography: HeartRateTypography
        @Composable @ReadOnlyComposable get() = LocalHeartRateTypography.current

    internal val images: HeartRateImages
        @Composable @ReadOnlyComposable get() = LocalHeartRateImages.current

}

internal val LocalHeartRateColors = staticCompositionLocalOf<HeartRateColors> {
    error("no colors provided")
}

internal val LocalHeartRateTypography = staticCompositionLocalOf<HeartRateTypography> {
    error("no text styles provided")
}

internal val LocalHeartRateImages = staticCompositionLocalOf<HeartRateImages> {
    error("no images provided")
}
