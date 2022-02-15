package com.chuntian.composecookbookcopy.ui.home.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.chuntian.composecookbookcopy.utils.TestTags
import com.chuntian.theme.ComposeCookBookCopyTheme
import com.chuntian.theme.Green200
import com.chuntian.theme.Green500
import com.chuntian.theme.Green700
import androidx.compose.material3.MaterialTheme

@Composable
fun LayoutScreen(onBack: () -> Unit) {
    HomeScaffold(title = "Layouts", onBack = onBack) { LayoutView() }
}

@Composable
private fun LayoutView() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        TypesOfRow()
        TypeOfColumns()
        TypeOfBox()
        ConstrainLayouts()
    }
}

@Composable
fun TypesOfRow() {
    val typography = MaterialTheme.typography
    Title(text = "Rows", style = typography.titleMedium)
    Title(text = "Arrangement.Start")
    MultipleRowTexts(
        horizontalArrangement = Arrangement.Start,
        testTag = TestTags.HOME_LAYOUTS_ROW_START
    )

    Title(text = "Arrangement.End")
    MultipleRowTexts(
        horizontalArrangement = Arrangement.End,
        testTag = TestTags.HOME_LAYOUTS_ROW_END
    )

    Title(text = "Arrangement.Center")
    MultipleRowTexts(
        testTag = TestTags.HOME_LAYOUTS_ROW_CENTER,
        horizontalArrangement = Arrangement.Center
    )

    Title(text = "Arrangement.SpaceBetween")
    MultipleRowTexts(
        testTag = TestTags.HOME_LAYOUTS_ROW_SPACE_BETWEEN,
        horizontalArrangement = Arrangement.SpaceBetween
    )

    Title(text = "Arrangement.SpaceAround")
    MultipleRowTexts(
        testTag = TestTags.HOME_LAYOUTS_ROW_SPACE_AROUND,
        horizontalArrangement = Arrangement.SpaceAround
    )

    Title(text = "Arrangement.SpaceEvenly")
    MultipleRowTexts(
        testTag = TestTags.HOME_LAYOUTS_ROW_SPACE_AROUND,
        horizontalArrangement = Arrangement.SpaceEvenly
    )
}

@Composable
fun Title(text: String, style: TextStyle = MaterialTheme.typography.labelMedium) {
    Text(text = text, style = style, modifier = Modifier.padding(8.dp))
}

@Composable
fun MultipleRowTexts(testTag: String, horizontalArrangement: Arrangement.Horizontal) {
    Row(
        horizontalArrangement = horizontalArrangement, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .testTag(testTag)
    ) {
        Text(text = "First", modifier = Modifier.padding(8.dp))
        Text(text = "Second", modifier = Modifier.padding(8.dp))
        Text(text = "Third", modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun TypeOfColumns() {
    Title(text = "Column", style = MaterialTheme.typography.titleMedium)
    Title(text = "Arrangement.Top")
    MultipleColumnTexts(
        testTag = TestTags.HOME_LAYOUTS_COLUMN_TOP,
        horizontalArrangement = Arrangement.Top
    )

    Title(text = "Arrangement.Bottom")
    MultipleColumnTexts(
        testTag = TestTags.HOME_LAYOUTS_COLUMN_BOTTOM,
        horizontalArrangement = Arrangement.Bottom
    )

    Title(text = "Arrangement.Center")
    MultipleColumnTexts(
        testTag = TestTags.HOME_LAYOUTS_COLUMN_BOTTOM,
        horizontalArrangement = Arrangement.Center
    )

    Title(text = "Arrangement.SpaceEvenly")
    MultipleColumnTexts(
        testTag = TestTags.HOME_LAYOUTS_COLUMN_BOTTOM,
        horizontalArrangement = Arrangement.SpaceEvenly
    )

    Title(text = "Arrangement.SpaceAround")
    MultipleColumnTexts(
        testTag = TestTags.HOME_LAYOUTS_COLUMN_BOTTOM,
        horizontalArrangement = Arrangement.SpaceAround
    )

    Title(text = "Arrangement.SpaceBetween")
    MultipleColumnTexts(
        testTag = TestTags.HOME_LAYOUTS_COLUMN_BOTTOM,
        horizontalArrangement = Arrangement.SpaceBetween
    )
}

@Composable
fun MultipleColumnTexts(testTag: String, horizontalArrangement: Arrangement.Vertical) {
    Column(
        verticalArrangement = horizontalArrangement, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.Gray)
            .testTag(testTag)
    ) {
        Text(text = "First", modifier = Modifier.padding(8.dp))
        Text(text = "Second", modifier = Modifier.padding(8.dp))
        Text(text = "Third", modifier = Modifier.padding(8.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeOfBox() {
    val boxModifier = Modifier
        .padding(8.dp)
        .background(Color.LightGray)
        .fillMaxWidth()
        .height(250.dp)
    val elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    Title(text = "Box", style = MaterialTheme.typography.titleMedium)
    Title(text = "Children with no align")
    Box(modifier = boxModifier.testTag(TestTags.HOME_LAYOUTS_BOX_NO_ALIGN)) {
        Card(
            containerColor = Green700,
            elevation = elevation,
            modifier = Modifier.size(200.dp),
            content = {})
        Card(
            containerColor = Green500,
            elevation = elevation,
            modifier = Modifier.size(150.dp),
            content = {})
        Card(
            containerColor = Green200,
            elevation = elevation,
            modifier = Modifier.size(100.dp),
            content = {})
    }

    Title(text = "Children with TopStart, Center BottomEnd")
    Box(modifier = boxModifier.testTag(TestTags.HOME_LAYOUTS_BOX_TOP_CENTER_AND_NO_ALIGN)) {
        Card(containerColor = Green700, elevation = elevation, modifier = Modifier
            .size(200.dp)
            .align(
                Alignment.TopStart
            ), content = {})
        Card(containerColor = Green500, elevation = elevation, modifier = Modifier
            .size(150.dp)
            .align(
                Alignment.Center
            ), content = {})
        Card(containerColor = Green200, elevation = elevation, modifier = Modifier
            .size(100.dp)
            .align(
                Alignment.BottomEnd
            ), content = {})
    }
}

@Composable
fun ConstrainLayouts() {
    Title(text = "ConstrainLayouts", style = MaterialTheme.typography.titleMedium)
    ConstraintLayout(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .height(200.dp)
            .testTag(TestTags.HOME_LAYOUTS_CONSTRAINT_LAYOUT)
    ) {
        val (mainButton, mainText, secondaryText, outlineButton) = createRefs()
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(mainButton) {
                top.linkTo(
                    parent.top,
                    margin = 16.dp
                )
            }) {
            Text(text = "MainButton")
        }
        Text(
            text = "Main Text",
            modifier = Modifier.constrainAs(mainText) {
                top.linkTo(
                    mainButton.bottom,
                    margin = 16.dp
                )
                absoluteLeft.linkTo(mainButton.end, margin = 16.dp)
            })
        Text(text = "Secondary Text", modifier = Modifier.constrainAs(secondaryText) {
            top.linkTo(mainText.bottom, margin = 16.dp)
            absoluteLeft.linkTo(mainButton.end, margin = 16.dp)
        })

        OutlinedButton(onClick = { }, modifier = Modifier.constrainAs(outlineButton) {
            bottom.linkTo(parent.bottom, margin = 16.dp)
            end.linkTo(parent.end)
        }) {
            Text(text = "Outline Button")
        }
    }
}

@Preview
@Composable
fun PreviewLayout() {
    ComposeCookBookCopyTheme {
        LayoutView()
    }
}