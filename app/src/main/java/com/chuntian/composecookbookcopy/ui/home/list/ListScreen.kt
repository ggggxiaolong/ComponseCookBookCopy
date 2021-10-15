package com.chuntian.composecookbookcopy.ui.home.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chuntian.data.DemoDataProvider
import timber.log.Timber


@ExperimentalFoundationApi
@Composable
fun ListScreen(type: String, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(modifier = Modifier.padding(4.dp)) {
                        Text(text = "List")
                        Text(text = type.lowercase(), style = MaterialTheme.typography.body2)
                    }
                },
                elevation = 8.dp,
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                })
        },
        content = {
            Timber.i(type)
            when (type) {
                ListViewType.VERTICAL.name -> {
                    VerticalListView()
                }
                ListViewType.HORIZONTAL.name -> {
                    HorizontalListView()
                }
                ListViewType.GRID.name -> {
                    GridListItemView()
                }
                ListViewType.MIX.name -> {
                }
            }
        }
    )
}

@Composable
private fun ListItemDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@Composable
fun VerticalListView() {
    val items = DemoDataProvider.itemList
    Timber.i("VerticalListView")
    LazyColumn {
        items(
            count = items.size,
            itemContent = { index ->
                if (index % 3 == 0) {
                    VerticalListItemSmall(item = items[index])
                } else {
                    VerticalListItem(item = items[index])
                }
                ListItemDivider()
            }
        )
    }
}

@Composable
fun HorizontalListView() {
    val list = DemoDataProvider.itemList
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = "Good Food",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.subtitle1
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(list) {
                HorizontalListItem(item = it)
            }
        }
    }

}

@ExperimentalFoundationApi
@Composable
fun GridListItemView() {
    val list = DemoDataProvider.itemList
    LazyVerticalGrid(cells = GridCells.Fixed(count = 2), contentPadding = PaddingValues(start = 4.dp, end = 4.dp)) {
        items(list) {
            GridListItem(item = it)
        }
    }
}

