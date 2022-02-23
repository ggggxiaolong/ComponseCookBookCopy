package com.chuntian.composecookbookcopy.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.utils.Subtitle

@Composable
fun TextDemo() {
    Text(
        text = "Text View",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(8.dp)
    )
    Subtitle(subtitle = "font weights")
    val textModifier = Modifier.padding(horizontal = 8.dp)
    Row {
        Text(text = "Plain", modifier = textModifier)
        Text(
            text = "Medium Bold",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            modifier = textModifier,
        )
        Text(
            text = "Bold",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = textModifier,
        )
        Text(
            text = "Extra Bold",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = textModifier,
        )
    }
    Subtitle(subtitle = "font family dynamic")
    Row {
        Text(text = "Default", modifier = textModifier)
        Text(
            text = "Cursive",
            style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily.Cursive),
            modifier = textModifier,
        )
        Text(
            text = "SansSerif",
            style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily.SansSerif),
            modifier = textModifier,
        )
        Text(
            text = "Monospace",
            style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily.Monospace),
            modifier = textModifier,
        )
    }
    Subtitle(subtitle = "Text decorations")
    Row {
        Text(text = "Default", modifier = textModifier)
        Text(text = "Underline", textDecoration = TextDecoration.Underline, modifier = textModifier)
        Text(
            text = "LineThrough",
            textDecoration = TextDecoration.LineThrough,
            modifier = textModifier
        )
        Text(
            text = "Monospace", textDecoration = TextDecoration.combine(
                listOf(
                    TextDecoration.Underline,
                    TextDecoration.LineThrough
                )
            ), modifier = textModifier
        )
    }
    Subtitle(subtitle = "Overflow")
    Text(
        text = "Ellipsis: This text is supposed to ellipsis with max 1 line allowed for this.",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = textModifier
    )
    Text(
        text = "Clip: This text is supposed to ellipsis with max 1 line allowed for this.",
        maxLines = 1,
        overflow = TextOverflow.Clip,
        modifier = textModifier
    )
}

@Preview
@Composable
fun PreviewTextDemo() {
    Column {
        TextDemo()
    }
}