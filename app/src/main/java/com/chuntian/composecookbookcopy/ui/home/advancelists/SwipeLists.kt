package com.chuntian.composecookbookcopy.ui.home.advancelists

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chuntian.data.AlbumsDataProvider
import com.chuntian.data.model.Album
import com.chuntian.theme.Green500
import kotlin.math.roundToInt

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Preview
@Composable
fun SwipeListView() {
    val albums = AlbumsDataProvider.albums
    LazyColumn {
        itemsIndexed(albums) { index, item ->
            SwiperListItem(index, item) {}
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun SwiperListItem(index: Int, album: Album, onItemSwiped: (Int) -> Unit) {
    val visible = remember(album.id) { mutableStateOf(true) }
    AnimatedVisibility(visible = visible.value) {
        Box(modifier = Modifier.background(Green500)) {
            BackgroundListItem(modifier = Modifier.align(Alignment.CenterEnd))
            ForegroundListItem(album, index) {
                visible.value = false
                onItemSwiped.invoke(index)
            }
        }
    }
}

@Composable
fun BackgroundListItem(modifier: Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.End) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary
            )
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.AccountBox,
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}

enum class SwipeState {
    SWIPED, VISIBLE, MIDDLE
}

@ExperimentalMaterialApi
@Composable
fun ForegroundListItem(album: Album, index: Int, onItemSwiped: (Int) -> Unit) {
    val swipeState = rememberSwipeableState(
        initialValue = SwipeState.VISIBLE,
        confirmStateChange = {
            if (it == SwipeState.SWIPED) {
                onItemSwiped.invoke(index)
            }
            true
        })
    val swipeAnchors =
        mapOf(0f to SwipeState.VISIBLE, -1000f to SwipeState.SWIPED, -500f to SwipeState.MIDDLE)
    Row(
        modifier = Modifier
            .swipeable(
                state = swipeState,
                anchors = swipeAnchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal,
            )
            .offset { IntOffset(swipeState.offset.value.roundToInt(), 0) }
            .background(MaterialTheme.colors.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = album.imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(55.dp)
                .padding(4.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .weight(1f)
        ) {
            Text(
                text = album.song,
                style = MaterialTheme.typography.h6.copy(fontSize = 16.sp),
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = "${album.artist}, ${album.descriptions}",
                style = MaterialTheme.typography.subtitle2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (album.id % 3 == 0) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = MaterialTheme.colors.primaryVariant,
                modifier = Modifier
                    .padding(4.dp)
                    .size(20.dp)
            )
        }
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.padding(4.dp)
        )
    }
}