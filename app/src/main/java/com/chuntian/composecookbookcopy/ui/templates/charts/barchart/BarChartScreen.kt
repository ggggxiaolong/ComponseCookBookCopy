package com.chuntian.composecookbookcopy.ui.templates.charts.barchart

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.chuntian.composecookbookcopy.ui.templates.charts.createRandomFloatList
import com.chuntian.composecookbookcopy.ui.templates.charts.getBounds
import com.chuntian.theme.Blue
import com.chuntian.theme.Orange
import com.chuntian.theme.Purple
import com.chuntian.theme.Yellow
import kotlin.math.min

@Composable
fun BarChartScreen(onBack: () -> Unit) {
    HomeScaffold(title = "Bar Chart", onBack = onBack) {
        BarChart(
            values = createRandomFloatList(),
            barColors = listOf(Blue, Purple, Orange, Yellow),
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .height(500.dp)
        )
    }
}

@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    barColors: List<Color> = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary
    ),
    barWidth: Float = 20f,
    values: List<Float>,
    animate: Boolean = true
) {
    val x = remember { Animatable(0f) }
    val xTarget = (values.size - 1).toFloat()
    LaunchedEffect(key1 = Unit, block = {
        x.animateTo(
            targetValue = xTarget,
            animationSpec = tween(durationMillis = if (animate) 500 else 0, easing = LinearEasing)
        )
    })
    Canvas(modifier = modifier.padding(16.dp)) {
        val xBounds = Pair(0f, xTarget)
        val yBounds = getBounds(values)
        val scaleX = size.width / (xBounds.second - xBounds.first)
        val scaleY = size.height / (yBounds.second - xBounds.first)
        val yMove = yBounds.first * scaleY

        (0..min(values.size - 1, x.value.toInt())).forEach { value ->
            val xOffset = value * scaleX
            val yOffset = size.height - (values[value] * scaleY) + yMove
            drawRect(
                topLeft = Offset(xOffset, yOffset),
                size = Size(barWidth, size.height - yOffset),
                brush = Brush.linearGradient(barColors)
            )
        }
    }
}