package com.chuntian.composecookbookcopy.ui.home.advancelists

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold

@Composable
fun AdvanceListScreen(onBack: () -> Unit){
    HomeScaffold(title = "Advance List(In Progress)", onBack = onBack ) {
        AdvanceListView()
    }
}

@Composable
fun AdvanceListView(){
    val tabs = listOf("Shimmers", "Animated Lists", "Swipeable Lists")
//    val selectedIndex = remember { mutableStateOf(0) }
//    val pagerState: PagerState = run { remember {
//        PagerS
//    } }
}