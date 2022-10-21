package com.chuntian.composecookbookcopy.ui.home.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.utils.TestTags
import com.chuntian.data.DemoDataProvider
import com.chuntian.data.model.Item
import com.chuntian.theme.ComposeCookBookCopyTheme
//import com.chuntian.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorizontalListItem(item: Item, modifier: Modifier = Modifier) {
    Card(
//        shape = Shapes.medium,
        modifier = modifier
            .size(280.dp, 200.dp)
            .testTag("${TestTags.HOME_SCREEN_LIST_ITEM}-${item.id}")
    ) {
        Column(modifier = Modifier.clickable { }) {
            Image(
                painter = painterResource(id = item.imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.subtitle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(text = item.source, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Preview
@Composable
fun PreviewHorizontalListItem(){
    ComposeCookBookCopyTheme() {
        HorizontalListItem(item = DemoDataProvider.item)
    }
}