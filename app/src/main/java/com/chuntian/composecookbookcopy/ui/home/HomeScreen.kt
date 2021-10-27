package com.chuntian.composecookbookcopy.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chuntian.composecookbookcopy.theme.AppThemeState
import com.chuntian.composecookbookcopy.ui.home.advancelists.AdvanceListScreen
import com.chuntian.composecookbookcopy.ui.home.constrainLayout.ConstrainLayoutScreen
import com.chuntian.composecookbookcopy.ui.home.dialogs.DialogScreen
import com.chuntian.composecookbookcopy.ui.home.layout.LayoutScreen
import com.chuntian.composecookbookcopy.ui.home.list.ListScreen
import com.chuntian.composecookbookcopy.ui.home.list.ListViewType
import com.chuntian.composecookbookcopy.ui.home.modifiers.ModifiersScreen
import com.chuntian.composecookbookcopy.ui.home.motionLayout.MotionLayoutScree
import com.chuntian.composecookbookcopy.ui.home.root.HomeRoot
import com.chuntian.composecookbookcopy.utils.CodingScreen
import com.chuntian.composecookbookcopy.utils.LocalNavControl
import com.chuntian.data.PATH
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(appThemeState: MutableState<AppThemeState>) {
    val controller = rememberNavController()
    val onBack: () -> Unit = { controller.popBackStack() }
    CompositionLocalProvider(LocalNavControl provides controller) {
        NavHost(navController = controller, startDestination = PATH.HOME) {
            composable(PATH.HOME) { HomeRoot(appThemeState = appThemeState) }
            composable(PATH.HOME_DIALOGS) {
                DialogScreen(onBack)
            }
            composable(PATH.HOME_LIST) {
                ListScreen(
                    type = it.arguments?.getString("type")?.uppercase()
                        ?: ListViewType.VERTICAL.name,
                    onBack = onBack
                )
            }
            composable(PATH.HOME_MODIFIER) {
                ModifiersScreen(onBack = onBack)
            }
            composable(PATH.HOME_LAYOUTS) {
                LayoutScreen(onBack = onBack)
            }
            composable(PATH.CODING) {
                CodingScreen(onBack)
            }
            composable(PATH.HOME_CONSTRAINS_LAYOUT){
                ConstrainLayoutScreen(onBack)
            }
            composable(PATH.HOME_MOTION_LAYOUT){
                MotionLayoutScree(onBack = onBack)
            }
            composable(PATH.HOME_ADVANCE_LISTS){
                AdvanceListScreen(onBack = onBack)
            }
        }
    }

}

@Composable
fun HomeScaffold(title: String, onBack: () -> Unit, content: @Composable (PaddingValues) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = title) }, elevation = 8.dp, navigationIcon = {
            IconButton(
                onClick = onBack
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        })
    }, content = content)
}