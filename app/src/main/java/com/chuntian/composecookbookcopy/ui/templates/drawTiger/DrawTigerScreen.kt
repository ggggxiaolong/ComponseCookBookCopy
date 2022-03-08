package com.chuntian.composecookbookcopy.ui.templates.drawTiger

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.R
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import kotlinx.coroutines.delay


@Composable
fun DrawTigerScreen(onBack: () -> Unit) {
    HomeScaffold(title = "Draw Tiger", onBack = onBack) {
        var pathList by remember{ mutableStateOf(emptyList<Path>())}
        val res = stringArrayResource(id = R.array.tiger_path).map {
            SvgPath(it).generatePath().asComposePath()
        }
        LaunchedEffect(key1 = Unit, block = {
            delay(1000)
            res.indices.forEach {
                delay(100)
                pathList = res.take(it)
            }
        })
        val color = MaterialTheme.colorScheme.primary
        Canvas(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxSize(),
            onDraw = {
                pathList.forEach {
                    drawPath(
                        path = it,
                        color = color,
                    )
                }

            })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    val pathList = arrayListOf<Path>()

    stringArrayResource(id = R.array.tiger_path).forEach {
        val path = SvgPath(it).generatePath().asComposePath()
        pathList.add(path)
    }

    val color = Color(0xFFFFFFFF)
    Canvas(
        modifier = Modifier
            .fillMaxSize(),
        onDraw = {
            pathList.forEach {
                drawPath(
                    path = it,
                    color = color,
                )
            }

        })
}