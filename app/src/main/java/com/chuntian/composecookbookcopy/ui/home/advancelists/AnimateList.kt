package com.chuntian.composecookbookcopy.ui.home.advancelists

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.chuntian.data.DemoDataProvider
import com.chuntian.data.model.Tweet
import com.chuntian.demo.youtube.components.YoutubeChip

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Preview
@Composable
fun AnimateListView() {
    val animations = listOf("Fade", "Scale", "Slide", "Fade+Slide", "Slide up", "RotateX")
    val tweets = DemoDataProvider.tweetList
    Column {
        var animationIndex by remember { mutableStateOf(0) }
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            items(6) {
                YoutubeChip(
                    selected = it == animationIndex,
                    text = animations[it],
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { animationIndex = it })
            }
        }
        LazyColumn{
            itemsIndexed(items = tweets){
                index, item -> AnimatedListItem(item = item, itemIndex = index, animationIndex = animationIndex)
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun AnimatedListItem(item: Tweet, itemIndex: Int, animationIndex: Int) {
    val painter = rememberImagePainter(data = "https://picsum.photos/id/${itemIndex + 1}/200/200")
    val animateModifier = when (animationIndex) {
        0 -> {//Fade
            val animatedProgress = remember { Animatable(initialValue = 0f) }
            LaunchedEffect(Unit) {
                animatedProgress.animateTo(targetValue = 1f, animationSpec = tween(600))
            }
            Modifier
                .padding(8.dp)
                .alpha(animatedProgress.value)
        }
        1 -> {//Scale
            val animatedProgress = remember { Animatable(0.8f) }
            LaunchedEffect(key1 = Unit) {
                animatedProgress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
            Modifier
                .padding(8.dp)
                .graphicsLayer(scaleY = animatedProgress.value, scaleX = animatedProgress.value)
        }
        2 -> {//Slide
            val animatedProgress = remember { Animatable(initialValue = 300f) }
            LaunchedEffect(Unit) {
                animatedProgress.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(300, easing = FastOutSlowInEasing)
                )
            }
            Modifier
                .padding(8.dp)
                .graphicsLayer(translationX = animatedProgress.value)
        }
        3 -> {//Fade+Slide
            val animatedProgress = remember { Animatable(initialValue = -300f) }
            val opacityProgress = remember { Animatable(initialValue = 0f) }
            LaunchedEffect(key1 = Unit) {
                animatedProgress.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(300, easing = LinearEasing)
                )
                opacityProgress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(600)
                )
            }
            Modifier
                .padding(8.dp)
                .graphicsLayer(translationX = animatedProgress.value)
                .alpha(opacityProgress.value)
        }
        4 -> {//Slide up
            val animatedProgress = remember { Animatable(initialValue = 300f) }
            LaunchedEffect(key1 = Unit) {
                animatedProgress.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
            Modifier
                .padding(8.dp)
                .graphicsLayer(translationY = animatedProgress.value)
        }
        5 -> {//RotateX
            val animatedProgress = remember { Animatable(0f) }
            LaunchedEffect(key1 = Unit) {
                animatedProgress.animateTo(
                    targetValue = 360f,
                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                )
            }
            Modifier
                .padding(8.dp)
                .graphicsLayer(rotationX = animatedProgress.value)
        }
        else -> {
            val animatedProgress = remember { Animatable(0.8f) }
            LaunchedEffect(key1 = Unit) {
                animatedProgress.animateTo(targetValue = 1f, animationSpec = tween(300))
            }
            Modifier
                .padding(8.dp)
                .alpha(animatedProgress.value)
        }
    }
    Row(modifier = animateModifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .size(55.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .weight(1f)
        ) {
            Text(
                text = item.author,
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = item.text,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
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