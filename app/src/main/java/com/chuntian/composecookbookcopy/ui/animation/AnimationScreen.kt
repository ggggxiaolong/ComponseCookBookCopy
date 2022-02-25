package com.chuntian.composecookbookcopy.ui.animation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.utils.RotateIcon
import com.chuntian.composecookbookcopy.utils.TestTags
import com.chuntian.composecookbookcopy.utils.TitleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimationScreen() {
    var animateIcon by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            SmallTopAppBar(modifier = Modifier.testTag(TestTags.ANIM_SCREEN_ROOT),
                title = { Text(text = "Animations") },
                navigationIcon = {
                    IconButton(onClick = { animateIcon = !animateIcon }) {
                        RotateIcon(
                            state = animateIcon,
                            asset = Icons.Filled.PlayArrow,
                            angle = 1440f,
                            duration = 3000,
                        )
                    }
                })
        }) {
        LazyColumn(
            state = rememberLazyListState(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Spacer(modifier = Modifier.size(16.dp)) }
            item { TitleText(title = "State Animations(Fire and forget)") }
            item { AnimationsForStates() }
            item { VisibilityAnimate() }
            item { AnimateSuspendedAnimations() }
            item { TransitionAnimate() }
        }
    }
}