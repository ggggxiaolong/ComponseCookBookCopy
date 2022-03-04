package linechart

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.chuntian.composecookbookcopy.ui.templates.charts.createRandomFloatList
import com.chuntian.composecookbookcopy.ui.templates.charts.getBounds
import com.chuntian.composecookbookcopy.ui.templates.charts.increasingChart10Times
import com.chuntian.composecookbookcopy.ui.templates.charts.increasingChart5Times
import com.chuntian.theme.*
import kotlinx.coroutines.delay
import kotlin.math.min
import kotlin.random.Random

enum class LineChartDemo {
    Simple, Curve, Live
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LineChartScreen(onBack: () -> Unit, type: LineChartDemo) {
    HomeScaffold(title = "Line Chart", onBack = onBack) {
        Column(modifier = Modifier.fillMaxSize()) {
            when (type) {
                LineChartDemo.Live -> {
                    Card(
                        modifier = Modifier.padding(16.dp),
                    ) {
                        val items = remember {
                            mutableStateListOf<Float>().apply {
                                addAll(createRandomFloatList())
                            }
                        }
                        LaunchedEffect(key1 = Unit, block = {
                            while (items.size < 100) {
                                delay(2000)
                                items.add(Random.nextFloat() * 5)
                            }
                        })
                        LineChart(
                            yAxisValues = items,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            drawLiveBot = true,
                            customXTarget = 100,
                        )
                    }
                }
                LineChartDemo.Curve -> {
                    Card(
                        modifier = Modifier.padding(16.dp),
                    ) {
                        LineChart(
                            yAxisValues = createRandomFloatList(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            lineColors = listOf(TiktokBlue, TiktokRed),
                            lineType = LineType.Curve
                        )
                    }
                }
                LineChartDemo.Simple -> {
                    Card(
                        modifier = Modifier.padding(16.dp),
                    ) {
                        Box {
                            LineChart(
                                yAxisValues = increasingChart5Times,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                lineColors = listOf(TiktokBlue, Blue200)
                            )
                            LineChart(
                                yAxisValues = increasingChart10Times,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                lineColors = listOf(Orange200, Orange700)
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class LineType {
    Line, Curve
}

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    lineColors: List<Color> = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary
    ),
    lineWidth: Float = 4f,
    yAxisValues: List<Float>,
    animate: Boolean = true,
    drawLiveBot: Boolean = false,
    animationKey: Any? = Unit,
    customXTarget: Int = 0,
    lineType: LineType = LineType.Line,
) {
    val x = remember { Animatable(0f) }
    val xTarget =
        if (customXTarget > 0) customXTarget.toFloat() else (yAxisValues.size - 1).toFloat()
    LaunchedEffect(key1 = animationKey, block = {
        x.animateTo(
            targetValue = xTarget,
            animationSpec = tween(durationMillis = if (animate) 500 else 0, easing = LinearEasing)
        )
    })
    val infiniteTransition = rememberInfiniteTransition()
    val radius by infiniteTransition.animateFloat(
        initialValue = 7f,
        targetValue = 15f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val opacity by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = LinearEasing
            ), repeatMode = RepeatMode.Reverse
        )
    )
    Canvas(modifier = modifier.padding(8.dp)) {
        val path = Path()
        val xBounds = Pair(0f, xTarget)
        val yBounds = getBounds(yAxisValues)
        val scaleX = size.width / (xBounds.second - xBounds.first)
        val scaleY = size.height / (yBounds.second - xBounds.first)
        val yMove = yBounds.first * scaleY
        val interval = (0..min(yAxisValues.size - 1, x.value.toInt()))
        val last = interval.last()
        if (lineType == LineType.Curve) {
            var prevPoint: Pair<Float, Float> = Pair(0f, 0f)
            interval.forEach { index ->
                val xPoint = index * scaleX
                val yPoint = size.height - (yAxisValues[index] * scaleY) + yMove
                if (index == 0) {
                    path.moveTo(0f, yPoint)
                } else {
                    val cpx = prevPoint.first + (xPoint - prevPoint.first) / 2
                    path.cubicTo(cpx, prevPoint.second, cpx, yPoint, xPoint, yPoint)
                }
                prevPoint = xPoint to yPoint
            }
        } else {
            interval.forEach { index ->
                val xPoint = index * scaleX
                val yPoint = size.height - (yAxisValues[index] * scaleY) + yMove
                if (index == 0) {
                    path.moveTo(0f, yPoint)
                } else {
                    path.lineTo(xPoint, yPoint)
                }
            }
        }
        drawPath(
            path = path,
            brush = Brush.linearGradient(lineColors),
            style = Stroke(width = lineWidth)
        )
        if (drawLiveBot) {
            drawCircle(
                color = lineColors.first(), radius,
                Offset(last * scaleX, size.height - (yAxisValues[last] * scaleY) + yMove),
                opacity
            )
        }
    }
}