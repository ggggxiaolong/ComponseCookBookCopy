package com.chuntian.composecookbookcopy.ui.templates.charts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.chuntian.composecookbookcopy.ui.templates.charts.barchart.BarChartScreen
import com.chuntian.composecookbookcopy.ui.templates.charts.piechart.PieChartView
import com.chuntian.data.PATH
import com.chuntian.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import linechart.LineChartDemo
import linechart.LineChartScreen
import kotlin.random.Random

val pieColors = listOf(Green200, Purple, TiktokBlue, TiktokRed, Blue700, Orange200)
val increasingChart5Times = (0..10).map { it * it * 1f }.toList()
val increasingChart10Times = (10..20).map { it * it * 1f }.toList()

enum class ChartDemos(val value: String, val path: String) {
    SimpleLineChart("Simple Line Chart", PATH.TEMPLATE_CHARTS + "/simpleLine"),
    CurveLineChart("Curve Line Chart", PATH.TEMPLATE_CHARTS + "/curveLine"),
    LiveLineChart("Live Line Chart", PATH.TEMPLATE_CHARTS + "/liveLine"),
    BarChart("Bar Line Chart", PATH.TEMPLATE_CHARTS + "/barchart"),
    PieChart("Pie Line Chart", PATH.TEMPLATE_CHARTS + "/pieChart"),
}

@Preview
@Composable
fun ChartsScreen() {
    val controller = rememberNavController()
    val onBack: () -> Unit = { controller.popBackStack() }
    NavHost(navController = controller, startDestination = PATH.TEMPLATE_CHARTS) {
        composable(PATH.TEMPLATE_CHARTS) { RootView(onBack, controller) }
        composable(ChartDemos.SimpleLineChart.path) {
            LineChartScreen(
                onBack = onBack,
                type = LineChartDemo.Simple
            )
        }
        composable(ChartDemos.CurveLineChart.path) {
            LineChartScreen(
                onBack = onBack,
                type = LineChartDemo.Curve
            )
        }
        composable(ChartDemos.LiveLineChart.path) {
            LineChartScreen(
                onBack = onBack,
                type = LineChartDemo.Live
            )
        }
        composable(ChartDemos.BarChart.path) {
            BarChartScreen(onBack = onBack)
        }
        composable(ChartDemos.PieChart.path) {
            PieChartView(onBack = onBack)
        }
    }
}

@Composable
fun RootView(onBack: () -> Unit, controller: NavController) {
    HomeScaffold(title = "Compose Charts", onBack = onBack) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val items = ChartDemos.values()
            for (item in items) {
                Button(
                    onClick = { controller.navigate(item.path) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(text = item.value)
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

fun getBounds(list: List<Float>): Pair<Float, Float> {
    var min = Float.MAX_VALUE
    var max = -Float.MAX_VALUE
    list.forEach {
        min = min.coerceAtMost(it)
        max = max.coerceAtLeast(it)
    }
    return min to max
}