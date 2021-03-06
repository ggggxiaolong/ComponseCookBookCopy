package com.chuntian.composecookbookcopy.ui.templates.payCard

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

private val creditCardOffsetTranslator = object : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        return when {
            offset <= 3 -> offset
            offset <= 7 -> offset + 1
            offset <= 11 -> offset + 2
            offset < 16 -> offset + 3
            else -> 19
        }
    }

    override fun transformedToOriginal(offset: Int): Int {
        return when {
            offset <= 4 -> offset
            offset <= 9 -> offset - 1
            offset <= 14 -> offset - 2
            offset <= 19 -> offset - 3
            else -> 16
        }
    }
}

val CreditCardFilter = VisualTransformation { text ->
    val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 4 == 3 && i != 5) out += " "
    }
    TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}