package com.chuntian.composecookbookcopy.theme

import com.chuntian.theme.ColorPallet

data class AppThemeState(
    val darkTheme: Boolean = false,
    val pallet: ColorPallet = ColorPallet.PURPLE,
)