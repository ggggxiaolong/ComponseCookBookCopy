package com.chuntian.composecookbookcopy.ui.templates.payCard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayCardScreen() {
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var expiry by remember { mutableStateOf("") }
    var cvc by remember { mutableStateOf("") }
    var focusType by remember { mutableStateOf(FocusType.Name) }
    Scaffold {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            PaymentCard(
                name = name,
                number = number,
                expiryDate = expiry,
                cvc = cvc,
                focusType = focusType
            )
            InputItem(textFieldValue = name, label = "Card holder name", onTextChanged = {
                name = it
            }, onFocus = { focusType = FocusType.Name })
            InputItem(textFieldValue = number, label = "Card number",
                onTextChanged = { number = it },
                onFocus = { focusType = FocusType.Number }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                InputItem(
                    textFieldValue = expiry, label = "Expiry date",
                    onTextChanged = { expiry = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    keyboardType = KeyboardType.Number,
                    onFocus = { focusType = FocusType.Expiry }
                )
                InputItem(
                    textFieldValue = cvc, label = "CVC",
                    onTextChanged = { cvc = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    keyboardType = KeyboardType.Number,
                    onFocus = { focusType = FocusType.Cvc }
                )
            }

            Button(
                onClick = {}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Save",
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 8.dp)
                )
            }
        }
    }
}

