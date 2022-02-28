package com.chuntian.composecookbookcopy.ui.templates.login

import FaIcons
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.chuntian.composecookbookcopy.ui.widgets.HorizontalDottedProgressBar
import com.chuntian.composecookbookcopy.utils.IOScope
import com.chuntian.theme.components.FaIcon2
import com.chuntian.theme.helper.TextFieldDefaultsMaterial
import com.guru.fontawesomecomposelib.FaIcon
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LoginScreen() {
    Scaffold() {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var showPass by remember { mutableStateOf(false) }
        var hasError by remember { mutableStateOf(false) }
        var loading by remember { mutableStateOf(false) }
        val composition = rememberLottieComposition(LottieCompositionSpec.Asset("working.json"))
        val onLogin: () -> Unit = {
            if (isInvalidInput(email, password)) {
                hasError = true
                loading = false
            } else {
                hasError = false
                loading = true
                IOScope().launch {
                    delay(2000)
                    loading = false
                }
            }
        }
        val focusManager = LocalFocusManager.current
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { focusManager.clearFocus() })
        ) {
//            item { Spacer(modifier = Modifier.height(20.dp)) }
            item {
                LottieAnimation(
                    composition = composition.value,
                    modifier = Modifier.height(220.dp)
                )
            }
            item {
                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold)
                )
            }
            item {
                Text(
                    text = "We have missed you, Let's start by sign in!",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(12.dp)
                )
            }
            item {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    leadingIcon = {
                        FaIcon2(
                            faIcon = FaIcons.Envelope,
                        )
                    },
                    colors = TextFieldDefaultsMaterial.outlinedTextFieldColors(),
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    label = { Text(text = "Email address") },
                    placeholder = { Text(text = "abc@gmail.com") },
                    isError = hasError,
                )
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    leadingIcon = {
                        FaIcon2(
                            faIcon = FaIcons.Key,
                        )
                    },
                    trailingIcon = {
                        FaIcon2(
                            faIcon = if (showPass) FaIcons.Eye else FaIcons.EyeSlash,
                            modifier = Modifier.clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = { showPass = !showPass })
                        )
                    },
                    colors = TextFieldDefaultsMaterial.outlinedTextFieldColors(),
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions { focusManager.clearFocus(); onLogin() },
                    label = { Text(text = "Password") },
                    placeholder = { Text(text = "12334444") },
                    visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
                    isError = hasError
                )
            }
            item {
                Button(
                    onClick = onLogin,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(50.dp)
                ) {
                    if (loading) {
                        HorizontalDottedProgressBar()
                    } else {
                        Text(text = "Log in")
                    }
                }
            }
            item {
                Box {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .height(1.dp)
                            .background(Color.LightGray)
                    )
                    Text(
                        text = "Or use", color = Color.LightGray,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .background(MaterialTheme.colorScheme.background)
                            .padding(16.dp)
                    )
                }
            }
            item {
                OutlinedButton(onClick = { }) {
                    FaIcon2(
                        faIcon = FaIcons.Facebook,
//                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Sign in with Facebook",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                OutlinedButton(onClick = { }) {
                    FaIcon2(
                        faIcon = FaIcons.Google,
//                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Sign in with Gmail",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            item {
                val color = MaterialTheme.colorScheme.primary
                val span = AnnotatedString.Builder("Don't have an account? Register")
                span.addStyle(
                    style = SpanStyle(
                        color = color,
                        textDecoration = TextDecoration.Underline
                    ), start = 23, end = 31
                )
                Text(
                    text = span.toAnnotatedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .clickable {

                        },
                    textAlign = TextAlign.Center
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

fun isInvalidInput(email: String, password: String): Boolean = email.isEmpty() || password.isEmpty()