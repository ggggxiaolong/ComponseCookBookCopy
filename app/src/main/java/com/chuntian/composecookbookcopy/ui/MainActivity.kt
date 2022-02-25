package com.chuntian.composecookbookcopy.ui

import FaIcons
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.chuntian.composecookbookcopy.R
import com.chuntian.composecookbookcopy.theme.AppThemeState
import com.chuntian.composecookbookcopy.ui.animation.AnimationScreen
import com.chuntian.composecookbookcopy.ui.home.HomeScreen
import com.chuntian.composecookbookcopy.ui.home.root.PalletMenu
import com.chuntian.composecookbookcopy.ui.widgets.WidgetsScreen
import com.chuntian.composecookbookcopy.utils.IOScope
import com.chuntian.composecookbookcopy.utils.LocalThemeState
import com.chuntian.composecookbookcopy.utils.RotateIcon
import com.chuntian.composecookbookcopy.utils.TestTags
import com.chuntian.data.db.DB
import com.chuntian.theme.ColorPallet
import com.chuntian.theme.ComposeCookBookCopyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIconType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        IOScope().launch {
            val appThemeState = AppThemeState.fromTheme(DB.instance().themeDao().getTheme())
            withContext(Dispatchers.Main) {
                setContent {
                    val appTheme = remember { mutableStateOf(appThemeState) }
                    CompositionLocalProvider(LocalThemeState provides appTheme.value) {
                        BaseView(appThemeState = appTheme.value) {
                            MainAppContent(appThemeState = appTheme)
                        }
                    }
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
    modifier: Modifier,
    homeScreen: BottomNavType,
    appThemeState: MutableState<AppThemeState>,
    homeNavigateState: MutableState<Boolean>,
) {
    Column(modifier = modifier) {
        Crossfade(homeScreen) { screen ->
            Surface(color = MaterialTheme.colorScheme.background) {
                when (screen) {
                    BottomNavType.HOME -> HomeScreen(
                        appThemeState = appThemeState,
                        homeNavigateState = homeNavigateState
                    )
                    BottomNavType.WIDGETS -> WidgetsScreen()
                    BottomNavType.ANIMATION -> AnimationScreen()
                    BottomNavType.DEMO_UI -> HomeScreen(
                        appThemeState = appThemeState,
                        homeNavigateState = homeNavigateState
                    )
                    BottomNavType.TEMPLATE -> HomeScreen(
                        appThemeState = appThemeState,
                        homeNavigateState = homeNavigateState
                    )
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
    val homeNavigateState = remember { mutableStateOf(false) }
    val animateNavigateState =
        animateDpAsState(targetValue = if (homeNavigateState.value) 0.dp else 80.dp, tween(1000))
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
            val newData = appThemeState.value.copy(pallet = newPalletSelected)
            appThemeState.value = newData
            coroutineScope.launch {
                chooseColorBottomModalState.hide()
                withContext(Dispatchers.IO) {
                    DB.instance().themeDao().save(newData.toTheme())
                }
            }
        }
    }, sheetState = chooseColorBottomModalState) {
        val orientation = LocalConfiguration.current.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Column {
                HomeScreenContent(
                    homeScreen = homeScreenState.value,
                    appThemeState = appThemeState,
                    modifier = Modifier.weight(1f),
                    homeNavigateState = homeNavigateState,
                )
                BottomNavigationContent(
                    items = list,
                    homeScreenState = homeScreenState,
                    modifier = Modifier
                        .semantics { contentDescription = bottomNavBarContentDescription }
                        .height(animateNavigateState.value)
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
                        .width(animateNavigateState.value)
                        .testTag(TestTags.BOTTOM_NAV_TEST_TAG),
                    homeScreenState = homeScreenState
                )
                HomeScreenContent(
                    homeScreen = homeScreenState.value,
                    appThemeState = appThemeState,
                    modifier = Modifier.weight(1f),
                    homeNavigateState = homeNavigateState,
                )
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
    systemUiController.setSystemBarsColor(MaterialTheme.colorScheme.surface)
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