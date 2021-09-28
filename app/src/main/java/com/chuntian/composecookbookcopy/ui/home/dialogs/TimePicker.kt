package com.chuntian.composecookbookcopy.ui.home.dialogs

import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.chuntian.composecookbookcopy.utils.LocalThemeState
import com.chuntian.theme.ColorPallet
import com.chuntian.theme.R
import java.time.LocalDate

@Composable
fun DateTimePicker(onDateSelected: (LocalDate) -> Unit, onDismissRequest: () -> Unit) {
    val appThemeState = LocalThemeState.current
    val darkTheme = appThemeState.darkTheme
    val calendarStyle = when (appThemeState.pallet) {
        ColorPallet.GREEN -> if (darkTheme) R.style.CalendarDarkGreen else R.style.CalendarLightGreen
        ColorPallet.PURPLE -> if (darkTheme) R.style.CalendarDarkPurple else R.style.CalendarLightPurple
        ColorPallet.ORANGE -> if (darkTheme) R.style.CalendarDarkOrange else R.style.CalendarLightOrange
        ColorPallet.BLUE -> if (darkTheme) R.style.CalendarDarkBlue else R.style.CalendarLightBlue
    }
    Dialog(onDismissRequest = { onDismissRequest() }, properties = DialogProperties()) {
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = RoundedCornerShape(size = 4.dp)
                )
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(
                    color = MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
                )
            )
//            timepicker { time ->
//                // Do stuff with java.time.LocalTime object which is passed in
//            }
            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 16.dp, end = 16.dp)
            ) {
                TextButton(
                    onClick = onDismissRequest
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.button,
                        color = Color.Gray
                    )
                }

                TextButton(
                    onClick = {
//                        onDateSelected(selDate.value)
                        onDismissRequest()
                    }
                ) {
                    Text(
                        text = "OK",
                        style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.primary
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun PreviewTimePicker(){
    AndroidView(
        modifier = Modifier
            .wrapContentSize(),
        factory = { context ->
            TimePicker(ContextThemeWrapper(context, R.style.TimePicker)).apply {
                setIs24HourView(true)
            }
        },
        update = { view ->
        }
    )
}