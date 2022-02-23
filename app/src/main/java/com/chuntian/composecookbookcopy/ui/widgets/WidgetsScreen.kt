package com.chuntian.composecookbookcopy.ui.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.utils.TestTags
import com.google.accompanist.insets.ExperimentalAnimatedInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.rememberImeNestedScrollConnection

@OptIn(ExperimentalAnimatedInsets::class)
@Composable
fun WidgetsScreen() {
    Scaffold(modifier = Modifier.testTag(TestTags.WIDGET_SCREEN_ROOT), topBar = {
        SmallTopAppBar(title = { Text(text = "All Widgets") })
    }) {
        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier
                .padding(horizontal = 8.dp)
        ) {
            item { AllButtonsView() }
            item { Chips() }
            item { TextDemo() }
            item { TextInputs() }
            item { Loaders() }
            item { Toggles() }
            item { Appbars() }
            item { SnackBars() }
            item { UICards() }
        }
    }
}