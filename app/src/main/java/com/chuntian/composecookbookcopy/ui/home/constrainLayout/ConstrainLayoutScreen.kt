package com.chuntian.composecookbookcopy.ui.home.constrainLayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.chuntian.data.DemoDataProvider

@Composable
fun ConstrainLayoutScreen(onBack: () -> Unit) {
    HomeScaffold(title = "ConstrainLayout", onBack = onBack) {
        Column(modifier = Modifier
            .padding(it)
            .verticalScroll(rememberScrollState())) {
            ConstrainLayoutListItems()
            Spacer(modifier = Modifier.height(20.dp))
            ConstrainLayoutBigListItem()
        }
    }
}

@Composable
fun ConstrainLayoutListItems() {
    val isSelect = remember { mutableStateOf(true) }
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .clickable { }
        .padding(12.dp)) {
        val item = DemoDataProvider.item
        val (image, title, subtitle, space, source, button) = createRefs()
        createVerticalChain(title, subtitle, space, source, chainStyle = ChainStyle.Packed)

        Image(
            painter = painterResource(id = item.imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .constrainAs(image) {
                    linkTo(
                        top = parent.top,
                        topMargin = 16.dp,
                        bottom = parent.bottom,
                        bottomMargin = 16.dp
                    )
                    linkTo(start = parent.start, end = title.start)
                })

        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(title) {
                linkTo(
                    start = image.end,
                    startMargin = 16.dp,
                    end = button.start,
                    endMargin = 16.dp
                )
            }
        )
        IconButton(
            onClick = { isSelect.value = !isSelect.value },
            modifier = Modifier.constrainAs(button) {
                linkTo(top = title.top, bottom = title.bottom)
                linkTo(start = title.end, end = parent.end, endMargin = 8.dp)
            }) {
            Icon(
                imageVector = if (isSelect.value) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = item.subtitle,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(subtitle) {
                linkTo(start = title.start, end = parent.end)
                width = Dimension.fillToConstraints
            })
        Spacer(modifier = Modifier
            .height(8.dp)
            .constrainAs(space) {
                linkTo(top = subtitle.bottom, bottom = source.top)
            })
        Text(
            text = item.source,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.constrainAs(source) {
                start.linkTo(title.start)
                width = Dimension.fillToConstraints
            })
    }
}

@Preview
@Composable
fun PreviewConstrainLayoutLitItem() {
    ConstrainLayoutListItems()
}

@Composable
fun ConstrainLayoutBigListItem() {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .clickable { }) {
        val item = DemoDataProvider.item
        val (image, title, subtitle, button) = createRefs()
        val isSelect = remember { mutableStateOf(false) }
        Image(painter = painterResource(id = item.imageId),
            contentScale = ContentScale.Crop,
            contentDescription = null, modifier = Modifier
                .height(200.dp)
                .constrainAs(image) {
                    linkTo(start = parent.start, end = parent.end)
                    width = Dimension.fillToConstraints
                })
        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(title) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    startMargin = 16.dp,
                    endMargin = 16.dp
                )
                linkTo(top = image.bottom, topMargin = 8.dp, bottom = subtitle.top)
                width = Dimension.fillToConstraints
            })
        Text(
            text = item.subtitle,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.constrainAs(subtitle) {
                linkTo(start = title.start, end = title.end)
                linkTo(
                    top = title.bottom,
                    topMargin = 8.dp,
                    bottom = parent.bottom,
                    bottomMargin = 16.dp
                )
                width = Dimension.fillToConstraints
            })
        IconButton(
            onClick = { isSelect.value = !isSelect.value },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 8.dp)
                end.linkTo(parent.end, margin = 8.dp)
            }) {
            Icon(
                imageVector = if (isSelect.value) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
fun PreviewConstrainBigItem() {
    ConstrainLayoutBigListItem()
}