package com.chuntian.demo.instagram.ui.posts

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chuntian.data.DemoDataProvider
import com.chuntian.data.model.Tweet
import com.chuntian.demo.instagram.ui.profile.ProfileSection
import com.chuntian.demo.instagram.ui.profile.ProfileSectionSizes
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons

@Composable
fun PostItem(
    post: Tweet,
    isLiked: Boolean,
    onLickedClick: () -> Unit = {},
    onCommentsClicked: () -> Unit = {},
    onSendClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ProfileSection(
            imageId = post.authorImageId,
            text = post.author,
            size = ProfileSectionSizes.medium(),
            iconRight = {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "See moew options")
            }
        )
        PostImage(
            imageId = post.tweetImageId,
            contentDescription = post.text,
            modifier = Modifier.padding(top = 8.dp)
        )
        PostInteractionBar(
            isLiked = isLiked,
            onLickedClick = onLickedClick,
            onCommentsClicked = onCommentsClicked,
            onSendClick = onSendClick
        )
        ProfileSection(
            imageId = post.authorImageId,
            text = "Liked by ${DemoDataProvider.tweet.author} and ${DemoDataProvider.tweet.likesCount} others"
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = "View all ${post.commentsCount} comments",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = LocalContentAlpha.current)
            )
            Text(
                text = "${post.time} ago",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = LocalContentAlpha.current)
            )
        }
    }
}

@Composable
fun PostImage(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int,
    contentDescription: String? = null,
) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = contentDescription,
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun PostInteractionBar(
    isLiked: Boolean, onLickedClick: () -> Unit,
    onCommentsClicked: () -> Unit, onSendClick: () -> Unit, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        IconToggleButton(checked = isLiked, onCheckedChange = { onLickedClick() }) {
            val icon = if (isLiked) FaIcons.Heart else FaIcons.HeartRegular
            val tint = if (isLiked) Color.Red else MaterialTheme.colorScheme.onSurface
            FaIcon(faIcon = icon, tint = tint)
        }
        IconToggleButton(checked = false, onCheckedChange = { onCommentsClicked() }) {
            FaIcon(faIcon = FaIcons.CommentAltRegular, tint = MaterialTheme.colorScheme.onSurface)
        }
        IconToggleButton(checked = false, onCheckedChange = { onSendClick() }) {
            FaIcon(faIcon = FaIcons.PaperPlaneRegular, tint = MaterialTheme.colorScheme.onSurface)
        }
    }
}