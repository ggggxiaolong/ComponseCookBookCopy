package com.chuntian.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.platform.LocalContext

private val DarkGreenColorPalette = darkColorScheme(
    primary = green_theme_dark_primary,
    onPrimary = green_theme_dark_onPrimary,
    primaryContainer = green_theme_dark_primaryContainer,
    onPrimaryContainer = green_theme_dark_onPrimaryContainer,
    secondary = green_theme_dark_secondary,
    onSecondary = green_theme_dark_onSecondary,
    secondaryContainer = green_theme_dark_secondaryContainer,
    onSecondaryContainer = green_theme_dark_onSecondaryContainer,
    tertiary = green_theme_dark_tertiary,
    onTertiary = green_theme_dark_onTertiary,
    tertiaryContainer = green_theme_dark_tertiaryContainer,
    onTertiaryContainer = green_theme_dark_onTertiaryContainer,
    error = green_theme_dark_error,
    errorContainer = green_theme_dark_errorContainer,
    onError = green_theme_dark_onError,
    onErrorContainer = green_theme_dark_onErrorContainer,
    background = green_theme_dark_background,
    onBackground = green_theme_dark_onBackground,
    surface = green_theme_dark_surface,
    onSurface = green_theme_dark_onSurface,
    surfaceVariant = green_theme_dark_surfaceVariant,
    onSurfaceVariant = green_theme_dark_onSurfaceVariant,
    outline = green_theme_dark_outline,
    inverseOnSurface = green_theme_dark_inverseOnSurface,
    inverseSurface = green_theme_dark_inverseSurface,
    inversePrimary = green_theme_dark_inversePrimary,
)

private val DarkPurpleColorPalette = darkColorScheme(
    primary = purple_theme_dark_primary,
    onPrimary = purple_theme_dark_onPrimary,
    primaryContainer = purple_theme_dark_primaryContainer,
    onPrimaryContainer = purple_theme_dark_onPrimaryContainer,
    secondary = purple_theme_dark_secondary,
    onSecondary = purple_theme_dark_onSecondary,
    secondaryContainer = purple_theme_dark_secondaryContainer,
    onSecondaryContainer = purple_theme_dark_onSecondaryContainer,
    tertiary = purple_theme_dark_tertiary,
    onTertiary = purple_theme_dark_onTertiary,
    tertiaryContainer = purple_theme_dark_tertiaryContainer,
    onTertiaryContainer = purple_theme_dark_onTertiaryContainer,
    error = purple_theme_dark_error,
    errorContainer = purple_theme_dark_errorContainer,
    onError = purple_theme_dark_onError,
    onErrorContainer = purple_theme_dark_onErrorContainer,
    background = purple_theme_dark_background,
    onBackground = purple_theme_dark_onBackground,
    surface = purple_theme_dark_surface,
    onSurface = purple_theme_dark_onSurface,
    surfaceVariant = purple_theme_dark_surfaceVariant,
    onSurfaceVariant = purple_theme_dark_onSurfaceVariant,
    outline = purple_theme_dark_outline,
    inverseOnSurface = purple_theme_dark_inverseOnSurface,
    inverseSurface = purple_theme_dark_inverseSurface,
    inversePrimary = purple_theme_dark_inversePrimary,
)

private val DarkBlueColorPalette = darkColorScheme(
    primary = blue_theme_dark_primary,
    onPrimary = blue_theme_dark_onPrimary,
    primaryContainer = blue_theme_dark_primaryContainer,
    onPrimaryContainer = blue_theme_dark_onPrimaryContainer,
    secondary = blue_theme_dark_secondary,
    onSecondary = blue_theme_dark_onSecondary,
    secondaryContainer = blue_theme_dark_secondaryContainer,
    onSecondaryContainer = blue_theme_dark_onSecondaryContainer,
    tertiary = blue_theme_dark_tertiary,
    onTertiary = blue_theme_dark_onTertiary,
    tertiaryContainer = blue_theme_dark_tertiaryContainer,
    onTertiaryContainer = blue_theme_dark_onTertiaryContainer,
    error = blue_theme_dark_error,
    errorContainer = blue_theme_dark_errorContainer,
    onError = blue_theme_dark_onError,
    onErrorContainer = blue_theme_dark_onErrorContainer,
    background = blue_theme_dark_background,
    onBackground = blue_theme_dark_onBackground,
    surface = blue_theme_dark_surface,
    onSurface = blue_theme_dark_onSurface,
    surfaceVariant = blue_theme_dark_surfaceVariant,
    onSurfaceVariant = blue_theme_dark_onSurfaceVariant,
    outline = blue_theme_dark_outline,
    inverseOnSurface = blue_theme_dark_inverseOnSurface,
    inverseSurface = blue_theme_dark_inverseSurface,
    inversePrimary = blue_theme_dark_inversePrimary,
)

