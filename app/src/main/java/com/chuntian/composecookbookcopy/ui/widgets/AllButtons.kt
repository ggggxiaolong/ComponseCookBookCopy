package com.chuntian.composecookbookcopy.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AllButtonsView() {
    Text(
        text = "Buttons",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(8.dp)
    )

    Row {
        Button(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Main Button")
        }
        TextButton(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Text Button")
        }
        TextButton(onClick = {}, modifier = Modifier.padding(8.dp), enabled = false) {
            Text(text = "Disabled")
        }
    }

    Row {
        Button(onClick = {}, modifier = Modifier.padding(8.dp), enabled = false) {
            Text(text = "Disabled")
        }
        Button(
            onClick = {},
            modifier = Modifier.padding(8.dp),
            elevation = ButtonDefaults.buttonElevation()
        ) {
            Text(text = "Flat")
        }
        Button(onClick = {}, modifier = Modifier.padding(8.dp), shape = RoundedCornerShape(12.dp)) {
            Text(text = "Rounded")
        }
    }

    Row {
        OutlinedButton(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Outline")
        }
        Button(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(text = "Icon")
        }
        Button(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Icon")
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }

    Row {
        OutlinedButton(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Outline colors")
        }
        Button(onClick = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = "Custom colors")
        }
    }

    Row {
        val horizontalGradient = Brush.horizontalGradient(
            colors = listOf(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.inversePrimary
            ),
            0f, 250f
        )

        val verticalGradient = Brush.verticalGradient(
            colors = listOf(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.inversePrimary
            ),
            startY = 0f,
            endY = 100f,
        )

        Text(
            text = "Horizontal gradient", style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(12.dp)
                .clickable {}
                .clip(CircleShape)
                .background(brush = horizontalGradient)
                .padding(12.dp)
        )

        Text(
            text = "Vertical gradient", style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(12.dp)
                .clickable {}
                .clip(CircleShape)
                .background(brush = verticalGradient)
                .padding(12.dp)
        )
    }
    Row {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Image,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        IconButton(onClick = {}) {
            Row {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
    val swipeButtonState = remember { mutableStateOf(SwipeButtonState.Initial) }
    val scope = rememberCoroutineScope()
    SwipeButton(
        onSwiped = {
            swipeButtonState.value = SwipeButtonState.Swiped
            scope.launch {
                delay(2000)
                swipeButtonState.value = SwipeButtonState.Collapsed
                delay(3000)
                swipeButtonState.value = SwipeButtonState.Initial
            }
        },
        swipeButtonState = swipeButtonState.value
    ) {
        Text(text = "Pay Now", style = MaterialTheme.typography.labelLarge)
    }
    Spacer(modifier = Modifier.height(16.dp))
    SwipeButton(
        onSwiped = {
            swipeButtonState.value = SwipeButtonState.Swiped
            scope.launch {
                delay(2000)
                swipeButtonState.value = SwipeButtonState.Collapsed
                delay(3000)
                swipeButtonState.value = SwipeButtonState.Initial
            }
        },
        swipeButtonState = SwipeButtonState.Initial,
        enabled = false
    ) {
        Text(text = "Pay Now", style = MaterialTheme.typography.labelLarge)
    }
}

@Preview
@Composable
fun AllButtonPreview() {
    Column {
        AllButtonsView()
    }
}