package com.fcerio.common.compose

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun DDriversTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    colors: CommonColor = if (isDarkTheme) DarkColor else LightColor,
    typography: CommonTypography = AppTheme.typography,
    shapes: Shapes = Shapes(
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(8.dp)
    ),
    content: @Composable () -> Unit
) {
    val materialTypography = Typography(
        displayLarge = typography.largeTitle,
        displayMedium = typography.title1,
        displaySmall = typography.title2,
        headlineLarge = typography.title3,
        bodyLarge = typography.body1,
        bodyMedium = typography.body2,
        titleMedium = typography.title1,
        titleSmall = typography.subHeadline,
        labelLarge = typography.caption,
        labelMedium = typography.headline
    )

    MaterialTheme(
        typography = materialTypography,
        shapes = shapes
    ) {
        CompositionLocalProvider(
            LocalCommonColors provides colors,
            LocalTypography provides typography,
            content = content
        )
    }
}

object AppTheme {

    val colors: CommonColor
        @Composable
        @ReadOnlyComposable
        get() = LocalCommonColors.current

    val typography: CommonTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}