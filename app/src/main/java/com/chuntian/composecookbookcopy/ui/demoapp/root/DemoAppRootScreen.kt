package com.chuntian.composecookbookcopy.ui.demoapp.root

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chuntian.composecookbookcopy.utils.LocalNavControl
import com.chuntian.data.DemoDataProvider
import com.chuntian.data.PATH
import com.chuntian.data.model.DemoItems

@Composable
fun DemoAppRootScreen() {
    val items = DemoDataProvider.demoScreenListItems
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val isWiderScreen = screenWidth > 550
    if (isWiderScreen) {
        HorizontalScreenView(items)
    } else {
        VerticalScreenView(items = items)
    }

}

@Composable
fun HorizontalScreenView(items: List<DemoItems>) {
    val control = LocalNavControl.current
    val cardElevation = CardDefaults.cardElevation()
    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp),modifier = Modifier.fillMaxSize()) {
        items(items = items, itemContent = { item ->
            Surface(
                modifier = Modifier
                    .clickable { demoAppRootScreenItemClicked(item, control) }
                    .padding(8.dp)
                    .height(150.dp),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primary,
                contentColor = contentColorFor(MaterialTheme.colorScheme.primary),
//                tonalElevation = cardElevation.tonalElevation(enabled = true, interactionSource = null).value,
//                shadowElevation = cardElevation.shadowElevation(enabled = true, interactionSource = null).value
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = item.value)
                }
            }
        })
    }
}

@Composable
fun VerticalScreenView(items: List<DemoItems>) {
    val control = LocalNavControl.current
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = items, itemContent = { item ->
            Button(
                onClick = { demoAppRootScreenItemClicked(item, control) },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = item.value)
            }
        })
    }
}

fun demoAppRootScreenItemClicked(homeScreenItems: DemoItems, controller: NavController) {
    try {
        controller.navigate(homeScreenItems.path)
    } catch (e: IllegalArgumentException) {
        controller.navigate(PATH.CODING)
    }
}