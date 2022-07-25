package com.chuntian.composecookbookcopy.ui.templates.charts.piechart

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.chuntian.composecookbookcopy.ui.templates.charts.pieColors
import kotlin.math.min
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PieChartView(onBack: () -> Unit) {
    HomeScaffold(title = "Pie Chart", onBack = onBack) {
        Column(modifier = Modifier.padding(it)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp)
            ) {
                val pieChartValues: List<Float> =
                    (0..5).map { Random.nextFloat() * 10 }
                PieCharts(
                    values = pieChartValues,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }
            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(300.dp)
                    .padding(16.dp)
            ) {
                val pieChartValues: List<Float> =
                    (0..5).map { Random.nextFloat() * 10 }
                PieCharts(
                    values = pieChartValues,
                    modifier = Modifier
                        .width(200.dp)
                        .height(300.dp)
                )
            }
        }
    }
}

@Composable
fun PieCharts(modifier: Modifier = Modifier, values: List<Float>, animate: Boolean = true) {
    val animatePercent = remember { Animatable(0f) }
    LaunchedEffect(key1 = Unit, block = {
        animatePercent.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = if (animate) 1000 else 0, easing = LinearEasing)
        )
    })
    Canvas(modifier = modifier.padding(16.dp), onDraw = {
        val totalValue = values.sum()
        var startAngle = 0f
        val minSize = min(size.height, size.width)
        val isOffsetX = minSize == size.height
        (values.indices).forEach { index ->
            val sliceAngle: Float = 360f * values[index] / totalValue * animatePercent.value
            drawArc(
                color = pieColors[index],
                size = Size(minSize, minSize),
                startAngle = startAngle,
                sweepAngle = sliceAngle,
                useCenter = true,
                topLeft = if (isOffsetX)
                    Offset((size.width - minSize) / 2, 0f)
                else
                    Offset(0f, (size.height - minSize) / 2)
            )
            startAngle += sliceAngle
        }
    })

}