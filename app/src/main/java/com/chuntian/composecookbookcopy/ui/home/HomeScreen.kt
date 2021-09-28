package com.chuntian.composecookbookcopy.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chuntian.composecookbookcopy.theme.AppThemeState
import com.chuntian.composecookbookcopy.ui.home.dialogs.DialogScreen
import com.chuntian.composecookbookcopy.ui.home.root.HomeRoot
import com.chuntian.composecookbookcopy.utils.LocalNavControl

@ExperimentalFoundationApi
@Composable
fun HomeScreen(appThemeState: MutableState<AppThemeState>) {
    val controller = rememberNavController()
    CompositionLocalProvider(LocalNavControl provides controller){
        NavHost(navController = controller, startDestination = PATH_HOME){
            composable(PATH_HOME){ HomeRoot(appThemeState = appThemeState)}
            composable(PATH_DIALOGS){ DialogScreen {
                controller.popBackStack()
            }}
        }
    }

}

const val PATH_HOME = "/home"
const val PATH_DIALOGS = "/home/dialog"