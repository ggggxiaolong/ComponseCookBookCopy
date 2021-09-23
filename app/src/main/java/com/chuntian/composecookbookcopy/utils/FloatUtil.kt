package com.chuntian.composecookbookcopy.utils

import android.text.TextUtils

fun String.toFloatNum(): Float {
    return if (isEmpty() || (length == 1 && TextUtils.equals(this, "."))) {
        0f
    } else {
        toFloat()
    }
}