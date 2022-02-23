package com.chuntian.composecookbookcopy.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SnackBars() {
    Text(
        text = "SnackBars",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(8.dp)
    )
    Snackbar(modifier = Modifier.padding(4.dp)) {
        Text(text = "This is a basic SnackBar with action item")
    }
    Snackbar(modifier = Modifier.padding(4.dp), action = {
        TextButton(onClick = {}) {
            Text(text = "Remove")
        }
    }) {
        Text(text = "This is a basic SnackBar with action item")
    }
    Snackbar(modifier = Modifier.padding(4.dp),
        actionOnNewLine = true,
        action = {
            TextButton(onClick = {}) {
                Text(text = "Remove")
            }
        }) {
        Text(text = "SnackBar with action item bellow ext")
    }
}

@Composable
@Preview
fun PreviewSnackBar() {
    Column {
        SnackBars()
    }
}