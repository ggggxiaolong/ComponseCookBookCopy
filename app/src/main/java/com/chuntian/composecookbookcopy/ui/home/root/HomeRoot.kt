package com.chuntian.composecookbookcopy.ui.home.root

import android.os.Build
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chuntian.composecookbookcopy.theme.AppThemeState
import com.chuntian.composecookbookcopy.utils.IOScope
import com.chuntian.composecookbookcopy.utils.LocalNavControl
import com.chuntian.composecookbookcopy.utils.TestTags
import com.chuntian.data.DemoDataProvider
import com.chuntian.data.PATH
import com.chuntian.data.db.DB
import com.chuntian.data.model.HomeScreenItems
import com.chuntian.theme.*
import com.chuntian.theme.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun HomeRoot(appThemeState: MutableState<AppThemeState>) {
    val showMenu = remember { mutableStateOf(false) }
    Scaffold(modifier = Modifier.testTag(TestTags.HOME_SCREEN_ROOT),
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "Compose Cookbook") },
                actions = {
                    IconButton(
                        onClick = {
                            val newData =
                                appThemeState.value.copy(darkTheme = !appThemeState.value.darkTheme)
                            appThemeState.value = newData
                            IOScope().launch {
                                DB.instance().themeDao().save(newData.toTheme())
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sleep),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { showMenu.value = !showMenu.value }) {
                        Icon(imageVector = Icons.Default.Palette, contentDescription = null)
                    }
                })
        },
        content = {
            HomeRootScreenContent(
                showMenu = showMenu,
                onPalletChange = { newPalletSelected ->
                    val newData = appThemeState.value.copy(pallet = newPalletSelected)
                    appThemeState.value = newData
                    showMenu.value = false
                    IOScope().launch {
                        DB.instance().themeDao().save(newData.toTheme())
                    }
                }
            )
        }
    )
}


fun homeRootItemClicked(homeScreenItems: HomeScreenItems, controller: NavController) {
    try {
        controller.navigate(homeScreenItems.path)
    } catch (e: IllegalArgumentException) {
        controller.navigate(PATH.CODING)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRootScreenListView(
    homeScreenItems: HomeScreenItems, isWiderScreen: Boolean
) {
    val controller = LocalNavControl.current
    if (isWiderScreen) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .clickable { homeRootItemClicked(homeScreenItems, controller) }
                .height(150.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Text(
                text = homeScreenItems.name,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    } else {
        Button(
            onClick = { homeRootItemClicked(homeScreenItems, controller) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .testTag("button-${homeScreenItems.name}")
        ) {
            Text(
                text = homeScreenItems.name,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun MenuItem(color: Color, name: String, onPalletChange: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onPalletChange), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Filled.FiberManualRecord, tint = color, contentDescription = null)
        Text(text = name, modifier = Modifier.padding(8.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PalletMenu(
    modifier: Modifier,
    showMenu: Boolean,
    onPalletChange: (ColorPallet) -> Unit
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .animateContentSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            if (showMenu) {
                val list = listOf(
                    Triple(Green500, "Green", ColorPallet.GREEN),
                    Triple(Purple700, "Purple", ColorPallet.PURPLE),
                    Triple(Orange500, "Orange", ColorPallet.ORANGE),
                    Triple(Blue500, "Blue", ColorPallet.BLUE),
                )
                for (item in list) {
                    MenuItem(color = item.first, name = item.second) {
                        onPalletChange.invoke(item.third)
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    MenuItem(
                        color = dynamicLightColorScheme(LocalContext.current).primary,
                        name = "Dynamic"
                    ) {
                        onPalletChange.invoke(ColorPallet.WALLPAPER)
                    }
                }
            }
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun HomeRootScreenContent(
    showMenu: MutableState<Boolean>,
    onPalletChange: (ColorPallet) -> Unit,
) {
    val list = remember { DemoDataProvider.homeScreenListItems }
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val isWiderScreen = screenWidth > 550
    Box(modifier = Modifier.fillMaxSize()) {
        if (isWiderScreen) {
            LazyVerticalGrid(
                cells = GridCells.Adaptive(150.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(items = list, itemContent = {
                    HomeRootScreenListView(
                        homeScreenItems = it,
                        isWiderScreen = isWiderScreen
                    )
                })
            }
        } else {
            LazyColumn(modifier = Modifier.testTag(TestTags.HOME_SCREEN_LIST)) {
                items(items = list, itemContent = {
                    HomeRootScreenListView(
                        homeScreenItems = it,
                        isWiderScreen = isWiderScreen
                    )
                })
            }
        }
        PalletMenu(
            modifier = Modifier.align(Alignment.TopEnd),
            showMenu = showMenu.value,
            onPalletChange = onPalletChange
        )
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewHomeRootScreen() {
    val state = remember { mutableStateOf(AppThemeState(false, ColorPallet.PURPLE)) }
    HomeRoot(appThemeState = state)
}