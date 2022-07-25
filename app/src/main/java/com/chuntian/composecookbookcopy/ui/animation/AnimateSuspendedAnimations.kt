package com.chuntian.composecookbookcopy.ui.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.utils.Subtitle
import com.chuntian.composecookbookcopy.utils.TitleText
import kotlin.math.roundToInt

@Composable
fun AnimateSuspendedAnimations() {
    TitleText(title = "Suspending Animations via Animate(floatValue) with Bounds")
    Subtitle(subtitle = "Use it with PointerInput or LaunchedEffects")
    SimpleDismissAnimateView()
    Divider()
    PointerInputAnimate()
    Divider()
    DraggableCardAnimate()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDismissAnimateView() {
    var enable by remember { mutableStateOf(true) }
    val animate = remember { Animatable(0f) }
    animate.updateBounds(-100f, 100f)
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary) ,
        modifier = Modifier
            .size(100.dp)
            .offset(x = animate.value.dp)
            .clickable { enable = !enable }
    ) {
        LaunchedEffect(key1 = enable) {
            animate.animateTo(if (enable) 100f else -100f)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PointerInputAnimate() {
    Subtitle(subtitle = "Drag using Pointer input with Animate in coroutines")
    var offsetX by remember { mutableStateOf(0f) }
    val modifier = Modifier
        .size(100.dp)
        .offset { IntOffset(offsetX.roundToInt(), 0) }
        .draggable(
            orientation = Orientation.Horizontal,
            state = rememberDraggableState { delta -> offsetX += delta }
        )
    Card(modifier = modifier) {}
}

@Composable
fun DraggableCardAnimate() {
    Subtitle(subtitle = "Drag using Pointer input with Animate in coroutines")
    val startX =
        with(LocalDensity.current) { ((LocalConfiguration.current.screenWidthDp.dp - 100.dp) / 2).toPx() }
    val startY = with(LocalDensity.current) { 100.dp.toPx() }
    var offsetX by remember { mutableStateOf(startX) }
    var offsetY by remember { mutableStateOf(startY) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .size(100.dp)
                .pointerInput(Unit) {
                    detectDragGestures(onDragEnd = {
                        offsetX = startX
                        offsetY = startY
                    }) { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
        )
    }
}