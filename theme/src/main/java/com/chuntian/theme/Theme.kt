package com.chuntian.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkGreenColorPalette = darkColors(
    primary = Green200,
    primaryVariant = Green700,
    secondary = Teal200,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

private val DarkPurpleColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

private val DarkBlueColorPalette = darkColors(
    primary = Blue200,
    primaryVariant = Blue700,
    secondary = Teal200,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

private val DarkOrangeColorPalette = darkColors(
    primary = Orange200,
    primaryVariant = Orange700,
    secondary = Teal200,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

// Light pallets
private val LightGreenColorPalette = lightColors(
    primary = Green500,
    primaryVariant = Green700,
    secondary = Teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val LightPurpleColorPalette = lightColors(
    primary = Purple,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val LightBlueColorPalette = lightColors(
    primary = Blue500,
    primaryVariant = Blue700,
    secondary = Teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val LightOrangeColorPalette = lightColors(
    primary = Orange500,
    primaryVariant = Orange700,
    secondary = Teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

enum class ColorPallet {
    PURPLE, GREEN, ORANGE, BLUE
}

@Composable
fun ComposeCookBookCopyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorPallet: ColorPallet = ColorPallet.PURPLE,
    content: @Composable () -> Unit
) {
    val colors = when (colorPallet) {
        ColorPallet.GREEN -> if (darkTheme) DarkGreenColorPalette else LightGreenColorPalette
        ColorPallet.PURPLE -> if (darkTheme) DarkPurpleColorPalette else LightPurpleColorPalette
        ColorPallet.ORANGE -> if (darkTheme) DarkOrangeColorPalette else LightOrangeColorPalette
        ColorPallet.BLUE -> if (darkTheme) DarkBlueColorPalette else LightBlueColorPalette
    }
    MaterialTheme(colors = colors, typography = Typography, shapes = Shapes, content = content)
}