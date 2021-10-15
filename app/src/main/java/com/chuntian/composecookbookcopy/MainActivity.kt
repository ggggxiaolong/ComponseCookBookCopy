package com.chuntian.composecookbookcopy

import FaIcons
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.chuntian.composecookbookcopy.theme.AppThemeState
import com.chuntian.composecookbookcopy.theme.SystemUIController
import com.chuntian.composecookbookcopy.ui.home.HomeScreen
import com.chuntian.composecookbookcopy.utils.LocalNavControl
import com.chuntian.composecookbookcopy.utils.LocalThemeState
import com.chuntian.composecookbookcopy.utils.RotateIcon
import com.chuntian.composecookbookcopy.utils.TestTags
import com.chuntian.theme.*
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIconType

class MainActivity : AppCompatActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUIController = remember { SystemUIController(window) }
            val appTheme = remember { mutableStateOf(AppThemeState()) }
            CompositionLocalProvider(LocalThemeState provides appTheme.value) {
                BaseView(appThemeState = appTheme.value, systemUIController = systemUIController) {
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
    modifier: Modifier = Modifier,
    homeScreenState: MutableState<BottomNavType>
) {
    var animate by remember { mutableStateOf(false) }
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
    BottomNavigation(modifier = modifier) {
        for (item in list) {
            BottomNavigationItem(
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

@ExperimentalFoundationApi
@Composable
fun HomeScreenContent(
    homeScreen: BottomNavType,
    appThemeState: MutableState<AppThemeState>,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Crossfade(homeScreen) { screen ->
            Surface(color = MaterialTheme.colors.background) {
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

@ExperimentalFoundationApi
@Composable
fun MainAppContent(appThemeState: MutableState<AppThemeState>) {
    val homeScreenState = remember { mutableStateOf(BottomNavType.HOME) }
    val bottomNavBarContentDescription = stringResource(id = R.string.a11y_bottom_navigation_bar)
    Column {
        HomeScreenContent(homeScreenState.value, appThemeState, Modifier.weight(1f))
        BottomNavigationContent(homeScreenState = homeScreenState,
            modifier = Modifier
                .semantics { contentDescription = bottomNavBarContentDescription }
                .testTag(TestTags.BOTTOM_NAV_TEST_TAG)
        )
    }
}

@Composable
fun BaseView(
    appThemeState: AppThemeState,
    systemUIController: SystemUIController?,
    content: @Composable () -> Unit
) {
    val color = when (appThemeState.pallet) {
        ColorPallet.PURPLE -> Purple700
        ColorPallet.BLUE -> Blue700
        ColorPallet.ORANGE -> Orange700
        ColorPallet.GREEN -> Green700
    }
    systemUIController?.setSystemBarsColor(color, appThemeState.darkTheme)
    ComposeCookBookCopyTheme(
        darkTheme = appThemeState.darkTheme,
        colorPallet = appThemeState.pallet
    ) {
        content()
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val appThemeState = remember { mutableStateOf(AppThemeState(false, ColorPallet.GREEN)) }
    BaseView(appThemeState.value, null) {
        MainAppContent(appThemeState)
    }
}