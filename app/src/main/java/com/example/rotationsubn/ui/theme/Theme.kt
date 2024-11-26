package com.example.rotationsubn.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun RNTheme(content: @Composable () -> Unit) {
    val isDarkMode = isSystemInDarkTheme()

    CompositionLocalProvider(
        localCorners provides corners,
        localTypography provides typography,
        localColors provides Colors.byMode(isDarkMode),
        localGaps provides gaps,
        content = content
    )
}

internal val localCorners = staticCompositionLocalOf { corners }
internal val localTypography = staticCompositionLocalOf { typography }
internal val localColors = staticCompositionLocalOf { Colors.byMode() }
internal val localGaps = staticCompositionLocalOf { gaps }

object RNTheme {
    val corners: Corners
        @Composable
        get() = localCorners.current

    val typography: Typography
        @Composable
        get() = localTypography.current

    val colors: Colors
        @Composable
        get() = localColors.current

    val gaps: Gaps
        @Composable
        get() = localGaps.current
}
