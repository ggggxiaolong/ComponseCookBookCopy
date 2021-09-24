package com.chuntian.composecookbookcopy.ui.home.dialogs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chuntian.data.DemoDataProvider
import com.chuntian.theme.ComposeCookBookCopyTheme

class DialogActivity : ComponentActivity() {
    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DARK_THEME, false) ?: false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCookBookCopyTheme(isDarkTheme) {
                DialogScreen {
                    onBackPressed()
                }
            }
        }
    }

    companion object {
        private const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, isDarkTheme: Boolean) =
            Intent(context, DialogActivity::class.java).apply {
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}

@Composable
fun DialogScreen(onBack: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Dialogs") },
            elevation = 8.dp,
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            })
    }, content = {
        DialogsOptionList()
    })
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
            DialogType.ROUNDED to "Extra round dialog"
        )
        for (item in dialogTypes) {
            Button(
                onClick = { dialogState = DialogState(true, item.first) }, modifier = Modifier
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