package com.chuntian.composecookbookcopy.ui.home.renderScript

import android.graphics.BitmapFactory
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.chuntian.data.R
import com.google.android.renderscript.Toolkit


@Composable
fun RenderScriptScreen(onBack: () -> Unit) {
    HomeScaffold(title = "RenderScript", onBack = onBack) { padding ->
        RenderScriptView(padding)
    }
}

@Composable
private fun RenderScriptView(padding: PaddingValues) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        RenderEffectView(padding)
    } else {
        RenderToolkitView(padding = padding)
    }
}

//@Preview
@Composable
private fun RenderEffectView(padding: PaddingValues) {
    val blur = remember { mutableStateOf(Modifier.height(300.dp)) }
    val blurValue = remember { mutableStateOf(2) }
    val onBlur: () -> Unit = {
        blur.value = Modifier
            .height(300.dp)
            .blur(blurValue.value.dp)
    }
    val onSlide: (Float) -> Unit = { f ->
        blurValue.value = f.toInt()
    }
    Column(
        Modifier
            .padding(padding)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.food1),
            contentDescription = null,
            modifier = blur.value
        )
        Slider(
            value = blurValue.value.toFloat(),
            valueRange = 0f..25f,
            steps = 24,
            onValueChange = onSlide,
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = onBlur) {
            Text(text = "Blur")
        }
    }
}

@Composable
fun RenderToolkitView(padding: PaddingValues) {
    val context = LocalContext.current
    val imageBitmap = remember {
        mutableStateOf(
            BitmapFactory.decodeResource(context.resources, R.drawable.food1).asImageBitmap()
        )
    }
    val blurValue = remember { mutableStateOf(2) }
    val onBlur: () -> Unit = {
        if (blurValue.value > 0) {
            imageBitmap.value =
                Toolkit.blur(
                    BitmapFactory.decodeResource(context.resources, R.drawable.food1),
                    blurValue.value
                )
                    .asImageBitmap()
        } else {
            imageBitmap.value =
                BitmapFactory.decodeResource(context.resources, R.drawable.food1).asImageBitmap()
        }
    }
    val onSlide: (Float) -> Unit = { f ->
        blurValue.value = f.toInt()
    }
    Column(
        Modifier
            .padding(padding)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            bitmap = imageBitmap.value,
            contentDescription = null,
            modifier = Modifier.height(300.dp)
        )
        Slider(
            value = blurValue.value.toFloat(),
            valueRange = 0f..25f,
            steps = 24,
            onValueChange = onSlide,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Button(onClick = onBlur) {
            Text(text = "Blur")
        }
    }
}