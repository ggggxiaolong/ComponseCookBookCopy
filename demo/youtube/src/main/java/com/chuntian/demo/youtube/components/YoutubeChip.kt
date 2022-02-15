package com.chuntian.demo.youtube.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun YoutubeChip(selected: Boolean, text: String, modifier: Modifier = Modifier) {
    val isLight = isSystemInDarkTheme()
    val (color, contentColor, borderColor) = if (selected) {
        Triple(
            MaterialTheme.colorScheme.onSurface.copy(alpha = if (isLight) 0.7f else 1f),
            MaterialTheme.colorScheme.surface,
            MaterialTheme.colorScheme.surface
        )
    } else {
        Triple(
            MaterialTheme.colorScheme.onSurface.copy(alpha = if (isLight) 0.04f else 0.07f),
            MaterialTheme.colorScheme.onSurface,
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
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
        )
    }
}