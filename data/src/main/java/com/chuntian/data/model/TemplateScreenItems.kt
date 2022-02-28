package com.chuntian.data.model

import com.chuntian.data.PATH

enum class TemplateScreenItems(val value: String, val path: String) {
    Login("Login", PATH.TEMPLATE_LOGIN),
    Profiles("Profiles", PATH.TEMPLATE_PROFILES),
    OnBoarding("OnBoarding", PATH.TEMPLATE_ON_BOARDING),
    Charts("Charts", PATH.TEMPLATE_CHARTS),
    PayCard("Adding Payment Card", PATH.TEMPLATE_PAY_CARD),
    Pin("Pin Lock/BioMetric", PATH.TEMPLATE_PIN),
    EmptyScreen("EmptyScreen", PATH.TEMPLATE_EMPTY_SCREEN),
    Setting("Setting", PATH.TEMPLATE_SETTING),
    Loaders("Loaders", PATH.TEMPLATE_LOADERS),
    Canvas("Canvas Drawing", PATH.TEMPLATE_CANVAS),
    Animations("Animations", PATH.TEMPLATE_ANIMATIONS),
    Timer("Timer", PATH.TEMPLATE_TIMER),
    ClockView("ClockView", PATH.TEMPLATE_CLOCK_VIEW),
    Menu("Cascade Menu", PATH.TEMPLATE_MENU),
}