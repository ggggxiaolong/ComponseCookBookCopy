package com.chuntian.composecookbookcopy.ui.home.advancelists

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

enum class ShimmerAnimationType {
    BASIC, FADE, SHIMMER
}

@Preview
@Composable
fun ShimmerView() {
    val animateType = remember { mutableStateOf(ShimmerAnimationType.BASIC) }

    @Composable
    fun buttonColors(type: ShimmerAnimationType) = ButtonDefaults.buttonColors(
        backgroundColor = if (type == animateType.value) MaterialTheme.colorScheme.primary else Color.LightGray
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { animateType.value = ShimmerAnimationType.FADE },
                colors = buttonColors(
                    type = ShimmerAnimationType.FADE
                ),
                modifier = Modifier
                    .width(120.dp)
            ) {
                Text(text = "Fading")
            }
            Button(
                onClick = { animateType.value = ShimmerAnimationType.BASIC },
                colors = buttonColors(
                    type = ShimmerAnimationType.BASIC
                ),
                modifier = Modifier
                    .width(120.dp)
            ) {
                Text(text = "Basic")
            }
            Button(
                onClick = { animateType.value = ShimmerAnimationType.SHIMMER },
                colors = buttonColors(
                    type = ShimmerAnimationType.SHIMMER
                ),
                modifier = Modifier
                    .width(120.dp)
            ) {
                Text(text = "Shimmer")
            }
        }
        ShimmerItem(animateType.value)
        ShimmerBigItem(animateType.value)
        ShimmerItem(animateType.value)
        ShimmerItem(animateType.value)
    }

}

@Composable
fun ShimmerItem(type: ShimmerAnimationType) {
    val highlight: PlaceholderHighlight? = when (type) {
        ShimmerAnimationType.SHIMMER -> PlaceholderHighlight.shimmer(highlightColor = Color.White)
        ShimmerAnimationType.FADE -> PlaceholderHighlight.fade(
            highlightColor = Color.White, animationSpec = infiniteRepeatable(
                tween(durationMillis = 1200,  easing = LinearEasing), RepeatMode.Reverse
            )
        )
        ShimmerAnimationType.BASIC -> null
    }
    Row(modifier = Modifier.padding(16.dp)) {
        Spacer(
            modifier = Modifier
                .size(100.dp)
                .placeholder(visible = true, color = Color.LightGray, highlight = highlight)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            for (i in 0..2) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .padding(8.dp)
                        .placeholder(visible = true, color = Color.LightGray, highlight = highlight)
                )
            }
        }
    }
}

@Composable
fun ShimmerBigItem(type: ShimmerAnimationType) {
    val highlight: PlaceholderHighlight? = when (type) {
        ShimmerAnimationType.SHIMMER -> PlaceholderHighlight.shimmer(highlightColor = Color.White)
        ShimmerAnimationType.FADE -> PlaceholderHighlight.fade(
            highlightColor = Color.White, animationSpec = infiniteRepeatable(
                tween(durationMillis = 1200,  easing = LinearEasing), RepeatMode.Reverse
            )
        )
        ShimmerAnimationType.BASIC -> null
    }
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(bottom = 8.dp)
                .placeholder(visible = true, color = Color.LightGray, highlight = highlight)
        )
        for (i in 0..2) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(vertical = 8.dp)
                    .placeholder(visible = true, color = Color.LightGray, highlight = highlight)
            )
        }
    }
}