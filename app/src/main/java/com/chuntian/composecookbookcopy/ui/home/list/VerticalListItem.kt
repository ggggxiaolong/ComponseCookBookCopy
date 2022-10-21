package com.chuntian.composecookbookcopy.ui.home.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
//import com.chuntian.theme.Shapes

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
                .clip(shape = CardDefaults.shape)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = item.title, style = typography.titleLarge)
        Text(text = item.subtitle, style = typography.bodyMedium)
        Text(text = item.source, style = typography.titleMedium)
    }
}

@Preview
@Composable
fun PreviewListItem(){
    ComposeCookBookCopyTheme {
        VerticalListItem(item = DemoDataProvider.item)
    }
}
