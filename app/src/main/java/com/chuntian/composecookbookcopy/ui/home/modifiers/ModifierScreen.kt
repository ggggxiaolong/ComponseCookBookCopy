package com.chuntian.composecookbookcopy.ui.home.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.chuntian.theme.ComposeCookBookCopyTheme
import com.chuntian.theme.Green500
import com.chuntian.theme.Teal200

@ExperimentalComposeUiApi
@Composable
fun ModifiersScreen(onBack: () -> Unit) {
    HomeScaffold(title = "Modifiers", onBack = onBack) { ModifiersView() }
}

@ExperimentalComposeUiApi
@Composable
private fun ModifiersView() {
    val typography = MaterialTheme.typography
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(
            text = "Order in modifier values matters",
            style = typography.titleMedium,
            modifier = Modifier.padding(8.dp)
        )
        DemoText(text = "No Modifiers")
        DemoElementButton(modifier = Modifier)

        DemoText(text = "Modifier.fillMaxWidth()")
        DemoElementButton(modifier = Modifier.fillMaxWidth())

        DemoText(text = "Modifier.fillMaxWidth().padding(12.dp)")
        DemoElementButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )

        DemoText(text = "Modifier.size(100.dp)")
        DemoElementButton(modifier = Modifier.size(100.dp))

        DemoText(text = "Modifier.height(50.dp).width(200.dp)")
        DemoElementButton(
            modifier = Modifier
                .height(50.dp)
                .width(200.dp)
        )

        DemoText(text = "Modifier.clip(CircleShape)")
        DemoElementButton(modifier = Modifier.clip(CircleShape))

        DemoText(text = "Modifier.clip(RoundedCornerShape(12.dp))")
        DemoElementButton(modifier = Modifier.clip(RoundedCornerShape(12.dp)))

        DemoText(text = "Modifier.clip(RoundedCornerShape(topStart = 12.dp, bottomEnd = 12.dp))")
        DemoElementButton(
            modifier = Modifier.clip(
                RoundedCornerShape(
                    topStart = 12.dp,
                    bottomEnd = 12.dp
                )
            )
        )

        DemoText(text = "Modifier.clip(CutCornerShape(12.dp))")
        DemoElementButton(modifier = Modifier.clip(CutCornerShape(12.dp)))

        DemoText(text = "Modifier.align(Alignment.CenterHorizontally)")
        DemoElementButton(modifier = Modifier.align(Alignment.CenterHorizontally))

        DemoText(text = "Modifier.align(Alignment.End)")
        DemoElementButton(modifier = Modifier.align(Alignment.End))

        DemoText(text = "Modifier.alpha(0.5f)")
        DemoElementButton(modifier = Modifier.alpha(0.5f))

        DemoText(text = "Modifier.shadow(12.dp)")
        DemoElementButton(modifier = Modifier.shadow(12.dp))

        DemoText(text = "Modifier.background(MaterialTheme.colors.secondary)")
        DemoElementText(modifier = Modifier.background(MaterialTheme.colorScheme.secondary))

        DemoText(
            text = "Modifier.padding(8.dp).background(\n" +
                    "                    brush = Brush.horizontalGradient(\n" +
                    "                        colors = listOf(\n" +
                    "                            Teal200, Green500\n" +
                    "                        ), startX = 0f, endX = 100f\n" +
                    "                    )\n" +
                    "                )"
        )
        DemoElementText(
            modifier = Modifier
                .padding(8.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Teal200, Green500
                        ), startX = 0f, endX = 100f
                    )
                )
        )

        DemoText(text = "Modifier.background(brush = Brush.horizontalGradient.padding(8.dp))")
        DemoElementText(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Teal200, Green500
                        ), startX = 0f, endX = 200f
                    )
                )
                .padding(8.dp)
        )

        DemoText(text = "Modifier.background(brush = Brush.verticalGradient.padding(8.dp))")
        DemoElementText(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Teal200, Green500
                        ), startY = 0f, endY = 500f
                    )
                )
                .padding(8.dp)
        )

        DemoText(text = "Modifier.clickable {  }.background(Teal200).padding(8.dp)")
        DemoElementText(modifier = Modifier
            .clickable { }
            .background(Teal200)
            .padding(8.dp))

        DemoText(text = "Modifier.background(Teal200).pointerInteropFilter{ true }.padding(8.dp)")
        DemoElementText(modifier = Modifier
            .background(Teal200)
            .pointerInteropFilter { true }
            .padding(8.dp))

        DemoText(text = "Modifier.background(Teal200).pointerInput(\"key\"){this.detectTapGestures {}}.padding(8.dp)")
        DemoElementText(
            modifier = Modifier
                .background(Teal200)
                .pointerInput("key") { this.detectTapGestures {} }
                .padding(8.dp)
        )

    }
}

@Composable
fun DemoText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
        modifier = Modifier.padding(4.dp)
    )
}

@Composable
fun DemoElementButton(modifier: Modifier) {
    Button(onClick = { }, modifier = modifier) {
        Text(text = "Basic Button")
    }
}

@Composable
fun DemoElementText(modifier: Modifier) {
    Text(text = "Basic Text", modifier = modifier)
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun PreviewModifier() {
    ComposeCookBookCopyTheme() {
        ModifiersScreen {

        }
    }
}