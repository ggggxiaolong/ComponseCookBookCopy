package com.chuntian.composecookbookcopy.ui.templates.menu

import androidx.compose.ui.graphics.vector.ImageVector

class CascadeMenuBuilder<T : Any> {
    var menu = CascadeMenuItem<T>()

    fun icon(value: ImageVector) {
        menu.icon = value
    }

    fun item(id: T, title: String, init: (CascadeMenuBuilder<T>.() -> Unit)? = null) {
        val menuBuilder = CascadeMenuBuilder<T>()
        val children = menuBuilder.menu.apply {
            this.id = id
            this.title = title
        }
        if (init != null) {
            menuBuilder.init()
        }
        menu.children = menu.children ?: mutableListOf()
        children.parent = menu
        menu.children!!.add(children)
    }
}

fun <T : Any> cascadeMenu(init: CascadeMenuBuilder<T>.() -> Unit): CascadeMenuItem<T> {
    val menuBuilder = CascadeMenuBuilder<T>()
    menuBuilder.init()
    return menuBuilder.menu
}