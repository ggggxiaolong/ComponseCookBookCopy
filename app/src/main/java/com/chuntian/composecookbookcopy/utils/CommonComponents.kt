package com.chuntian.composecookbookcopy.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.fontawesomecomposelib.FaIcon

@Composable
fun HeadingSection(modifier: Modifier = Modifier, title: String = "", subtitle: String = "") {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (title.isNotEmpty()) {
            Text(text = title, style = MaterialTheme.typography.h6.copy(fontSize = 14.sp))
        }
        if (subtitle.isNotEmpty()) {
            Text(text = subtitle, style = MaterialTheme.typography.subtitle2)
        }
        Divider()
    }
}

@Preview
@Composable
fun previewHeadingSection() {
    HeadingSection(title = "Title", subtitle = "this is Subtitle")
}

@Composable
fun TitleText(modifier: Modifier = Modifier, title: String) {
    Text(
        text = title, style = MaterialTheme.typography.h6.copy(fontSize = 14.sp),
        modifier = modifier.padding(8.dp)
    )
}

@Composable
fun Subtitle(subtitle: String, modifier: Modifier = Modifier) {
    Text(
        text = subtitle,
        style = MaterialTheme.typography.subtitle2,
        modifier = modifier.padding(8.dp)
    )
}

@Composable
fun RotateIcon(
    state: Boolean,
    asset: ImageVector,
    angle: Float,
    duration: Int,
    modifier: Modifier = Modifier
) {
    FaIcon(
        faIcon = FaIcons.Play,
        tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
        size = 20.dp,
        modifier = modifier
            .padding(2.dp)
            .graphicsLayer(
                rotationZ = animateFloatAsState(if (state) 0f else angle, tween(duration)).value
            )

    )
}

@Composable
fun CodingScreen(onBack: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Coding..") }, navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        })
    }, content = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = "Coding..", style = MaterialTheme.typography.h3, modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }
    })
}