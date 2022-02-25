package com.chuntian.composecookbookcopy.ui.animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.chuntian.data.R
import com.chuntian.composecookbookcopy.utils.Subtitle

@Composable
fun AnimationsForStates() {
    SimpleColorStateAnimation()
    Divider()
    SimpleDpStateAnimations()
    Divider()
    SimpleFloatStateAnimation()
    Divider()
    SimpleOffsetStateAnimation()
    Divider()
    SimpleAnimateCustomStateClass()
    Divider()
    DrawLayerWithAnimateAsStateAnimation()
}

@Composable
fun SimpleColorStateAnimation() {
    Subtitle(subtitle = "Animate color")
    val enabled = remember { mutableStateOf(true) }
    val animatedColor =
        animateColorAsState(targetValue = if (enabled.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)
    val buttonColors = ButtonDefaults.buttonColors(containerColor = animatedColor.value)
    Button(
        onClick = { enabled.value = !enabled.value },
        colors = buttonColors,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Color Animation")
    }
}

@Composable
fun SimpleDpStateAnimations() {
    Subtitle(subtitle = "Animation DP value")
    var enabled by remember { mutableStateOf(true) }
    val animatedColorState = animateColorAsState(
        targetValue =
        if (enabled) {
            MaterialTheme.colorScheme.primary
        } else MaterialTheme.colorScheme.secondary
    )
    val animatedHeightState = animateDpAsState(
        targetValue = if (enabled) 40.dp else 60.dp
    )
    val animatedWidthState = animateDpAsState(
        targetValue = if (enabled) 200.dp else 300.dp
    )
    val buttonColors = ButtonDefaults.buttonColors(containerColor = animatedColorState.value)
    Button(
        onClick = { enabled = !enabled },
        colors = buttonColors,
        modifier = Modifier
            .padding(16.dp)
            .height(animatedHeightState.value)
            .width(animatedWidthState.value)
    ) {
        Text(text = "Scale & Color")
    }
}

@Composable
fun SimpleFloatStateAnimation() {
    Subtitle(subtitle = "Animate Float value")
    var enable by remember { mutableStateOf(true) }
    val animatedFloatState = animateFloatAsState(targetValue = if (enable) 1f else 0.5f)
    Button(
        onClick = { enable = !enable },
        modifier = Modifier
            .padding(16.dp)
            .alpha(animatedFloatState.value)
    ) {
        Text(text = "Opacity change")
    }
}

@Composable
fun SimpleOffsetStateAnimation() {
    Subtitle(subtitle = "Animate Offset x,y value")
    Spacer(modifier = Modifier.height(20.dp))
    var enabled by remember { mutableStateOf(true) }
    val animatedOffset by animateOffsetAsState(
        targetValue = if (enabled) Offset(
            x = 0f,
            y = 0f
        ) else Offset(x = 50f, y = 40f)
    )
    Row(horizontalArrangement = Arrangement.SpaceAround) {
        Image(
            painter = painterResource(id = R.drawable.p1),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp)
                .offset(x = Dp(animatedOffset.x), y = Dp(animatedOffset.y))
                .clickable { enabled = !enabled }
        )
        Image(
            painter = painterResource(id = R.drawable.p2),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp)
                .offset(x = -Dp(animatedOffset.x), y = -Dp(animatedOffset.y))
                .clickable { enabled = !enabled }
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

data class CustomAnimationState(val width: Dp, val rotation: Float)

@Composable
fun SimpleAnimateCustomStateClass() {
    Subtitle(subtitle = "Animate Custom Class State with 2D vector")
    var enabled by remember { mutableStateOf(true) }
    val initUiState = CustomAnimationState(200.dp, 0f)
    val targetUiState = CustomAnimationState(300.dp, 15f)
    val uiState = if (enabled) initUiState else targetUiState
    val animationState by animateValueAsState(
        targetValue = uiState,
        typeConverter = TwoWayConverter(
            convertToVector = { AnimationVector2D(it.width.value, it.rotation) },
            convertFromVector = { CustomAnimationState(it.v1.dp, it.v2) }
        ),
        animationSpec = tween(600)
    )
    Button(
        onClick = { enabled = !enabled },
        modifier = Modifier
            .padding(16.dp)
            .width(animationState.width)
            .rotate(animationState.rotation)
    ) {
        Text(text = "Custom Animation")
    }
    Text(
        text = "You can also use Size, Int, Rect using AnimateAsState",
        style = MaterialTheme.typography.labelMedium
    )
}

@Composable
fun DrawLayerWithAnimateAsStateAnimation() {
    Subtitle(subtitle = "Float state Animations on graphicsLayer")
    Spacer(modifier = Modifier.padding(30.dp))
    var draw by remember { mutableStateOf(false) }
    val shadow1 = animateFloatAsState(targetValue = if (draw) 30f else 5f)
    val transformX1 = animateFloatAsState(targetValue = if (draw) 320f else 0f)
    val shadow2 = animateFloatAsState(targetValue = if (draw) 30f else 10f)
    val transformX2 = animateFloatAsState(targetValue = if (draw) -320f else 0f)
    val transformY2 = animateFloatAsState(targetValue = if (draw) 0f else 25f)
    val shadow3 = animateFloatAsState(targetValue = if (draw) 30f else 5f)
    val transformY3 = animateFloatAsState(targetValue = if (draw) 0f else 50f)
    Box {
        Image(
            painter = painterResource(id = R.drawable.adele21),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer {
                    shadowElevation = shadow1.value
                    translationX = transformX1.value
                    translationY = 0f
                }
                .clickable { draw = !draw }
        )
        Image(
            painter = painterResource(id = R.drawable.dualipa),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer {
                    shadowElevation = shadow2.value
                    translationX = transformX2.value
                    translationY = transformY2.value
                }
                .clickable { draw = !draw }
        )
        Image(
            painter = painterResource(id = R.drawable.edsheeran),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer {
                    shadowElevation = shadow3.value
                    translationY = transformY3.value
                }
                .clickable { draw = !draw }
        )
    }
    Spacer(modifier = Modifier.padding(30.dp))
    var draw2 by remember { mutableStateOf(false) }
    val shadow21 = animateFloatAsState(targetValue = if (draw2) 30f else 10f)
    val translateX21 = animateFloatAsState(targetValue = if (draw2) -320f else 0f)
    val rotationY21 = animateFloatAsState(targetValue = if (draw2) 45f else 0f)

    val shadow22 = animateFloatAsState(targetValue = if (draw2) 30f else 5f)
    val translateY22 = animateFloatAsState(targetValue = if (draw2) 0f else 30f)
    val rotationY22 = animateFloatAsState(targetValue = if (draw2) 45f else 0f)

    val shadow23 = animateFloatAsState(targetValue = if (draw2) 30f else 5f)
    val translateX23 = animateFloatAsState(targetValue = if (draw2) 320f else 0f)
    val rotationY23 = animateFloatAsState(targetValue = if (draw2) 45f else 0f)
    val translateY23 = animateFloatAsState(targetValue = if (draw2) 0f else 50f)
    Box {
        Image(
            painter = painterResource(id = R.drawable.wolves),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer {
                    shadowElevation = shadow21.value
                    translationX = translateX21.value
//
                    translationY = 0f
                    rotationY = rotationY21.value
                }
                .clickable { draw2 = !draw2 }
        )
        Image(
            painter = painterResource(id = R.drawable.sam),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer {
                    shadowElevation = shadow22.value
                    translationY = translateY22.value
                    rotationY = rotationY22.value
                }
                .clickable { draw2 = !draw2 }
        )
        Image(
            painter = painterResource(id = R.drawable.billie),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer {
                    shadowElevation = shadow23.value
                    translationX = translateX23.value
                    rotationY = rotationY23.value
                    translationY = translateY23.value
                }
                .clickable { draw2 = !draw2 }
        )
    }
    Spacer(modifier = Modifier.height(30.dp))
    var draw3 by remember { mutableStateOf(false) }
    val shadow31 = animateFloatAsState(targetValue = if (draw3) 30f else 5f)
    val translateX31 = animateFloatAsState(targetValue = if (draw3) 320f else 0f)
    val rotationZ31 = animateFloatAsState(targetValue = if (draw3) 45f else 0f)

    val shadow32 = animateFloatAsState(targetValue = if (draw3) 30f else 10f)
    val translateX32 = animateFloatAsState(targetValue = if (draw3) -320f else 0f)
    val translateY32 = animateFloatAsState(targetValue = if (draw3) 0f else 30f)
    val rotationZ32 = animateFloatAsState(targetValue = if (draw3) 45f else 0f)

    val shadow33 = animateFloatAsState(targetValue = if (draw3) 30f else 5f)
    val translateY33 = animateFloatAsState(targetValue = if (draw3) 0f else 50f)
    val rotationZ33 = animateFloatAsState(targetValue = if (draw3) 45f else 0f)

    Box {
        Image(
            painter = painterResource(id = R.drawable.camelia),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer {
                    shadowElevation = shadow31.value
                    translationX = translateX31.value
                    rotationZ = rotationZ31.value
                }
                .clickable { draw3 = !draw3 }
        )

        Image(
            painter = painterResource(id = R.drawable.eminem),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer {
                    shadowElevation = shadow32.value
                    translationX = translateX32.value
                    rotationZ = rotationZ32.value
                    translationY = translateY32.value
                }
                .clickable { draw3 = !draw3 }
        )

        Image(
            painter = painterResource(id = R.drawable.dancemonkey),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer {
                    shadowElevation = shadow33.value
                    translationY = translateY33.value
                    rotationZ = rotationZ33.value
                }
                .clickable { draw3 = !draw3 }
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}