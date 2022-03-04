package com.chuntian.composecookbookcopy.ui.templates

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chuntian.composecookbookcopy.ui.templates.charts.ChartsScreen
import com.chuntian.composecookbookcopy.ui.templates.login.LoginScreen
import com.chuntian.composecookbookcopy.ui.templates.onBoarding.OnBoardingScreen
import com.chuntian.composecookbookcopy.ui.templates.payCard.PayCardScreen
import com.chuntian.composecookbookcopy.ui.templates.profile.ProfileScree
import com.chuntian.composecookbookcopy.ui.templates.root.TemplateRootScreen
import com.chuntian.composecookbookcopy.utils.CodingScreen
import com.chuntian.composecookbookcopy.utils.LocalNavControl
import com.chuntian.data.PATH

@Composable
fun TemplateScreen(homeNavigateState: MutableState<Boolean>) {
    val controller = rememberNavController()
    val onBack: () -> Unit = { controller.popBackStack() }
    controller.addOnDestinationChangedListener { _, destination, _ ->
        homeNavigateState.value = destination.route != PATH.TEMPLATE
    }
    CompositionLocalProvider(LocalNavControl provides controller) {
        NavHost(navController = controller, startDestination = PATH.TEMPLATE) {
            composable(PATH.TEMPLATE) { TemplateRootScreen() }
            composable(PATH.TEMPLATE_EMPTY_SCREEN) { CodingScreen(onBack) }
            composable(PATH.TEMPLATE_LOGIN) { LoginScreen() }
            composable(PATH.TEMPLATE_PROFILES) { ProfileScree() }
            composable(PATH.TEMPLATE_ON_BOARDING) { OnBoardingScreen() }
            composable(PATH.TEMPLATE_CHARTS) { ChartsScreen() }
            composable(PATH.TEMPLATE_PAY_CARD) { PayCardScreen() }
        }
    }
}