package com.chuntian.demo.youtube.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun YoutubeChip(selected: Boolean, text: String, modifier: Modifier = Modifier) {
    val isLight = MaterialTheme.colors.isLight
    val (color, contentColor, borderColor) = if (selected) {
        Triple(
            MaterialTheme.colors.onSurface.copy(alpha = if (isLight) 0.7f else 1f),
            MaterialTheme.colors.surface,
            MaterialTheme.colors.surface
        )
    } else {
        Triple(
            MaterialTheme.colors.onSurface.copy(alpha = if (isLight) 0.04f else 0.07f),
            MaterialTheme.colors.onSurface,
            if (isLight) Color.LightGray else Color.DarkGray
        )
    }
    Surface(
        color = color,
        contentColor = contentColor,
        border = BorderStroke(width = 1.dp, color = borderColor),
        modifier = modifier,
        shape = CircleShape
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
        )
    }
}