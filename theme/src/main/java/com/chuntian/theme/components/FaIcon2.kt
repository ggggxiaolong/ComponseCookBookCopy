package com.chuntian.theme.components

import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIconType

@Composable
fun FaIcon2(
    faIcon: FaIconType,
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
) = FaIcon(faIcon, modifier, size, tint)