package com.chuntian.composecookbookcopy.ui.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.utils.Subtitle
import com.chuntian.composecookbookcopy.utils.TitleText
import com.chuntian.theme.*
import com.chuntian.theme.R

@Composable
fun VisibilityAnimate() {
    TitleText(title = "Using Visibility Api(Experimental)")
    AnimateVisibilityAnim()
    Divider()
    AnimateVisibilityDifferentChildAnimate()
    Divider()
    AnimateVisibilitySlideInOut()
    Divider()
    VisibilityAnimationShrink()
    Divider()
    AnimateContentSize()
}

@Composable
fun AnimateVisibilityAnim() {
    Subtitle(subtitle = "Animate view Visibility via AnimateVisibility")
    var expand by remember { mutableStateOf(true) }
    FloatingActionButton(
        onClick = { expand = !expand },
        backgroundColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier.padding(16.dp)
    ) {
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_twitter),
                contentDescription = null
            )
            AnimatedVisibility(
                visible = expand,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = "Tweet", modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun AnimateVisibilityDifferentChildAnimate() {
    Subtitle(subtitle = "AnimateVisibility() with different child Animations")
    val colors = listOf(Green500, Blue500, Purple)
    var expanded by remember { mutableStateOf(true) }
    IconButton(onClick = { expanded = !expanded }) {
        Icon(imageVector = Icons.Default.RemoveRedEye, contentDescription = "")
    }

    AnimatedVisibility(visible = expanded) {
        Column(horizontalAlignment = Alignment.Start) {
            colors.forEach { color ->
                Card(
//                    containerColor = color,
                    colors = CardDefaults.cardColors(containerColor = color),
                    modifier = Modifier
                        .size(80.dp)
                        .padding(8.dp)
                        .animateEnterExit(
                            enter = slideInHorizontally { it },
                            exit = ExitTransition.None
                        )
                ) {}
            }
        }
    }
}

@Composable
fun AnimateVisibilitySlideInOut() {
    Subtitle(subtitle = "Visibility animation with fade and slide as enter/exit")
    var visibility by remember { mutableStateOf(true) }
    Row(
        modifier = Modifier
            .padding(12.dp)
            .width(200.dp)
            .height(60.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { visibility = !visibility }) {
        AnimatedVisibility(
            visible = visibility,
            enter = slideIn(
                tween(easing = LinearOutSlowInEasing, durationMillis = 500),
                initialOffset = { IntOffset(0, 120) }),
            exit = slideOut(
                tween(durationMillis = 500, easing = FastOutSlowInEasing),
                targetOffset = { IntOffset(0, 120) })
        ) {
            Text(text = "Tap for sliding animation")
        }
    }
}

@Composable
fun VisibilityAnimationShrink() {
    Subtitle(subtitle = "Visibility animation with expand/shrink as enter/exit")
    var visibility by remember { mutableStateOf(true) }
    Row(
        modifier = Modifier
            .padding(12.dp)
            .width(200.dp)
            .height(60.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { visibility = !visibility }) {
        AnimatedVisibility(
            visible = visibility,
            modifier = Modifier.align(Alignment.CenterVertically),
            enter = expandIn(expandFrom = Alignment.Center) { it * 4 },
            exit = shrinkOut(shrinkTowards = Alignment.Center)
        ) {
            Button(
                onClick = { visibility = !visibility },
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(text = "Shrink/Expand")
            }
        }
    }
}

@Composable
fun AnimateContentSize() {
    Subtitle(subtitle = "Using Modifier.animateContentSize() to Animate content change")
    var count by remember { mutableStateOf(1) }
    Row(
        modifier = Modifier
            .animateContentSize()
            .clickable { if (count < 10) count += 3 else count = 1 }) {
        (0..count).forEach{ _ ->
            Icon(imageVector = Icons.Default.PlayCircleFilled, contentDescription = null)
        }
    }
}