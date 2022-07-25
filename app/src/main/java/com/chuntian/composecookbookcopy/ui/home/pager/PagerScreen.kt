package com.chuntian.composecookbookcopy.ui.home.pager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.chuntian.data.DemoDataProvider
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PagerScreen(onBack: () -> Unit) {
    val items = DemoDataProvider.itemList.take(10)
    HomeScaffold(title = "Pager", onBack = onBack) {
        val pagerSate = rememberPagerState(initialPage = 1)
        val pagerSate2 = rememberPagerState(initialPage = 1)
        Column(modifier = Modifier.padding(it).fillMaxSize()) {
            HorizontalPager(
                count = items.size,
                state = pagerSate,
                contentPadding = PaddingValues(horizontal = 36.dp),
                modifier = Modifier.height(200.dp)
            ) { page ->
                Box(modifier = Modifier) {
                    Image(
                        painter = painterResource(id = items[page].imageId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Text(
                        text = items[page].title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .align(Alignment.BottomCenter   )
                            .padding(16.dp)
                            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(4.dp))
                            .padding(8.dp)
                    )
                }
            }
            HorizontalPagerIndicator(
                pagerState = pagerSate,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalPager(
                count = items.size,
                state = pagerSate2,
                contentPadding = PaddingValues(horizontal = 36.dp),
                modifier = Modifier.height(200.dp)
            ) { page ->
                Box(modifier = Modifier.graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }) {
                    Image(
                        painter = painterResource(id = items[page].imageId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
            }
        }
    }
}