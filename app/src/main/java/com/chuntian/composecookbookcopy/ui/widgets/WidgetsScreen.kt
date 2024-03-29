package com.chuntian.composecookbookcopy.ui.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.utils.TestTags

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WidgetsScreen() {
    Scaffold(
        modifier = Modifier.testTag(TestTags.WIDGET_SCREEN_ROOT),
        topBar = { TopAppBar(title = { Text(text = "All Widgets") }) },
        backgroundColor = MaterialTheme.colorScheme.surface,
    ) {
        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = it.calculateBottomPadding())
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