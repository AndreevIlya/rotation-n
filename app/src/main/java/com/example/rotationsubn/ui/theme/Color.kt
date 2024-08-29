package com.example.rotationsubn.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
class Colors(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val inversePrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val inverseSurface: Color,
    val inverseOnSurface: Color,
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,
    val outline: Color,
    val shadow: Color
) {
    companion object {
        fun byMode(isDark: Boolean = false) = if (isDark) darkColors else lightColors
    }
}

val light_primary = Color(0xFF006d3a)
val light_onPrimary = Color(0xFFffffff)
val light_primaryContainer = Color(0xFF99f6b5)
val light_onPrimaryContainer = Color(0xFF00210d)
val light_secondary = Color(0xFF006f00)
val light_onSecondary = Color(0xFFffffff)
val light_secondaryContainer = Color(0xFF8dfb76)
val light_onSecondaryContainer = Color(0xFF002200)
val light_tertiary = Color(0xFF376b00)
val light_onTertiary = Color(0xFFffffff)
val light_tertiaryContainer = Color(0xFFa8f85c)
val light_onTertiaryContainer = Color(0xFF0c2000)
val light_error = Color(0xFFba1b1b)
val light_errorContainer = Color(0xFFffdad4)
val light_onError = Color(0xFFffffff)
val light_onErrorContainer = Color(0xFF410001)
val light_background = Color(0xFFfefcf4)
val light_onBackground = Color(0xFF1b1c17)
val light_surface = Color(0xFFfefcf4)
val light_onSurface = Color(0xFF1b1c17)
val light_surfaceVariant = Color(0xFFdde5db)
val light_onSurfaceVariant = Color(0xFF414942)
val light_outline = Color(0xFF717971)
val light_inverseOnSurface = Color(0xFFf3f1e9)
val light_inverseSurface = Color(0xFF30312b)
val light_inversePrimary = Color(0xFF7dda9b)
val light_shadow = Color(0xFF000000)

val dark_primary = Color(0xFF7dda9b)
val dark_onPrimary = Color(0xFF00391b)
val dark_primaryContainer = Color(0xFF005229)
val dark_onPrimaryContainer = Color(0xFF99f6b5)
val dark_secondary = Color(0xFF72de5d)
val dark_onSecondary = Color(0xFF003a00)
val dark_secondaryContainer = Color(0xFF005300)
val dark_onSecondaryContainer = Color(0xFF8dfb76)
val dark_tertiary = Color(0xFF8edb42)
val dark_onTertiary = Color(0xFF193800)
val dark_tertiaryContainer = Color(0xFF275000)
val dark_onTertiaryContainer = Color(0xFFa8f85c)
val dark_error = Color(0xFFffb4a9)
val dark_errorContainer = Color(0xFF930006)
val dark_onError = Color(0xFF680003)
val dark_onErrorContainer = Color(0xFFffdad4)
val dark_background = Color(0xFF1b1c17)
val dark_onBackground = Color(0xFFe4e2db)
val dark_surface = Color(0xFF1b1c17)
val dark_onSurface = Color(0xFFe4e2db)
val dark_surfaceVariant = Color(0xFF414942)
val dark_onSurfaceVariant = Color(0xFFc0c9bf)
val dark_outline = Color(0xFF8a938a)
val dark_inverseOnSurface = Color(0xFF1b1c17)
val dark_inverseSurface = Color(0xFFe4e2db)
val dark_inversePrimary = Color(0xFF006d3a)
val dark_shadow = Color(0xFF000000)

val seed = Color(0xFF005c31)
val error = Color(0xFFba1b1b)

val lightColors = Colors(
    primary = light_primary,
    onPrimary = light_onPrimary,
    primaryContainer = light_primaryContainer,
    onPrimaryContainer = light_onPrimaryContainer,
    secondary = light_secondary,
    onSecondary = light_onSecondary,
    secondaryContainer = light_secondaryContainer,
    onSecondaryContainer = light_onSecondaryContainer,
    tertiary = light_tertiary,
    onTertiary = light_onTertiary,
    tertiaryContainer = light_tertiaryContainer,
    onTertiaryContainer = light_onTertiaryContainer,
    error = light_error,
    errorContainer = light_errorContainer,
    onError = light_onError,
    onErrorContainer = light_onErrorContainer,
    background = light_background,
    onBackground = light_onBackground,
    surface = light_surface,
    onSurface = light_onSurface,
    surfaceVariant = light_surfaceVariant,
    onSurfaceVariant = light_onSurfaceVariant,
    outline = light_outline,
    inverseOnSurface = light_inverseOnSurface,
    inverseSurface = light_inverseSurface,
    inversePrimary = light_inversePrimary,
    shadow = light_shadow
)
val darkColors = Colors(
    primary = dark_primary,
    onPrimary = dark_onPrimary,
    primaryContainer = dark_primaryContainer,
    onPrimaryContainer = dark_onPrimaryContainer,
    secondary = dark_secondary,
    onSecondary = dark_onSecondary,
    secondaryContainer = dark_secondaryContainer,
    onSecondaryContainer = dark_onSecondaryContainer,
    tertiary = dark_tertiary,
    onTertiary = dark_onTertiary,
    tertiaryContainer = dark_tertiaryContainer,
    onTertiaryContainer = dark_onTertiaryContainer,
    error = dark_error,
    errorContainer = dark_errorContainer,
    onError = dark_onError,
    onErrorContainer = dark_onErrorContainer,
    background = dark_background,
    onBackground = dark_onBackground,
    surface = dark_surface,
    onSurface = dark_onSurface,
    surfaceVariant = dark_surfaceVariant,
    onSurfaceVariant = dark_onSurfaceVariant,
    outline = dark_outline,
    inverseOnSurface = dark_inverseOnSurface,
    inverseSurface = dark_inverseSurface,
    inversePrimary = dark_inversePrimary,
    shadow = dark_shadow
)
