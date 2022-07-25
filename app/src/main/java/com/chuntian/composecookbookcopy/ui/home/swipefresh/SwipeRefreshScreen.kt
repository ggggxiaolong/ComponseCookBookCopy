package com.chuntian.composecookbookcopy.ui.home.swipefresh

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.chuntian.composecookbookcopy.ui.home.list.VerticalListView
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun SwipeRefreshScreen(onBack: () -> Unit) {
    val model = SwipeRefreshViewModel()
    val isRefreshing = model.isRefreshing.observeAsState(false)
    HomeScaffold(title = "SwipeRefresh", onBack = onBack) {
        SwipeRefresh(
            modifier = Modifier.padding(it),
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing.value),
            onRefresh = { model.fresh() }) {
            VerticalListView(padding = PaddingValues(0.dp))
        }
    }
}