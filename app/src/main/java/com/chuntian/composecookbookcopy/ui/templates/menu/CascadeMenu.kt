package com.chuntian.composecookbookcopy.ui.templates.menu

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.material.icons.rounded.ArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

val MAX_WIDTH = 192.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun <T : Any> CascadeMenu(
    modifier: Modifier = Modifier,
    isOpen: Boolean,
    menu: CascadeMenuItem<T>,
    colors: MenuItemColors = MenuDefaults.itemColors(),
    offset: DpOffset = DpOffset.Zero,
    onItemSelected: (CascadeMenuItem<T>) -> Unit,
    onDismiss: () -> Unit,
) {
    DropdownMenu(
        expanded = isOpen, onDismissRequest = onDismiss,
        modifier = modifier.width(MAX_WIDTH),
        offset = offset,
    ) {
        val menuState = remember { mutableStateOf(menu) }
        AnimatedContent(targetState = menuState.value, transitionSpec = {
            if (initialState == targetState.parent) {
                ContentTransform(
                    targetContentEnter = slideIntoContainer(AnimatedContentScope.SlideDirection.Right),
                    initialContentExit = slideOutOfContainer(AnimatedContentScope.SlideDirection.Right)
                )
            } else {
                ContentTransform(
                    targetContentEnter = slideIntoContainer(AnimatedContentScope.SlideDirection.Left),
                    initialContentExit = slideOutOfContainer(AnimatedContentScope.SlideDirection.Left)
                )
            }
        }) { targetState ->
            CascadeMenuContent(
                menuState = menuState,
                targetMenu = targetState,
                onItemSelected = onItemSelected,
                colors = colors
            )
        }
    }
}

@Composable
fun <T : Any> CascadeMenuContent(
    menuState: MutableState<CascadeMenuItem<T>>,
    targetMenu: CascadeMenuItem<T>,
    onItemSelected: (CascadeMenuItem<T>) -> Unit,
    colors: MenuItemColors,
) {
    Column(modifier = Modifier.width(MAX_WIDTH)) {
        if (targetMenu.hasParent()) {
            CascadeHeaderItem(
                targetMenu.title,
                colors = colors,
                onClick = { menuState.value = targetMenu.parent!! })
        }
        if (targetMenu.hasChildren()) {
            val children = targetMenu.children!!
            for (child in children) {
                if (child.hasChildren()) {
                    CascadeParentItem(
                        item = child,
                        onClick = {
                            menuState.value = child
                        },
                        colors = colors
                    )
                } else {
                    CascadeChildren(item = child, onClick = onItemSelected, colors = colors)
                }
            }
        }
    }
}

@Composable
fun CascadeHeaderItem(title: String, onClick: () -> Unit, colors: MenuItemColors) {
    DropdownMenuItem(
        text = { Text(text = title, style = MaterialTheme.typography.titleMedium) },
        onClick = onClick,
        leadingIcon = { Icon(imageVector = Icons.Rounded.ArrowLeft, contentDescription = null) },
        colors = colors
    )
}

@Composable
fun <T : Any> CascadeParentItem(
    item: CascadeMenuItem<T>,
    onClick: (CascadeMenuItem<T>) -> Unit,
    colors: MenuItemColors
) {
    DropdownMenuItem(
        text = {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium
            )
        },
        onClick = { onClick(item) },
        leadingIcon = {
            if (item.icon != null) {
                Icon(imageVector = item.icon!!, contentDescription = null)
            }
        },
        trailingIcon = { Icon(imageVector = Icons.Rounded.ArrowRight, contentDescription = null) },
        colors = colors,
    )
}

@Composable
fun <T : Any> CascadeChildren(
    item: CascadeMenuItem<T>,
    colors: MenuItemColors,
    onClick: (CascadeMenuItem<T>) -> Unit
) {
    DropdownMenuItem(
        text = {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium
            )
        },
        onClick = { onClick(item) },
        leadingIcon = {
            if (item.icon != null) {
                Icon(imageVector = item.icon!!, contentDescription = null)
            }
        },
        colors = colors,
    )
}