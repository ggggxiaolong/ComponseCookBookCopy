package com.chuntian.composecookbookcopy.utils

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import com.chuntian.composecookbookcopy.theme.AppThemeState

val LocalNavControl = staticCompositionLocalOf<NavController> {
    noLocalProvidedFor("LocalNavControl")
}

val LocalThemeState = staticCompositionLocalOf<AppThemeState> {
    noLocalProvidedFor("LocalThemeState")
}

private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}