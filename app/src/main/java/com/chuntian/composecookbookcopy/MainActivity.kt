package com.chuntian.composecookbookcopy

import FaIcons
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.chuntian.composecookbookcopy.theme.AppThemeState
import com.chuntian.composecookbookcopy.ui.home.HomeScreen
import com.chuntian.composecookbookcopy.ui.home.root.PalletMenu
import com.chuntian.composecookbookcopy.utils.LocalThemeState
import com.chuntian.composecookbookcopy.utils.RotateIcon
import com.chuntian.composecookbookcopy.utils.TestTags
import com.chuntian.theme.ColorPallet
import com.chuntian.theme.ComposeCookBookCopyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIconType
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @OptIn(
        ExperimentalMaterial3Api::class,
        ExperimentalAnimationApi::class,
        ExperimentalPagerApi::class,
        ExperimentalComposeUiApi::class,
        ExperimentalFoundationApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appTheme = remember { mutableStateOf(AppThemeState()) }
            CompositionLocalProvider(LocalThemeState provides appTheme.value) {
                BaseView(appThemeState = appTheme.value) {
                    MainAppContent(appThemeState = appTheme)
                }
            }
        }
    }
}

class NavigationItemData(
    val type: BottomNavType,
    val iconType: FaIconType,
    @StringRes val label: Int,
    val tag: String,
    val animate: Boolean,
)

@Composable
fun BottomNavigationContent(
    items: List<NavigationItemData>,
    modifier: Modifier = Modifier,
    homeScreenState: MutableState<BottomNavType>
) {
    var animate by remember { mutableStateOf(false) }
    NavigationBar(modifier = modifier) {
        for (item in items) {
            NavigationBarItem(
                selected = homeScreenState.value == item.type,
                onClick = {
                    homeScreenState.value = item.type
                    animate = item.animate
                },
                icon = {
                    if (item.type != BottomNavType.ANIMATION) {
                        FaIcon(
                            faIcon = item.iconType,
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                        )
                    } else {
                        RotateIcon(
                            state = animate,
                            asset = Icons.Default.PlayArrow,
                            angle = 720f,
                            duration = 2000
                        )
                    }

                },
                label = { Text(text = stringResource(id = item.label)) },
                modifier = Modifier.testTag(item.tag)
            )
        }
    }
}

@Composable
private fun NavigationRailContent(
    items: List<NavigationItemData>,
    modifier: Modifier,
    homeScreenState: MutableState<BottomNavType>
) {
    var animate by remember { mutableStateOf(false) }
    NavigationRail(modifier = modifier) {
        for (item in items) {
            NavigationRailItem(
                selected = homeScreenState.value == item.type,
                onClick = { homeScreenState.value = item.type; animate = item.animate },
                label = {
                    Text(
                        text = stringResource(id = item.label),
                        style = TextStyle(fontSize = 12.sp)
                    )
                },
                icon = {
                    if (item.type == BottomNavType.ANIMATION) {
                        RotateIcon(
                            state = animate,
                            asset = Icons.Default.PlayArrow,
                            angle = 720f,
                            duration = 2000
                        )
                    } else {
                        FaIcon(
                            faIcon = item.iconType,
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                        )
                    }
                },
                modifier = Modifier.testTag(TestTags.BOTTOM_NAV_HOME_TEST_TAG)
            )
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun HomeScreenContent(
    homeScreen: BottomNavType,
    appThemeState: MutableState<AppThemeState>,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Crossfade(homeScreen) { screen ->
            Surface(color = MaterialTheme.colorScheme.background) {
                when (screen) {
                    BottomNavType.HOME -> HomeScreen(appThemeState = appThemeState)
                    BottomNavType.WIDGETS -> HomeScreen(appThemeState = appThemeState)
                    BottomNavType.ANIMATION -> HomeScreen(appThemeState = appThemeState)
                    BottomNavType.DEMO_UI -> HomeScreen(appThemeState = appThemeState)
                    BottomNavType.TEMPLATE -> HomeScreen(appThemeState = appThemeState)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun MainAppContent(appThemeState: MutableState<AppThemeState>) {
    val homeScreenState = remember { mutableStateOf(BottomNavType.HOME) }
    val bottomNavBarContentDescription = stringResource(id = R.string.a11y_bottom_navigation_bar)
    val chooseColorBottomModalState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val list = listOf(
        NavigationItemData(
            BottomNavType.HOME,
            FaIcons.Home,
            R.string.navigation_item_home,
            TestTags.BOTTOM_NAV_HOME_TEST_TAG,
            false
        ),
        NavigationItemData(
            BottomNavType.WIDGETS,
            FaIcons.Tools,
            R.string.navigation_item_widgets,
            TestTags.BOTTOM_NAV_WIDGETS_TEST_TAG,
            false
        ),
        NavigationItemData(
            BottomNavType.ANIMATION,
            FaIcons.Play,
            R.string.navigation_item_animation,
            TestTags.BOTTOM_NAV_ANIM_TEST_TAG,
            true
        ),
        NavigationItemData(
            BottomNavType.DEMO_UI,
            FaIcons.LaptopCode,
            R.string.navigation_item_demoui,
            TestTags.BOTTOM_NAV_DEMO_UI_TEST_TAG,
            false
        ),
        NavigationItemData(
            BottomNavType.TEMPLATE,
            FaIcons.LayerGroup,
            R.string.navigation_item_profile,
            TestTags.BOTTOM_NAV_TEMPLATE_TEST_TAG,
            false
        ),
    )
    ModalBottomSheetLayout(sheetContent = {
        PalletMenu(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            showMenu = false
        ) { newPalletSelected ->
            appThemeState.value = appThemeState.value.copy(pallet = newPalletSelected)
            coroutineScope.launch {
                chooseColorBottomModalState.hide()
            }
        }
    }, sheetState = chooseColorBottomModalState) {
        val orientation = LocalConfiguration.current.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Column {
                HomeScreenContent(homeScreenState.value, appThemeState, Modifier.weight(1f))
                BottomNavigationContent(
                    items = list,
                    homeScreenState = homeScreenState,
                    modifier = Modifier
                        .semantics { contentDescription = bottomNavBarContentDescription }
                        .testTag(TestTags.BOTTOM_NAV_TEST_TAG)
                )
            }
        } else {
            Row(modifier = Modifier.fillMaxSize()) {
                NavigationRailContent(
                    items = list,
                    modifier = Modifier
                        .semantics {
                            contentDescription = bottomNavBarContentDescription
                        }
                        .testTag(TestTags.BOTTOM_NAV_TEST_TAG),
                    homeScreenState = homeScreenState
                )
                HomeScreenContent(homeScreenState.value, appThemeState, Modifier.weight(1f))
            }
        }
    }

}

@Composable
fun BaseView(
    appThemeState: AppThemeState,
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(if (appThemeState.darkTheme) Color.Black else Color.White)
    ComposeCookBookCopyTheme(
        darkTheme = appThemeState.darkTheme,
        colorPallet = appThemeState.pallet
    ) {
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val appThemeState = remember { mutableStateOf(AppThemeState(false, ColorPallet.GREEN)) }
    BaseView(appThemeState.value) {
        MainAppContent(appThemeState)
    }
}