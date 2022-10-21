package com.chuntian.composecookbookcopy.ui.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import com.chuntian.data.model.Tweet
import kotlin.math.roundToInt

@Composable
fun SwipeButton(
    onSwiped: () -> Unit,
    modifier: Modifier = Modifier,
    swipeButtonState: SwipeButtonState,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    shape: Shape = CircleShape,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    icon: ImageVector = Icons.Default.ArrowForward,
    rotateIcon: Boolean = true,
    iconPadding: PaddingValues = PaddingValues(2.dp),
    content: @Composable RowScope.() -> Unit,
) {
    val enableContainerColor: Color = MaterialTheme.colorScheme.primary
    val enableContentColor: Color = MaterialTheme.colorScheme.onPrimary
    val disabledContainerColor: Color = MaterialTheme.colorScheme.onSurface.copy(0.12f)
    val disabledContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(0.38f)
    val containerColor by rememberUpdatedState(if (enabled) enableContainerColor else disabledContainerColor)
    val contentColor by rememberUpdatedState(if (enabled) enableContentColor else disabledContentColor)
    val dragOffset = remember { mutableStateOf(0f) }
    val collapsed = swipeButtonState == SwipeButtonState.Collapsed
    val swipe = swipeButtonState == SwipeButtonState.Swiped

    Surface(
        modifier = modifier,
        shape = shape,
        color = containerColor,
        contentColor = contentColor,
        border = border,
//        shadowElevation = elevation?.shadowElevation(
//            enabled = enabled,
//            interactionSource = interactionSource
//        )?.value ?: 0.dp,
//        tonalElevation = elevation?.tonalElevation(
//            enabled = enabled,
//            interactionSource = interactionSource
//        )?.value ?: 0.dp,
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            val maxWidth = this.constraints.maxWidth.toFloat()

            when {
                collapsed -> {
                    val animatedProcess = remember { Animatable(initialValue = 0f) }
                    LaunchedEffect(Unit) {
                        animatedProcess.animateTo(targetValue = 1f, animationSpec = tween(600))
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .scale(animatedProcess.value)
                            .padding(iconPadding)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.onPrimary)
                            .align(Alignment.Center)

                    ) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Done",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                swipe -> {
                    HorizontalDottedProgressBar()
                }
                else -> {
                    dragOffset.value = 0f
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        ProvideTextStyle(value = MaterialTheme.typography.bodyMedium) {
                            Row(
                                modifier
                                    .fillMaxWidth()
                                    .padding(contentPadding),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                content = content
                            )
                        }
                    }
                }
            }

            AnimatedVisibility(visible = !swipe) {
                IconButton(
                    onClick = {}, enabled = enabled, modifier = Modifier
                        .padding(iconPadding)
                        .align(Alignment.CenterStart)
                        .offset { IntOffset(dragOffset.value.roundToInt(), 0) }
                        .draggable(
                            enabled = enabled,
                            orientation = Orientation.Horizontal,
                            state = rememberDraggableState { delta ->
                                val newValue = dragOffset.value + delta
                                dragOffset.value = newValue.coerceIn(0f, maxWidth)
                            },
                            onDragStopped = {
                                if (dragOffset.value > maxWidth * 2 / 3) {
                                    dragOffset.value = maxWidth
                                    onSwiped.invoke()
                                } else {
                                    dragOffset.value = 0f
                                }
                            },
                        )
                        .background(MaterialTheme.colorScheme.onPrimary, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Arrow",
                        modifier = Modifier.graphicsLayer {
                            if (rotateIcon) {
                                rotationZ += dragOffset.value / 5
                            }
                        },
                        tint = containerColor
                    )
                }
            }
        }
    }
}

enum class SwipeButtonState {
    Initial, Swiped, Collapsed
}