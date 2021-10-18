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
import com.chuntian.composecookbookcopy.ui.home.modifiers.ModifiersScreen
import com.chuntian.composecookbookcopy.ui.home.list.ListScreen
import com.chuntian.composecookbookcopy.ui.home.list.ListViewType
import com.chuntian.composecookbookcopy.ui.home.root.HomeRoot
import com.chuntian.composecookbookcopy.utils.LocalNavControl

@ExperimentalFoundationApi
@Composable
fun HomeScreen(appThemeState: MutableState<AppThemeState>) {
    val controller = rememberNavController()
    val onBack: () -> Unit = { controller.popBackStack() }
    CompositionLocalProvider(LocalNavControl provides controller) {
        NavHost(navController = controller, startDestination = PATH_HOME) {
            composable(PATH_HOME) { HomeRoot(appThemeState = appThemeState) }
            composable(PATH_DIALOGS) {
                DialogScreen(onBack)
            }
            composable(PATH_LIST) {
                ListScreen(
                    type = it.arguments?.getString("type")?.uppercase()
                        ?: ListViewType.VERTICAL.name,
                    onBack = onBack
                )
            }
            composable(PATH_MODIFIER) {
                ModifiersScreen(onBack = onBack)
            }
        }
    }

}

const val PATH_HOME = "/home"
const val PATH_DIALOGS = "/home/dialog"
const val PATH_LIST = "/home/list/{type}"
const val PATH_LIST_PREFIX = "/home/list/"
const val PATH_MODIFIER = "/home/modifiers"