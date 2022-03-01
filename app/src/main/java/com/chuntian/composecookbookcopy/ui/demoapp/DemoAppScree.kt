package com.chuntian.composecookbookcopy.ui.demoapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chuntian.composecookbookcopy.ui.demoapp.root.DemoAppRootScreen
import com.chuntian.composecookbookcopy.utils.CodingScreen
import com.chuntian.composecookbookcopy.utils.LocalNavControl
import com.chuntian.data.PATH

@Composable
fun DemoAppScree(homeNavigateState: MutableState<Boolean>) {
    val controller = rememberNavController()
    val onBack: () -> Unit = { controller.popBackStack() }
    controller.addOnDestinationChangedListener { _, destination, _ ->
        homeNavigateState.value = destination.route != PATH.DEMO
    }
    CompositionLocalProvider(LocalNavControl provides controller) {
        NavHost(navController = controller, startDestination = PATH.DEMO) {
            composable(PATH.DEMO) { DemoAppRootScreen() }
            composable(PATH.CODING) { CodingScreen(onBack) }
        }
    }
}