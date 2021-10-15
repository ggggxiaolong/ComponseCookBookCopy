package com.chuntian.composecookbookcopy.ui.home.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chuntian.data.DemoDataProvider
import com.chuntian.data.model.Item
import com.chuntian.theme.ComposeCookBookCopyTheme

@Composable
fun VerticalListItemSmall(item: Item, modifier: Modifier = Modifier) {
    val typography = MaterialTheme.typography
    Row(modifier = modifier
        .clickable { }
        .padding(16.dp)) {
        ItemImage(item = item, Modifier.padding(end = 16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.title, style = typography.subtitle1)
            Text(text = item.subtitle, style = typography.body2)
        }
        FavIcon(modifier)
    }
}

@Composable
fun FavIcon(modifier: Modifier = Modifier) {
    val isFavorite = remember { mutableStateOf(true) }
    IconToggleButton(
        checked = isFavorite.value,
        onCheckedChange = { isFavorite.value = !isFavorite.value }) {
        Icon(
            imageVector = if (isFavorite.value) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null,
            modifier = modifier,
            tint = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun ItemImage(item: Item, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = item.imageId), contentDescription = null,
        modifier = modifier
            .size(100.dp, 80.dp)
            .clip(MaterialTheme.shapes.medium),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun PreviewVerticalSmall(){
    ComposeCookBookCopyTheme() {
        VerticalListItemSmall(item = DemoDataProvider.item)
    }
}