private val DarkOrangeColorPalette = darkColorScheme(
    primary = orange_theme_dark_primary,
    onPrimary = orange_theme_dark_onPrimary,
    primaryContainer = orange_theme_dark_primaryContainer,
    onPrimaryContainer = orange_theme_dark_onPrimaryContainer,
    secondary = orange_theme_dark_secondary,
    onSecondary = orange_theme_dark_onSecondary,
    secondaryContainer = orange_theme_dark_secondaryContainer,
    onSecondaryContainer = orange_theme_dark_onSecondaryContainer,
    tertiary = orange_theme_dark_tertiary,
    onTertiary = orange_theme_dark_onTertiary,
    tertiaryContainer = orange_theme_dark_tertiaryContainer,
    onTertiaryContainer = orange_theme_dark_onTertiaryContainer,
    error = orange_theme_dark_error,
    errorContainer = orange_theme_dark_errorContainer,
    onError = orange_theme_dark_onError,
    onErrorContainer = orange_theme_dark_onErrorContainer,
    background = orange_theme_dark_background,
    onBackground = orange_theme_dark_onBackground,
    surface = orange_theme_dark_surface,
    onSurface = orange_theme_dark_onSurface,
    surfaceVariant = orange_theme_dark_surfaceVariant,
    onSurfaceVariant = orange_theme_dark_onSurfaceVariant,
    outline = orange_theme_dark_outline,
    inverseOnSurface = orange_theme_dark_inverseOnSurface,
    inverseSurface = orange_theme_dark_inverseSurface,
    inversePrimary = orange_theme_dark_inversePrimary,
)

// Light pallets
private val LightGreenColorPalette = lightColorScheme(
    primary = green_theme_light_primary,
    onPrimary = green_theme_light_onPrimary,
    primaryContainer = green_theme_light_primaryContainer,
    onPrimaryContainer = green_theme_light_onPrimaryContainer,
    secondary = green_theme_light_secondary,
    onSecondary = green_theme_light_onSecondary,
    secondaryContainer = green_theme_light_secondaryContainer,
    onSecondaryContainer = green_theme_light_onSecondaryContainer,
    tertiary = green_theme_light_tertiary,
    onTertiary = green_theme_light_onTertiary,
    tertiaryContainer = green_theme_light_tertiaryContainer,
    onTertiaryContainer = green_theme_light_onTertiaryContainer,
    error = green_theme_light_error,
    errorContainer = green_theme_light_errorContainer,
    onError = green_theme_light_onError,
    onErrorContainer = green_theme_light_onErrorContainer,
    background = green_theme_light_background,
    onBackground = green_theme_light_onBackground,
    surface = green_theme_light_surface,
    onSurface = green_theme_light_onSurface,
    surfaceVariant = green_theme_light_surfaceVariant,
    onSurfaceVariant = green_theme_light_onSurfaceVariant,
    outline = green_theme_light_outline,
    inverseOnSurface = green_theme_light_inverseOnSurface,
    inverseSurface = green_theme_light_inverseSurface,
    inversePrimary = green_theme_light_inversePrimary,
)

private val LightPurpleColorPalette = lightColorScheme(
    primary = purple_theme_light_primary,
    onPrimary = purple_theme_light_onPrimary,
    primaryContainer = purple_theme_light_primaryContainer,
    onPrimaryContainer = purple_theme_light_onPrimaryContainer,
    secondary = purple_theme_light_secondary,
    onSecondary = purple_theme_light_onSecondary,
    secondaryContainer = purple_theme_light_secondaryContainer,
    onSecondaryContainer = purple_theme_light_onSecondaryContainer,
    tertiary = purple_theme_light_tertiary,
    onTertiary = purple_theme_light_onTertiary,
    tertiaryContainer = purple_theme_light_tertiaryContainer,
    onTertiaryContainer = purple_theme_light_onTertiaryContainer,
    error = purple_theme_light_error,
    errorContainer = purple_theme_light_errorContainer,
    onError = purple_theme_light_onError,
    onErrorContainer = purple_theme_light_onErrorContainer,
    background = purple_theme_light_background,
    onBackground = purple_theme_light_onBackground,
    surface = purple_theme_light_surface,
    onSurface = purple_theme_light_onSurface,
    surfaceVariant = purple_theme_light_surfaceVariant,
    onSurfaceVariant = purple_theme_light_onSurfaceVariant,
    outline = purple_theme_light_outline,
    inverseOnSurface = purple_theme_light_inverseOnSurface,
    inverseSurface = purple_theme_light_inverseSurface,
    inversePrimary = purple_theme_light_inversePrimary,
)

