package com.chuntian.composecookbookcopy.ui.templates.charts

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.chuntian.composecookbookcopy.utils.LocalNavControl
import com.chuntian.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.min
import kotlin.random.Random

val pieColors = listOf(Green200, Purple, TiktokBlue, TiktokRed, Blue700, Orange200)
val increasingChart5Times = (0..10).map { it * it * 1f }.toList()
val increasingChart10Times = (10..20).map { it * it * 1f }.toList()


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Preview
@Composable
fun ChartsScreen() {
    val controller = LocalNavControl.current
    HomeScaffold(title = "Compose Charts", onBack = { controller.popBackStack() }) {
        val pagerState = rememberPagerState()
        val scope = rememberCoroutineScope()
        Column {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                backgroundColor = MaterialTheme.colorScheme.background,
                contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.background),
                indicator = { position ->
                    TabRowDefaults.Indicator(Modifier.pagerTabIndicatorOffset(pagerState, position))
                }) {
                listOf("LineChart", "BarChart", "PieChart").forEachIndexed { index, s ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        text = { Text(text = s) },
                        onClick = { scope.launch { pagerState.scrollToPage(index) } }
                    )
                }
            }
            HorizontalPager(count = 3, state = pagerState) { page ->
                when (page) {
                    0 -> LineChartView()
                    1 -> Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .padding(16.dp)
                    ) {
                        BarChart(
                            values = createRandomFloatList(),
                            barColors = listOf(Blue, Purple, Orange, Yellow),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(500.dp)
                        )
                    }
                    else -> {
                        Column {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(16.dp)
                            ) {
                                val pieChartValues: List<Float> = (0..5).map { Random.nextFloat() * 10 }
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
                                val pieChartValues: List<Float> = (0..5).map { Random.nextFloat() * 10 }
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
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LineChartView() {
    LazyColumn {
        item {
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
        item {
            Card(
                modifier = Modifier.padding(16.dp),
            ) {
                LineChart(
                    yAxisValues = createRandomFloatList(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    lineColors = listOf(TiktokBlue, TiktokRed)
                )
            }
        }
        item {
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

fun createRandomFloatList(): List<Float> {
    val list = mutableListOf<Float>()
    (0..20).forEach { _ ->
        list.add(Random.nextFloat() * 10)
    }
    return list
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
        interval.forEach { value ->
            val xPoint = value * scaleX
            val yPoint = size.height - (yAxisValues[value] * scaleY) + yMove
            if (value == 0) {
                path.moveTo(0f, yPoint)
            } else {
                path.lineTo(xPoint, yPoint)
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

private fun getBounds(list: List<Float>): Pair<Float, Float> {
    var min = Float.MAX_VALUE
    var max = -Float.MAX_VALUE
    list.forEach {
        min = min.coerceAtMost(it)
        max = max.coerceAtLeast(it)
    }
    return min to max
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
    Canvas(modifier = modifier.padding(8.dp)) {
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

@Composable
fun PieCharts(values: List<Float>, animate: Boolean = true, modifier: Modifier = Modifier) {
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