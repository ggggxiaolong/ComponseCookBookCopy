package com.chuntian.composecookbookcopy.ui.home.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.utils.TestTags
import com.chuntian.data.DemoDataProvider
import com.chuntian.data.model.Item
import com.chuntian.theme.ComposeCookBookCopyTheme

@Composable
fun VerticalListItem(item: Item, modifier: Modifier = Modifier) {
    val typography = MaterialTheme.typography
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("${TestTags.HOME_SCREEN_LIST_ITEM}-${item.id}")
    ) {
        Image(
            painter = painterResource(id = item.imageId), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = item.title, style = typography.h6)
        Text(text = item.subtitle, style = typography.body2)
        Text(text = item.source, style = typography.subtitle2)
    }
}

@Preview
@Composable
fun PreviewListItem(){
    ComposeCookBookCopyTheme {
        VerticalListItem(item = DemoDataProvider.item)
    }
}
