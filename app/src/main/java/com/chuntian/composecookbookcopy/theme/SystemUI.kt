package com.chuntian.composecookbookcopy.theme

import android.os.Build
import android.view.View
import android.view.Window
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb

class SystemUIController(private val window: Window) {

    fun setStatusBarColor(
        color: Color,
        darkIcons: Boolean = color.luminance() > 0.5f,
        transformColorForLightContent: (Color) -> Color = BlackScrimmed
    ) {
        val statusBarColor = when {
            darkIcons -> transformColorForLightContent(color)
            else -> color
        }
        window.statusBarColor = statusBarColor.toArgb()
        if (darkIcons) {
            window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    fun setNavigationBarColor(
        color: Color,
        darkIcons: Boolean = color.luminance() > 0.5f,
        transformColorForLightContent: (Color) -> Color = BlackScrimmed
    ) {
        val navBarColor = when {
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> Color.Transparent
            darkIcons && Build.VERSION.SDK_INT < Build.VERSION_CODES.O -> transformColorForLightContent(
                color
            )
            else -> transformColorForLightContent(color)
        }
        window.navigationBarColor = navBarColor.toArgb()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            if (darkIcons) {
//                window.decorView.systemUiVisibility =
//                    window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
//            } else {
//                window.decorView.systemUiVisibility =
//                    window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
//            }
//        }
    }

    fun setSystemBarsColor(
        color: Color,
        darkIcons: Boolean = color.luminance() > 0.5f,
        transformColorForLightContent: (Color) -> Color = BlackScrimmed
    ) {
        setStatusBarColor(color, darkIcons, transformColorForLightContent)
        setNavigationBarColor(color, darkIcons, transformColorForLightContent)
    }
}

private val BlackScrim = Color(0f, 0f, 0f, 0.2f)
private val BlackScrimmed: (Color) -> Color = { original ->
    BlackScrim.compositeOver(original)
}