package com.chuntian.composecookbookcopy.ui.templates.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CascadeMenuScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    var isOpen by remember { mutableStateOf(false) }
    val items = getMenu()
    val scope = rememberCoroutineScope()
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }, topBar = {
        SmallTopAppBar(title = { Text(text = "Menu") }, actions = {
            IconButton(onClick = { isOpen = true }) {
                Icon(imageVector = Icons.TwoTone.MoreVert, contentDescription = null)
            }
            CascadeMenu(
                isOpen = isOpen, menu = items,
                onDismiss = { isOpen = false },
                onItemSelected = { item ->
                    isOpen = false
                    scope.launch {
                        snackbarHostState.showSnackbar(item.title)
                    }
                },
                offset = DpOffset(8.dp, 0.dp)
            )
        })
    }) {
    }
}

fun getMenu(): CascadeMenuItem<String> {
    val menu = cascadeMenu<String> {
        item("about", "About") {
            icon(Icons.TwoTone.Language)
        }
        item("copy", "Copy") {
            icon(Icons.TwoTone.FileCopy)
        }
        item("share", "Share") {
            icon(Icons.TwoTone.Share)
            item("to_clipboard", "To clipboard") {
                item("pdf", "PDF")
                item("epub", "EPUB")
                item("web_page", "Web page")
                item("microsoft_word", "Microsoft word")
            }
            item("as_a_file", "As a file") {
                item("pdf", "PDF")
                item("epub", "EPUB")
                item("web_page", "Web page")
                item("microsoft_word", "Microsoft word")
            }
        }
        item("remove", "Remove") {
            icon(Icons.TwoTone.DeleteSweep)
            item("yep", "Yep") {
                icon(Icons.TwoTone.Done)
            }
            item("go_back", "Go back") {
                icon(Icons.TwoTone.Close)
            }
        }
    }
    return menu
}