package com.chuntian.composecookbookcopy.ui.templates.onBoarding

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Preview
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingScreen() {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    Scaffold {
        Column(modifier = Modifier.fillMaxSize()) {
            TextButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 16.dp)
            ) {
                Text(text = "Skip")
            }
            HorizontalPager(
                count = 3,
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                val item = items[pagerState.currentPage]
                val composition = rememberLottieComposition(
                    spec = LottieCompositionSpec.Asset(item.third)
                )
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnimation(
                        composition = composition.value,
                        modifier = Modifier.height(250.dp),
                        iterations = Int.MAX_VALUE
                    )
                    Text(
                        text = item.first,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = item.second,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 80.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                for (i in (0..2)) {
                    Icon(
                        imageVector = Icons.Filled.Album,
                        contentDescription = null,
                        modifier = Modifier.padding(4.dp),
                        tint = if (i == pagerState.currentPage) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                }
            }
            Button(
                onClick = {
                    if (pagerState.currentPage < 2) {
                        scope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                modifier = Modifier
                    .animateContentSize()
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 32.dp)
                    .height(50.dp)
            ) {
                Text(text = if (pagerState.currentPage < 2) "Next" else "Let's Begin")
            }
        }
    }
}

val items = listOf(
    Triple(
        "Team Collaborations",
        "Our tools help your teams collaborate for the best output results",
        "profile.json"
    ),
    Triple(
        "Improve Productivity",
        "Our tools are designed to improve productivity by automating all the stuff for you",
        "working.json"
    ),
    Triple(
        "Growth Tracking",
        "We provide dashboard and charts to track your growth easily and suggestions.",
        "food.json"
    )
)