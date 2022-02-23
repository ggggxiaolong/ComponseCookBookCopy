package com.chuntian.composecookbookcopy.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toggles() {
    Text(
        text = "Toggle, Switch, Sliders",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(8.dp)
    )

    var checked by remember { mutableStateOf(true) }
    var switched by remember { mutableStateOf(true) }
    val modifier = Modifier.padding(8.dp)
    Row {
        Checkbox(checked = checked, onCheckedChange = { checked = !checked }, modifier = modifier)
        Switch(
            checked = switched, onCheckedChange = { switched = !switched }, modifier = modifier,
            colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.primary)
        )
    }
    var selectedIndex by remember { mutableStateOf(0) }
    val items = listOf("Kotlin", "Java", "Switch")
    Row(verticalAlignment = Alignment.CenterVertically) {
        items.forEachIndexed { index, item ->
            RadioButton(selected = index == selectedIndex, onClick = { selectedIndex = index })
            Text(text = item, modifier = Modifier
                .clickable { selectedIndex = index })
            if (index < items.size - 1) {
                Spacer(modifier = Modifier.size(4.dp))
            }
        }
    }
    var sliderState by remember { mutableStateOf(0f) }
    Slider(
        value = sliderState, onValueChange = { sliderState = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.primary,
            activeTrackColor = MaterialTheme.colorScheme.primary,
        ),
    )
    var sliderState2 by remember { mutableStateOf(20f) }
    Slider(
        value = sliderState2,
        onValueChange = { sliderState2 = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        valueRange = 0f..100f,
        steps = 5,
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.primary,
            activeTrackColor = MaterialTheme.colorScheme.primary,
        ),
    )
}