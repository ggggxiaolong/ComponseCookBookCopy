package com.chuntian.composecookbookcopy.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.utils.Subtitle
import com.chuntian.composecookbookcopy.utils.TitleText
import com.chuntian.data.R
import com.chuntian.theme.TwitterColor

@Composable
fun Appbars() {
    Text(
        text = "App Bars",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(8.dp)
    )
    TopAppbars()
    BottomAppbarView()
    NavigationBarView()
}


@Composable
fun TopAppbars() {
    Subtitle(subtitle = "Top App Bar")
    SmallTopAppBar(title = { Text(text = "Home") }, navigationIcon = {
        IconButton(onClick = { }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    })
    Spacer(modifier = Modifier.size(8.dp))
    TopAppBar(title = { Text(text = "Instagram") },
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        elevation = 8.dp,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = com.chuntian.theme.R.drawable.ic_instagram),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Send, contentDescription = null)
            }
        }
    )
    Spacer(modifier = Modifier.size(8.dp))
    TopAppBar(title = {
        Icon(
            painter = painterResource(id = com.chuntian.theme.R.drawable.ic_twitter),
            contentDescription = null,
            tint = TwitterColor,
            modifier = Modifier.fillMaxWidth()
        )
    },
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        elevation = 8.dp,
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.p6),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .size(32.dp)
                    .clip(CircleShape)
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = null)
            }
        }
    )
}

@Composable
fun BottomAppbarView() {
    Spacer(modifier = Modifier.size(16.dp))
    Subtitle(subtitle = "Bottom app bars: Note bottom app bar support FAB cutouts when use scaffolds see demoUI crypto app")

    BottomAppBar(cutoutShape = CircleShape) {
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.MoreHoriz, contentDescription = null)
        }
        TitleText(title = "Bottom App Bar")
    }
}

@Composable
fun NavigationBarView() {
    Spacer(modifier = Modifier.size(16.dp))
    Subtitle(subtitle = "Bottom Navigation Bars")
    val selectedIndex = remember { mutableStateOf(0) }
    val items = listOf(
        "Home" to Icons.Outlined.Home,
        "Search" to Icons.Outlined.Search,
        "Your Library" to Icons.Outlined.LibraryMusic
    )
    NavigationBar {
        items.forEachIndexed { index, pair ->
            NavigationBarItem(
                selected = selectedIndex.value == index,
                onClick = { selectedIndex.value = index },
                icon = { Icon(imageVector = pair.second, contentDescription = null) },
                label = { Text(text = pair.first) }
            )
        }
    }
    Spacer(modifier = Modifier.size(16.dp))
    val items2 = listOf(Icons.Outlined.ReadMore, Icons.Outlined.Search, Icons.Outlined.CleanHands)
    NavigationBar {
        items2.forEachIndexed { index, imageVector ->
            NavigationBarItem(selected = selectedIndex.value == index,
                onClick = { selectedIndex.value = index },
                icon = { Icon(imageVector = imageVector, contentDescription = null) })
        }
    }
}

@Preview
@Composable
fun PreviewAppbars() {
    Column {
        Appbars()
    }
}