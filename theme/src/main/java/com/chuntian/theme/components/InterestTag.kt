package com.chuntian.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun InterestTag(
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    shape: Shape = RoundedCornerShape(4.dp),
    style: TextStyle = MaterialTheme.typography.labelMedium,
    onClick: () -> Unit = {}
) {
    Text(
        text = text,
        color = contentColor,
        style = style,
        modifier = modifier
            .padding(4.dp)
            .clickable(onClick = onClick)
            .clip(shape = shape)
            .background(containerColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}