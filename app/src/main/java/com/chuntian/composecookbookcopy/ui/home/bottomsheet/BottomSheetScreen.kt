package com.chuntian.composecookbookcopy.ui.home.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.chuntian.data.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetScreen(onBack: () -> Unit) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    BottomSheetScaffold(
        topBar = {
            SmallTopAppBar(title = { Text(text = "BottomSheet") }, navigationIcon = {
                IconButton(
                    onClick = onBack
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            })
        },
        sheetContent = { PlayerBottomSheetView() },
        drawerContent = { DrawerContentView() },
        content = { SheetContent(scope, bottomSheetScaffoldState, sheetState) },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = if (sheetState.isAnimationRunning || sheetState.isVisible) 0.dp else 65.dp
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SheetContent(
    scope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    sheetState: ModalBottomSheetState
) {
    var showFullBottomSheet by remember { mutableStateOf(false) }
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            if (showFullBottomSheet) {
                FullBottomSheetContent {
                    scope.launch {
                        sheetState.hide()
                    }
                }
            } else {
                DrawerContentView()
            }
        }) {
        Column(modifier = Modifier.fillMaxSize()) {
            Button(
                onClick = {
                    scope.launch {
                        bottomSheetScaffoldState.drawerState.open()
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Navigation Drawer")
            }

            Button(
                onClick = {
                    scope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        }
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Bottom Sheet")
            }

            Button(
                onClick = {
                    showFullBottomSheet = false
                    scope.launch {
                        sheetState.show()
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Modal Bottom sheet")
            }

            Button(
                onClick = {
                    showFullBottomSheet = true
                    scope.launch {
                        sheetState.animateTo(ModalBottomSheetValue.Expanded)
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Modal Bottom sheet Full")
            }
        }
    }
}

@Composable
fun DrawerContentView() {
    for (index in 0..3) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Item $index")
            Icon(imageVector = Icons.Default.List, contentDescription = "List")
        }
    }
}

@Composable
fun PlayerBottomSheetView() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.adele21),
            contentDescription = null,
            modifier = Modifier.size(65.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Someone Like you by Adele",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        )
        Icon(
            imageVector = Icons.Default.FavoriteBorder,
            modifier = Modifier.padding(8.dp),
            contentDescription = null
        )
        Icon(
            imageVector = Icons.Default.PlayArrow,
            modifier = Modifier.padding(8.dp),
            contentDescription = null
        )
    }
    Text(
        text = "Lyrics",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(16.dp)
    )
    Text(
        text = "I heard that you're settled down\n" +
                "That you found a girl and you're married now\n" +
                "I heard that your dreams came true\n" +
                "Guess she gave you things, I didn't give to you\n" +
                "Old friend, why are you so shy?\n" +
                "Ain't like you to hold back or hide from the light\n" +
                "I hate to turn up out of the blue, uninvited\n" +
                "But I couldn't stay away, I couldn't fight it\n" +
                "I had hoped you'd see my face\n" +
                "And that you'd be reminded that for me, it isn't over",
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun FullBottomSheetContent(onClose: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("working.json"))
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(composition = composition, modifier = Modifier.height(400.dp))
            Button(onClick = onClose, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Back")
            }
        }
    }
}