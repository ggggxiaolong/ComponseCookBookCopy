package com.chuntian.composecookbookcopy.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import java.net.PasswordAuthentication

@Composable
fun TextInputs() {
    Text(
        text = "Text Inputs",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(8.dp)
    )
    var text by remember { mutableStateOf("") }
    val textModifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
    TextField(
        value = text,
        onValueChange = { v -> text = v },
        modifier = textModifier,
        label = { Text(text = "Label") },
        placeholder = { Text(text = "placeholder") }
    )

    OutlinedTextField(
        value = text,
        onValueChange = { v -> text = v },
        modifier = textModifier,
        label = { Text(text = "Password") },
        placeholder = { Text(text = "12334444") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
    OutlinedTextField(
        value = text,
        onValueChange = { v -> text = v },
        modifier = textModifier,
        label = { Text(text = "Email address") },
        placeholder = { Text(text = "Your email") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
        trailingIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription = null) }
    )
    var numberText by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = numberText,
        onValueChange = { numberText = it },
        modifier = textModifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = "Phone number") },
        placeholder = { Text(text = "88888888") },
    )
}

@Preview
@Composable
fun PreviewTextInputs() {
    Column {
        TextInputs()
    }
}