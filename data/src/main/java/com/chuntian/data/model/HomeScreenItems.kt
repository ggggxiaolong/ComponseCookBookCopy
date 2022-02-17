package com.chuntian.data.model

import com.chuntian.data.PATH

sealed class HomeScreenItems {
    object Dialogs : HomeScreenItems()
    object TabLayout : HomeScreenItems()
    object Pager : HomeScreenItems()
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
    object RenderScript : HomeScreenItems()

    val name: String
        get() = when(this) {
            Dialogs -> "Dialogs"
            TabLayout -> "TabLayout"
            Pager -> "Pager"
            RenderScript -> "RenderScript"
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

    val path : String
        get() = when(this){
            Dialogs -> PATH.HOME_DIALOGS
            TabLayout -> PATH.HOME_TAB_LAYOUT
            Pager -> PATH.HOME_PAGER
            is ListView -> PATH.HOME_LIST.replace("{type}", type)
            ConstrainsLayout -> PATH.HOME_CONSTRAINS_LAYOUT
            MotionLayout -> PATH.HOME_MOTION_LAYOUT
            CollapsingAppBar -> PATH.HOME_COLLAPSING_APPBAR
            BottomAppBar -> PATH.HOME_BOTTOM_APPBAR
            BottomSheets -> PATH.HOME_BOTTOM_SHEETS
            Layouts -> PATH.HOME_LAYOUTS
            Modifiers -> PATH.HOME_MODIFIER
            AndroidView -> PATH.HOME_ANDROID_VIEW
            AdvanceLists -> PATH.HOME_ADVANCE_LISTS
            PullRefresh -> PATH.HOME_PULL_REFRESH_DEMOS
            CustomFling -> PATH.HOME_CUSTOM_FLING
            RenderScript -> PATH.HOME_RENDER_SCRIPT
        }
}