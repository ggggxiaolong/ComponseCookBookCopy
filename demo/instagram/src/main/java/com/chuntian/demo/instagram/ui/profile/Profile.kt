package com.chuntian.demo.instagram.ui.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class ProfileSize(val size: Dp) {
    Small(20.dp), Medium(32.dp), Large(64.dp)
}

data class ProfileSectionSize(
    val profileIconSize: Dp,
    val textStyle: TextStyle,
)

object ProfileSectionSizes {
    @Composable
    fun small() = ProfileSectionSize(ProfileSize.Small.size, MaterialTheme.typography.labelMedium)

    @Composable
    fun medium() = ProfileSectionSize(ProfileSize.Medium.size, MaterialTheme.typography.bodyMedium)
}

@Composable
fun ProfilePicture(
    @DrawableRes imageId: Int,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    size: Dp = ProfileSize.Medium.size
) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = contentDescription,
        modifier = modifier
            .size(size)
            .clip(
                CircleShape
            ),
        contentScale = ContentScale.Crop
    )
}