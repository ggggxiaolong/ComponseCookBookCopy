package com.chuntian.composecookbookcopy.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.utils.Subtitle
import com.chuntian.data.DemoDataProvider
import com.chuntian.data.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun UICards() {
    Text(
        text = "UI Cards, Boxes and Lists",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(8.dp)
    )
    val item = DemoDataProvider.item

    Subtitle("Inbuilt box as container for any CLipping/Alignment controls")

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
//        containerColor = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp),
//        contentColor = ,
//        colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.onPrimary, containerColor = ),
    ) {
        Column {
            Text(
                text = item.title,
                modifier = Modifier.padding(8.dp),
//                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = item.subtitle,
                modifier = Modifier.padding(8.dp),
//                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
    Divider()
    Subtitle("Inbuilt Card")
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.p3),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Text(text = item.title, modifier = Modifier.padding(16.dp))
        }
    }
    Divider()
    Subtitle(subtitle = "In-built ListItems")
    ListItem(text = { Text(text = item.title) }, secondaryText = { Text(text = item.subtitle) })
    Divider()
    ListItem(
        text = { Text(text = item.title) },
        secondaryText = { Text(text = item.subtitle) },
        singleLineSecondaryText = false
    )
    ListItem(
        text = { Text(text = item.title, style = MaterialTheme.typography.titleMedium) },
        secondaryText = { Text(text = item.subtitle, style = MaterialTheme.typography.bodyMedium) },
        icon = {
            Image(
                painter = painterResource(id = item.imageId),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop,
            )
        })
    Divider()
    ListItem(
        text = { Text(text = item.title, style = MaterialTheme.typography.titleMedium) },
        secondaryText = { Text(text = item.subtitle, style = MaterialTheme.typography.bodyMedium) },
        icon = {
            Image(
                painter = painterResource(id = item.imageId),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop,
            )
        },
        overlineText = { Text(text = "OverLine text", textDecoration = TextDecoration.Underline) },
        singleLineSecondaryText = false
    )
    Divider()
    ListItem(
        text = { Text(text = item.title, style = MaterialTheme.typography.titleMedium) },
        secondaryText = { Text(text = item.subtitle, style = MaterialTheme.typography.bodyMedium) },
        icon = {
            Image(
                painter = painterResource(id = item.imageId),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop,
            )
        },
        overlineText = { Text(text = "OverLine text", textDecoration = TextDecoration.Underline) },
        trailing = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null) },
        singleLineSecondaryText = false
    )
}

@Preview
@Composable
fun PreviewUICards() {
    Column {
        UICards()
    }
}