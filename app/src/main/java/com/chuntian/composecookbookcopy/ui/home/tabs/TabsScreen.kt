package com.chuntian.composecookbookcopy.ui.home.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.chuntian.composecookbookcopy.ui.home.list.GridListItemView
import com.chuntian.composecookbookcopy.ui.home.list.VerticalListView
import com.chuntian.composecookbookcopy.utils.ImageChip
import com.chuntian.data.DemoDataProvider
import com.chuntian.demo.instagram.ui.posts.PostItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.random.Random

enum class TabType(val value: String) {
    Apple("Apple"), Google("Google"), Amazon("Amazon")
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsScreen(onBack: () -> Unit) {
    val pagerState = rememberPagerState()
    val tabs = TabType.values()
    val scope = rememberCoroutineScope()
    HomeScaffold(title = "Tabs", onBack = onBack) {
        Column(modifier = Modifier.padding(it)) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                backgroundColor = MaterialTheme.colorScheme.background,
                contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.background),
                indicator = { position ->
                    TabRowDefaults.Indicator(Modifier.pagerTabIndicatorOffset(pagerState, position))
                }) {
                for (item in tabs) {
                    Tab(
                        selected = pagerState.currentPage == item.ordinal,
                        text = { Text(text = item.value) },
                        onClick = { scope.launch { pagerState.scrollToPage(item.ordinal) } }
                    )
                }
            }
            HorizontalPager(count = tabs.size, state = pagerState) { page ->
                when (page) {
                    0 -> AppleView()
                    1 -> GoogleView()
                    else -> AmazonView()
                }
            }
        }
    }
}

@Composable
fun AppleView() {
    val items = DemoDataProvider.tweetList.filter { it.tweetImageId > 0 }
    val selectItem = remember { mutableStateOf(0) }
    Column {
        ScrollableTabRow(
            selectedTabIndex = selectItem.value,
            contentColor = MaterialTheme.colorScheme.background,
            indicator = {},
            backgroundColor = MaterialTheme.colorScheme.background,
            edgePadding = 16.dp
        ) {
            items.forEachIndexed { index, item ->
                Box(modifier = Modifier.padding(end = 8.dp)) {
                    ImageChip(
                        image = item.authorImageId,
                        title = item.author,
                        selected = selectItem.value == index,
                        onClick = { selectItem.value = index },
                    )
                }
            }
        }
        PostItem(post = items[selectItem.value], isLiked = Random.nextBoolean())
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GoogleView() {
    GridListItemView()
}

@Composable
fun AmazonView() {
    VerticalListView()
}