package com.chuntian.composecookbookcopy.ui.templates.payCard

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.chuntian.theme.helper.TextFieldDefaultsMaterial

@Composable
fun InputItem(
    textFieldValue: String,
    label: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    onFocus: () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = textFieldValue,
        onValueChange = onTextChanged,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.Next),
        textStyle = textStyle,
        maxLines = 1,
        singleLine = true,
        label = {
            Text(text = label, style = MaterialTheme.typography.labelMedium)
        },
        colors = TextFieldDefaultsMaterial.outlinedTextFieldColors(),
        modifier = modifier.onFocusChanged {
            if (it.isFocused) {
                onFocus()
            }
        },
        visualTransformation = visualTransformation
    )
}