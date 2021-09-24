package com.chuntian.data.model

import java.util.*

sealed class HomeScreenItems {
    object Dialogs : HomeScreenItems()
    object TabLayout : HomeScreenItems()
    object Carousel : HomeScreenItems()
    object Layouts : HomeScreenItems()
    data class ListView(val type: String = "Vertical") : HomeScreenItems()
    object AdvanceLists : HomeScreenItems()
    object ConstrainsLayout : HomeScreenItems()
    object CollapsingAppBar : HomeScreenItems()
    object BottomAppBar : HomeScreenItems()
    object BottomSheets : HomeScreenItems()
    object Modifiers : HomeScreenItems()
    object AndroidView : HomeScreenItems()
    object PullRefresh : HomeScreenItems()
    object CustomFling : HomeScreenItems()
    object MotionLayout : HomeScreenItems()

    val name: String
        get() = when(this) {
            is Dialogs -> "Dialogs"
            is TabLayout -> "TabLayout"
            is Carousel -> "Carousel"
            is ListView -> "$type ListView"
            ConstrainsLayout -> "Constraint Layout"
            MotionLayout -> "Motion Layout"
            CollapsingAppBar -> "Collapsing AppBar"
            BottomAppBar -> "BottomAppBar"
            BottomSheets -> "BottomSheets"
            Layouts -> "layouts"
            Modifiers -> "Modifiers"
            AndroidView -> "Compose X Android views"
            AdvanceLists -> "Advance List"
            PullRefresh -> "Pull refresh demos"
            CustomFling -> "Custom Fling"
        }
}