private val LightBlueColorPalette = lightColorScheme(
    primary = blue_theme_light_primary,
    onPrimary = blue_theme_light_onPrimary,
    primaryContainer = blue_theme_light_primaryContainer,
    onPrimaryContainer = blue_theme_light_onPrimaryContainer,
    secondary = blue_theme_light_secondary,
    onSecondary = blue_theme_light_onSecondary,
    secondaryContainer = blue_theme_light_secondaryContainer,
    onSecondaryContainer = blue_theme_light_onSecondaryContainer,
    tertiary = blue_theme_light_tertiary,
    onTertiary = blue_theme_light_onTertiary,
    tertiaryContainer = blue_theme_light_tertiaryContainer,
    onTertiaryContainer = blue_theme_light_onTertiaryContainer,
    error = blue_theme_light_error,
    errorContainer = blue_theme_light_errorContainer,
    onError = blue_theme_light_onError,
    onErrorContainer = blue_theme_light_onErrorContainer,
    background = blue_theme_light_background,
    onBackground = blue_theme_light_onBackground,
    surface = blue_theme_light_surface,
    onSurface = blue_theme_light_onSurface,
    surfaceVariant = blue_theme_light_surfaceVariant,
    onSurfaceVariant = blue_theme_light_onSurfaceVariant,
    outline = blue_theme_light_outline,
    inverseOnSurface = blue_theme_light_inverseOnSurface,
    inverseSurface = blue_theme_light_inverseSurface,
    inversePrimary = blue_theme_light_inversePrimary,
)

private val LightOrangeColorPalette = lightColorScheme(
    primary = orange_theme_light_primary,
    onPrimary = orange_theme_light_onPrimary,
    primaryContainer = orange_theme_light_primaryContainer,
    onPrimaryContainer = orange_theme_light_onPrimaryContainer,
    secondary = orange_theme_light_secondary,
    onSecondary = orange_theme_light_onSecondary,
    secondaryContainer = orange_theme_light_secondaryContainer,
    onSecondaryContainer = orange_theme_light_onSecondaryContainer,
    tertiary = orange_theme_light_tertiary,
    onTertiary = orange_theme_light_onTertiary,
    tertiaryContainer = orange_theme_light_tertiaryContainer,
    onTertiaryContainer = orange_theme_light_onTertiaryContainer,
    error = orange_theme_light_error,
    errorContainer = orange_theme_light_errorContainer,
    onError = orange_theme_light_onError,
    onErrorContainer = orange_theme_light_onErrorContainer,
    background = orange_theme_light_background,
    onBackground = orange_theme_light_onBackground,
    surface = orange_theme_light_surface,
    onSurface = orange_theme_light_onSurface,
    surfaceVariant = orange_theme_light_surfaceVariant,
    onSurfaceVariant = orange_theme_light_onSurfaceVariant,
    outline = orange_theme_light_outline,
    inverseOnSurface = orange_theme_light_inverseOnSurface,
    inverseSurface = orange_theme_light_inverseSurface,
    inversePrimary = orange_theme_light_inversePrimary,
)

enum class ColorPallet {
    PURPLE, GREEN, ORANGE, BLUE, WALLPAPER
}

@Composable
fun ComposeCookBookCopyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorPallet: ColorPallet = ColorPallet.WALLPAPER,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colors = when (colorPallet) {
        ColorPallet.GREEN -> if (darkTheme) DarkGreenColorPalette else LightGreenColorPalette
        ColorPallet.PURPLE -> if (darkTheme) DarkPurpleColorPalette else LightPurpleColorPalette
        ColorPallet.ORANGE -> if (darkTheme) DarkOrangeColorPalette else LightOrangeColorPalette
        ColorPallet.BLUE -> if (darkTheme) DarkBlueColorPalette else LightBlueColorPalette
        ColorPallet.WALLPAPER -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            if (darkTheme)
                dynamicDarkColorScheme(context)
            else
                dynamicLightColorScheme(context)
        else
            if (darkTheme)
                DarkGreenColorPalette
            else LightGreenColorPalette
    }
    MaterialTheme(colorScheme = colors, content = content)
}