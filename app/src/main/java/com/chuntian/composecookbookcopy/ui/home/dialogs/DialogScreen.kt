package com.chuntian.composecookbookcopy.ui.home.dialogs

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.chuntian.data.DemoDataProvider
import com.chuntian.theme.ComposeCookBookCopyTheme
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import timber.log.Timber
import java.util.*

@Composable
fun DialogScreen(onBack: () -> Unit) {
    HomeScaffold(title = "Dialogs", onBack = onBack) { DialogsOptionList() }
}

@Composable
fun ShowDialog(type: DialogType, onDismiss: () -> Unit) {
    val item = remember { DemoDataProvider.item }
    when (type) {
        DialogType.SIMPLE -> AlertDialog(
            onDismissRequest = onDismiss,
            text = { Text(text = item.subtitle) },
            buttons = {
                TextButton(
                    onClick = onDismiss, modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Ok")
                }
            })
        DialogType.TITLE -> AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = item.title, style = MaterialTheme.typography.h6) },
            text = { Text(text = item.subtitle) },
            buttons = {
                Row(modifier = Modifier) {
                    TextButton(onClick = onDismiss, modifier = Modifier.padding(4.dp)) {
                        Text(text = "Cancel", color = Color.Gray)
                    }
                    TextButton(onClick = onDismiss, modifier = Modifier.padding(4.dp)) {
                        Text(text = "Ok")
                    }
                }
            }
        )
        DialogType.VERTICAL_BUTTON -> AlertDialog(onDismissRequest = onDismiss,
            title = { Text(text = item.title, style = MaterialTheme.typography.h6) },
            text = { Text(text = item.subtitle) },
            buttons = {
                OutlinedButton(
                    onClick = onDismiss, modifier = Modifier
                        .padding(8.dp)
                        .width(100.dp)
                ) {
                    Text(text = "Cancel")
                }
                Button(
                    onClick = onDismiss, modifier = Modifier
                        .padding(8.dp)
                        .width(100.dp)
                ) {
                    Text(text = "Ok")
                }
            })
        DialogType.IMAGE -> AlertDialog(onDismissRequest = onDismiss,
            title = { Text(text = item.title, style = MaterialTheme.typography.h6) },
            text = {
                Column {
                    Text(text = item.subtitle, modifier = Modifier.padding(bottom = 8.dp))
                    Image(painter = painterResource(id = item.imageId), contentDescription = null)
                }
            },
            buttons = {
                TextButton(onClick = onDismiss, modifier = Modifier.padding(8.dp)) {
                    Text(text = "Ok")
                }
            }
        )
        DialogType.LONG_DIALOG ->
            AlertDialog(
                title = { Text(text = item.title, style = MaterialTheme.typography.h6) },
                text = {
                    Column {
                        Text(text = item.subtitle, modifier = Modifier.padding(8.dp))
                        Image(
                            painter = painterResource(id = item.imageId),
                            contentDescription = null
                        )
                        Text(
                            text = item.subtitle + item.title + item.subtitle + item.title,
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                },
                buttons = {
                    TextButton(onClick = onDismiss, modifier = Modifier.padding(8.dp)) {
                        Text(text = "Ok")
                    }
                },
                onDismissRequest = onDismiss,
            )
        DialogType.ROUNDED -> AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = item.title, style = MaterialTheme.typography.h6) },
            text = {
                Column {
                    Text(text = item.subtitle, modifier = Modifier.padding(bottom = 8.dp))
                    Image(painter = painterResource(id = item.imageId), contentDescription = null)
                }
            },
            buttons = {
                TextButton(onClick = onDismiss, modifier = Modifier.padding(8.dp)) {
                    Text(text = "Ok")
                }
            },
            shape = RoundedCornerShape(16.dp)
        )
        DialogType.DATE_PICKER -> {
            val activity = LocalContext.current as AppCompatActivity
            val picker = MaterialDatePicker.Builder.datePicker().build()
            picker.show(activity.supportFragmentManager, "time_picker")
            picker.addOnPositiveButtonClickListener {
                Timber.i(Date(it).toString())
            }
            picker.addOnDismissListener {
                onDismiss()
            }
        }
        DialogType.TIME_PICKER -> {
            val activity = LocalContext.current as AppCompatActivity
            val picker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()
            picker.show(activity.supportFragmentManager, "time_picker")
            picker.addOnPositiveButtonClickListener {
                Timber.i("${picker.hour}:${picker.minute}")
            }
            picker.addOnDismissListener {
                onDismiss()
            }
        }
    }
}

@Composable
fun DialogsOptionList() {
    var dialogState by remember { mutableStateOf(DialogState(false, DialogType.SIMPLE)) }

    if (dialogState.showDialog) {
        ShowDialog(type = dialogState.dialogType) {
            dialogState = dialogState.copy(showDialog = false)
        }
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        val dialogTypes = listOf(
            DialogType.SIMPLE to "Plain Message Dialog",
            DialogType.TITLE to "Title Dialog",
            DialogType.VERTICAL_BUTTON to "Dialog with Vertical buttons",
            DialogType.IMAGE to "Dialog with Image",
            DialogType.LONG_DIALOG to "Long Dialog",
            DialogType.ROUNDED to "Extra round dialog",
            DialogType.DATE_PICKER to "Date Picker",
            DialogType.TIME_PICKER to "Time Picker",
        )
        for (item in dialogTypes) {
            Button(
                onClick = { dialogState = DialogState(true, item.first) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = item.second)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ComposeCookBookCopyTheme {
        DialogsOptionList()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPicker() {
    ComposeCookBookCopyTheme {
        CalendarPicker({ _ -> }, {})
    }
}