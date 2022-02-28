package com.chuntian.composecookbookcopy.ui.templates.root

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
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
import com.chuntian.data.model.TemplateScreenItems

@Composable
fun TemplateRootScreen() {
    val items = DemoDataProvider.templateScreenListItems
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val isWiderScreen = screenWidth > 550
    if (isWiderScreen) {
        HorizontalScreenView(items)
    } else {
        VerticalScreenView(items = items)
    }

}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HorizontalScreenView(items: List<TemplateScreenItems>) {
    val control = LocalNavControl.current
    val cardElevation = CardDefaults.cardElevation()
    LazyVerticalGrid(cells = GridCells.Adaptive(150.dp)) {
        items(items = items, itemContent = { item ->
            Surface(
                modifier = Modifier
                    .clickable { templateRootScreenItemClicked(item, control) }
                    .padding(8.dp)
                    .height(150.dp),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primary,
                contentColor = contentColorFor(MaterialTheme.colorScheme.primary),
                tonalElevation = cardElevation.tonalElevation(interactionSource = null).value,
                shadowElevation = cardElevation.shadowElevation(interactionSource = null).value
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
fun VerticalScreenView(items: List<TemplateScreenItems>) {
    val control = LocalNavControl.current
    LazyColumn {
        items(items = items, itemContent = { item ->
            Button(
                onClick = { templateRootScreenItemClicked(item, control) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Text(text = item.value)
            }
        })
    }
}

fun templateRootScreenItemClicked(homeScreenItems: TemplateScreenItems, controller: NavController) {
    try {
        controller.navigate(homeScreenItems.path)
    } catch (e: IllegalArgumentException) {
        controller.navigate(PATH.TEMPLATE_EMPTY_SCREEN)
    }
}