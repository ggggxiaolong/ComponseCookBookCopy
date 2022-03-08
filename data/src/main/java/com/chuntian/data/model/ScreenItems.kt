package com.chuntian.data.model

import com.chuntian.data.PATH

enum class TemplateScreenItems(val value: String, val path: String) {
    Login("Login", PATH.TEMPLATE_LOGIN),
    Profiles("Profiles", PATH.TEMPLATE_PROFILES),
    OnBoarding("OnBoarding", PATH.TEMPLATE_ON_BOARDING),
    Charts("Charts", PATH.TEMPLATE_CHARTS),
    PayCard("Adding Payment Card", PATH.TEMPLATE_PAY_CARD),
    DrawTiger("Draw tiger", PATH.TEMPLATE_DRAW_TIGER),
    EmptyScreen("EmptyScreen", PATH.TEMPLATE_EMPTY_SCREEN),
    Setting("Setting", PATH.TEMPLATE_SETTING),
    Loaders("Loaders", PATH.TEMPLATE_LOADERS),
    Canvas("Canvas Drawing", PATH.TEMPLATE_CANVAS),
    Animations("Animations", PATH.TEMPLATE_ANIMATIONS),
    Timer("Timer", PATH.TEMPLATE_TIMER),
    ClockView("ClockView", PATH.TEMPLATE_CLOCK_VIEW),
    Menu("Cascade Menu", PATH.TEMPLATE_MENU),
}

enum class HomeScreenItems(val value: String, val path: String) {
    Dialogs("Dialogs", PATH.HOME_DIALOGS),
    TabLayout("TabLayout", PATH.HOME_TAB_LAYOUT),
    Pager("Pager", PATH.HOME_PAGER),
    Layouts("Layouts", PATH.HOME_LAYOUTS),
    VerticalListView("Vertical ListView", PATH.HOME_VERTICAL_LIST),
    HorizontalListView("Horizontal ListView", PATH.HOME_HORIZONTAL_LIST),
    GridListView("Grid ListView", PATH.HOME_GRID_LIST),
    AdvanceLists("Advance List", PATH.HOME_ADVANCE_LISTS),
    ConstrainsLayout("Constraint Layout", PATH.HOME_CONSTRAINS_LAYOUT),
    CollapsingAppBar("Collapsing AppBar", PATH.HOME_COLLAPSING_APPBAR),
    BottomAppBar("BottomAppBar", PATH.HOME_BOTTOM_APPBAR),
    BottomSheets("BottomSheets", PATH.HOME_BOTTOM_SHEETS),
    Modifiers("Modifiers", PATH.HOME_MODIFIER),
    AndroidView("Compose X Android views", PATH.HOME_ANDROID_VIEW),
    PullRefresh("Pull refresh demos", PATH.HOME_PULL_REFRESH_DEMOS),
    CustomFling("Custom Fling", PATH.HOME_CUSTOM_FLING),
    MotionLayout("Motion Layout", PATH.HOME_MOTION_LAYOUT),
    RenderScript("RenderScript", PATH.HOME_RENDER_SCRIPT),
}

enum class DemoItems(val value: String, val path: String) {
    Ins("Instagram", PATH.DEMO_INS),
    Twitter("Twitter", PATH.DEMO_TWITTER),
    Gmail("Gmail", PATH.DEMO_GMAIL),
    Youtube("Youtube", PATH.DEMO_YOUTUBE),
    Spotify("Spotify", PATH.DEMO_SPOTIFY),
    Crypto("CryptoApp+Mvvm", PATH.DEMO_CRYPTO),
    Movies("MoviesApp+Mvvm", PATH.DEMO_MOVIES),
    Dating("DatingApp", PATH.DEMO_DATING),
    Tiktok("Tiktok", PATH.DEMO_TIKTOK),
    Paint("Paint", PATH.DEMO_PAINT),
}