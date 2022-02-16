package com.chuntian.composecookbookcopy.theme

import com.chuntian.data.db.Theme
import com.chuntian.theme.ColorPallet

data class AppThemeState(
    val darkTheme: Boolean = false,
    val pallet: ColorPallet = ColorPallet.PURPLE,
) {
    fun toTheme() = Theme(isDark = if (darkTheme) 1 else 0, pallet = pallet.ordinal)

    companion object {
        fun fromTheme(theme: Theme?) = if (theme == null) AppThemeState() else AppThemeState(
            darkTheme = theme.isDark == 1,
            pallet = ColorPallet.values()[theme.pallet]
        )
    }
}