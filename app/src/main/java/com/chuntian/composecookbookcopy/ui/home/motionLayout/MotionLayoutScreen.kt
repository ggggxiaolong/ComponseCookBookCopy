package com.chuntian.composecookbookcopy.ui.home.motionLayout

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.chuntian.data.AlbumsDataProvider

@Composable
fun MotionLayoutScree(onBack: () -> Unit) {
    HomeScaffold(title = "MotionLayout", onBack = onBack) {
        Column(Modifier.background(Color.White)) {
            ButtonMotionLayoutView()
            Spacer(modifier = Modifier.height(200.dp))
            ImageMotionLayout()
        }
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
private fun ButtonMotionLayoutView() {
    val animateButton = remember { mutableStateOf(false) }
    val buttonAnimationProcess by animateFloatAsState(
        targetValue = if (animateButton.value) 1f else 0f,
        animationSpec = tween(1000)
    )
    val onClick: () -> Unit = { animateButton.value = !animateButton.value }
    Spacer(modifier = Modifier.height(16.dp))
    MotionLayout(
        start = ConstraintSet(
            """{
                button1 : {
                  width: "spread",
                  height: 60,
                  start: ['parent', 'start', 16],
                  end: ['parent', 'end', 16],
                  top: ['parent', 'top', 16]
                },
                button2: {
                  width: "spread",
                  height: 60,
                  start: ['parent', 'start', 16],
                  end: ['parent', 'end', 16],
                  top: ['button1', 'bottom', 16]
                },
                button3: {
                  width: "spread",
                  height: 60,
                  start: ['parent', 'start', 16],
                  end: ['parent', 'end', 16],
                  top: ['button2', 'bottom', 16]
                }
                }"""
        ),
        end = ConstraintSet(
            """{
            button1: {
              width: 100,
              height: 60,
              start: ['parent', 'start', 16],
              end: ['button2', 'start', 16]
            },
            button2: {
              width: 100,
              height: 60,
              start: ['button1', 'end', 16],
              end: ['button3', 'start', 16]
            },
            button3: {
              width: 100,
              height: 60,
              start: ['button2', 'end', 16],
              end: ['parent', 'end', 16]
            },
        }"""
        ),
        progress = buttonAnimationProcess,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                Color.White
            )
    ) {
        for (i in 1..3) {
            Button(onClick = onClick, modifier = Modifier.layoutId("button$i")) {
                Text(text = "Button$i")
            }
        }
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
private fun ImageMotionLayout() {
    val albums = AlbumsDataProvider.albums.take(4)
    var animateImage by remember { mutableStateOf(false) }
    val imageAnimationProgress by animateFloatAsState(
        targetValue = if (animateImage) 1f else 0f,
        animationSpec = tween(1000)
    )
    val onClick: () -> Unit = { animateImage = !animateImage }
    MotionLayout(
        start = ConstraintSet("""{
              image0: {
                width: 150,
                height: 150,
                start: ['parent', 'start', 16]
              },
              image1: {
                width: 150,
                height: 150,
                start: ['parent', 'start', 24]
              },
              image2: {
                width: 150,
                height: 150,
                start: ['parent', 'start', 32]
              },
              image3: {
                width: 150,
                height: 150,
                start: ['parent', 'start', 40]
              },
            }"""),
        end = ConstraintSet("""{
              image0: {
                width: 150,
                height: 150,
                start: ['parent', 'start', 16]
              },
              image1: {
                width: 150,
                height: 150,
                start: ['image0', 'end', 16]
              },
              image2: {
                width: 150,
                height: 150,
                start: ['parent', 'start', 16],
                top: ['image0', 'bottom', 16]
              },
              image3: {
                width: 150,
                height: 150,
                start: ['image2', 'end', 16],
                top: ['image0', 'bottom', 16]
              }
            }"""),
        progress = imageAnimationProgress,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        albums.forEachIndexed { index, album ->
            Image(
                painter = painterResource(id = album.imageId),
                contentDescription = null,
                modifier = Modifier
                    .layoutId("image$index")
                    .clickable(onClick = onClick)
            )
        }
    }
}

@Preview
@Composable
fun PreviewMotionLayoutButtons() {
    Column(Modifier.background(Color.White)) {
        ButtonMotionLayoutView()
        Spacer(modifier = Modifier.height(200.dp))
        ImageMotionLayout()
    }
}