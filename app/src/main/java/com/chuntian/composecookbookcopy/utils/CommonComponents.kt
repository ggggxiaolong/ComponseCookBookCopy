package com.chuntian.composecookbookcopy.utils

import FaIcons
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.guru.fontawesomecomposelib.FaIcon

@Composable
fun HeadingSection(modifier: Modifier = Modifier, title: String = "", subtitle: String = "") {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (title.isNotEmpty()) {
            Text(text = title, style = MaterialTheme.typography.titleLarge.copy(fontSize = 14.sp))
        }
        if (subtitle.isNotEmpty()) {
            Text(text = subtitle, style = MaterialTheme.typography.titleMedium)
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
        text = title, style = MaterialTheme.typography.titleLarge.copy(fontSize = 14.sp),
        modifier = modifier.padding(8.dp)
    )
}

@Composable
fun Subtitle(subtitle: String, modifier: Modifier = Modifier) {
    Text(
        text = subtitle,
        style = MaterialTheme.typography.titleMedium,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodingScreen(onBack: () -> Unit) {
    Scaffold(topBar = {
        SmallTopAppBar(title = { Text(text = "Coding..") }, navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        })
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.Asset("working.json"))
            LottieAnimation(composition = composition, modifier = Modifier.height(300.dp))
            Text(
                text = "work in progress",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    })
}

@Composable
fun ImageChip(
    @DrawableRes image: Int,
    title: String,
    enabled: Boolean = true,
    selected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val border = if (enabled) BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary) else null
    val colors =
        if (enabled && !selected) ButtonDefaults.outlinedButtonColors() else ButtonDefaults.buttonColors()
    val contentColor = colors.contentColor(enabled = enabled).value
    Surface(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick
            ),
        shape = RoundedCornerShape(8.dp),
        color = colors.containerColor(enabled = enabled).value,
        contentColor = contentColor,
        border = border
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 8.dp)) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge.copy(color = contentColor),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}