package com.chuntian.demo.instagram.ui.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileSection(
    @DrawableRes imageId: Int,
    text: String,
    modifier: Modifier = Modifier,
    iconRight: @Composable () -> Unit = {},
    size: ProfileSectionSize = ProfileSectionSizes.small()
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        ProfilePicture(imageId = imageId, size = size.profileIconSize)
        Text(
            text = text,
            style = size.textStyle,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )
        iconRight()
    }
}