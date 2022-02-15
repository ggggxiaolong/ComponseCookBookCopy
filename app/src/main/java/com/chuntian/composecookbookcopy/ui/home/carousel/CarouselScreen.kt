package com.chuntian.composecookbookcopy.ui.home.carousel

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.chuntian.composecookbookcopy.R
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold

@Composable
fun CarouselScreen(onBack: () -> Unit){
    HomeScaffold(title = "Carousel", onBack = onBack) {
        Column(modifier = Modifier.padding(16.dp)) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.verify_finger))
            val count = remember { mutableStateOf(0f)}
            val progress by animateLottieCompositionAsState(composition,clipSpec = LottieClipSpec.Progress(count.value, count.value + 0.2f))
            LottieAnimation(
                composition,
                progress,
                Modifier.height(300.dp)
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                if (count.value == 1f){
                    count.value = 0f
                } else {
                    count.value = count.value + 0.2f
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Next")
            }
        }
    }
}