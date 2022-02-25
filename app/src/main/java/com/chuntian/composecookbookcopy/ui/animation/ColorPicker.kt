package com.chuntian.composecookbookcopy.ui.animation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.utils.Subtitle
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun ColorPicker(onColorSelected: (Color) -> Unit) {
    Subtitle(subtitle = "Color picker with Draggable")
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenWithPx = with(LocalDensity.current) { screenWidth.toPx() }
    var activeColor by remember { mutableStateOf(Color.Red) }

    val max = screenWidth - 32.dp
    val min = 0.dp
    val (minPx, maxPx) = with(LocalDensity.current) { min.toPx() to max.toPx() }
    var dragOffset by remember { mutableStateOf(0f) }
    Box(modifier = Modifier.padding(16.dp)) {
        Spacer(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(brush = colorMapGradient(screenWithPx))
                .align(Alignment.Center)
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        dragOffset = offset.x
                        activeColor = getActiveColor(dragOffset, screenWithPx)
                        onColorSelected.invoke(activeColor)
                    }
                }
        )
        Icon(
            imageVector = Icons.Filled.FiberManualRecord,
            tint = activeColor,
            contentDescription = null,
            modifier = Modifier
                .offset { IntOffset(x = dragOffset.roundToInt(), y = 0) }
                .border(
                    border = BorderStroke(4.dp, MaterialTheme.colorScheme.onSurface),
                    shape = CircleShape
                )
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState(
                        onDelta = { delta ->
                            val newValue = dragOffset + delta
                            dragOffset = newValue.coerceIn(minPx, maxPx)
                            activeColor = getActiveColor(dragOffset, screenWithPx)
                        },
                    ),
                    onDragStopped = { onColorSelected.invoke(activeColor) }
                )
        )
    }
}

fun colorMapGradient(screenWithPx: Float): Brush {
    val colors = mutableListOf<Color>()
    for (i in 0..360 step 2) {
        val randomSaturation = 90 + Random.nextFloat() * 10
        val randomLightness = 50 + Random.nextFloat() * 10
        val hsv = android.graphics.Color.HSVToColor(
            floatArrayOf(
                i.toFloat(),
                randomSaturation,
                randomLightness
            )
        )
        colors.add(Color(hsv))
    }
    return Brush.horizontalGradient(colors = colors, startX = 0f, endX = screenWithPx)
}

fun getActiveColor(offset: Float, screenWithPx: Float): Color {
    val hue = (offset / screenWithPx) * 360f
    val randomSaturation = 90 + Random.nextFloat() * 10
    val randomLightness = 50 + Random.nextFloat() * 10
    return Color(android.graphics.Color.HSVToColor(
        floatArrayOf(
            hue,
            randomSaturation,
            randomLightness
        )
    ))
}
