package com.chuntian.composecookbookcopy.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.utils.Subtitle
import com.chuntian.data.R
import com.chuntian.demo.youtube.components.YoutubeChip

@Composable
fun Chips() {
    Text(
        text = "Custom Chip",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(8.dp)
    )
    Subtitle(subtitle = "Custom chips with surface")
    Row() {
        YoutubeChip(selected = true, text = "Chip", modifier = Modifier.padding(horizontal = 8.dp))
        YoutubeChip(
            selected = false,
            text = "Inactive",
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        CustomImageChip(text = "Custom", imageId = R.drawable.p2, selected = true)
        Spacer(modifier = Modifier.padding(8.dp))
        CustomImageChip(text = "Custom", imageId = R.drawable.p6, selected = false)
    }
}

@Composable
private fun CustomImageChip(
    text: String,
    imageId: Int,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val contentColor = when {
        selected -> MaterialTheme.colorScheme.onPrimary
        else -> Color.LightGray
    }
    Surface(
        color = when {
            selected -> MaterialTheme.colorScheme.primary
            else -> Color.Transparent
        },
        contentColor = contentColor,
        shape = CircleShape,
        border = BorderStroke(width = 1.dp, color = contentColor),
        modifier = modifier
    ) {
        Row {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(20.dp)
                    .clip(
                        CircleShape
                    )
            )
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}