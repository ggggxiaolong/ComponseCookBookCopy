package com.chuntian.composecookbookcopy.ui.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.utils.Subtitle
import com.chuntian.composecookbookcopy.utils.TitleText
import com.chuntian.theme.Green200
import com.chuntian.theme.Green500
import com.chuntian.theme.Orange
import com.chuntian.theme.Purple

@Composable
fun TransitionAnimate() {
    TitleText(title = "Multi-state Animations via Transition and States")
    MultiStateColorPositionAnimation()
    Divider()
    MultiStateInfiniteTransition()
    Divider()
    MultiStateAnimationCircleStrokeCanvas()
}

enum class AnimationScreenState {
    Start, Middle, End
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiStateColorPositionAnimation() {
    Subtitle(subtitle = "Multi State color and position transition")
    Spacer(modifier = Modifier.height(80.dp))
    var animationState by remember { mutableStateOf(AnimationScreenState.Start) }
    val colors = Triple(Green200, Purple, Orange)
    val transition = updateTransition(targetState = animationState, label = "transition")
    val animatedColor by transition.animateColor(
        transitionSpec = { tween(500) },
        label = "animatedColor"
    ) { state ->
        when (state) {
            AnimationScreenState.Start -> colors.first
            AnimationScreenState.Middle -> colors.second
            AnimationScreenState.End -> colors.third
        }
    }
    val position by transition.animateDp(
        transitionSpec = { tween(500) },
        label = "position"
    ) { state ->
        when (state) {
            AnimationScreenState.Start -> 0.dp
            AnimationScreenState.Middle -> 80.dp
            AnimationScreenState.End -> (-80).dp
        }
    }
    FloatingActionButton(
        containerColor = animatedColor,
        modifier = Modifier.offset(x = position, y = position),
        onClick = {
            animationState = when (animationState) {
                AnimationScreenState.Start -> AnimationScreenState.Middle
                AnimationScreenState.Middle -> AnimationScreenState.End
                AnimationScreenState.End -> AnimationScreenState.Start
            }
        }
    ) {
        Icon(imageVector = Icons.Default.PlayCircleFilled, contentDescription = null)
    }
    Spacer(modifier = Modifier.height(80.dp))
}

@Composable
fun MultiStateInfiniteTransition() {
    Subtitle(subtitle = "Multi State infinite transition")
    val colors = Green200 to Orange
    val transition = rememberInfiniteTransition()
    val animatedColor by transition.animateColor(
        initialValue = colors.first,
        targetValue = colors.second,
        animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse)
    )
    val animatedPosition by transition.animateFloat(
        initialValue = -80f,
        targetValue = 80f,
        animationSpec = infiniteRepeatable(tween(800), repeatMode = RepeatMode.Reverse)
    )
    FloatingActionButton(
        onClick = {},
        modifier = Modifier.offset(x = animatedPosition.dp),
        containerColor = animatedColor
    ) {
        Icon(imageVector = Icons.Default.PlayCircleFilled, contentDescription = null)
    }
}

@Composable
fun MultiStateAnimationCircleStrokeCanvas() {
    Subtitle(subtitle = "Canvas Circle stroke with transition")
    MultiStateAnimationCircleFilledCanvas()
}

@Composable
fun MultiStateAnimationCircleFilledCanvas(color: Color = Green500, radiusEnd: Float = 200f) {
    val transition = rememberInfiniteTransition()
    val floatAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = tween(3000, easing = LinearEasing), RepeatMode.Restart)
    )
    var selectColor by remember{ mutableStateOf(color)}
    Spacer(modifier = Modifier.height(100.dp))
    Canvas(modifier = Modifier.padding(16.dp)) {
        val centerOffset = Offset(0f, 0f)
        drawCircle(color = selectColor.copy(alpha = (1 - floatAnim)), radius = floatAnim * radiusEnd, center = centerOffset)
        drawCircle(
            color = selectColor.copy(alpha = (1.76f - floatAnim) % 1f),
            radius = ((floatAnim + 0.25f) % 1f) *radiusEnd,
            center = centerOffset
        )
        drawCircle(
            color = selectColor.copy(alpha = (1.5f - floatAnim) % 1f),
            radius = ((floatAnim + 0.5f) % 1f) *radiusEnd,
            center = centerOffset
        )
        drawCircle(
            color = selectColor.copy(alpha = (1.25f - floatAnim) % 1f),
            radius = ((floatAnim + 0.75f) % 1f) *radiusEnd,
            center = centerOffset
        )
    }
    Spacer(modifier = Modifier.height(100.dp))
    ColorPicker(onColorSelected = {selectColor = it})
}

