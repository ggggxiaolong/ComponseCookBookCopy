package com.chuntian.composecookbookcopy.ui.home.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chuntian.data.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerContent() {
    for (i in 1..3) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Item $i")
            Icon(imageVector = Icons.Default.List, contentDescription = "List")
        }
    }
}

@Composable
fun BottomSheetContent() {
    DrawerContent()
}

@Composable
fun PlayerBottomSheet() {
    val backgroundColor = MaterialTheme.colors.background.copy(alpha = 0.7f)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.adele21),
            contentDescription = null,
            modifier = Modifier.size(65.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Some Like you by Adele",
            style = MaterialTheme.typography.h6.copy(fontSize = 14.sp),
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
            contentDescription = null,
            modifier = Modifier.padding(16.dp)
        )
    }
    Text(text = "Lyrics", style = MaterialTheme.typography.h6, modifier = Modifier.padding(16.dp))
    Text(
        text = "I headr that you're settled down\n" + "That you found a girl and you're married now\n" +
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

@ExperimentalMaterialApi
@Composable
private fun ScaffoldContent(
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    sheetState: ModalBottomSheetState
) {
    ModalBottomSheetLayout(
        sheetContent = { BottomSheetContent() },
        modifier = Modifier.fillMaxWidth(),
        sheetState = sheetState
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { coroutineScope.launch { bottomSheetScaffoldState.drawerState.open() } },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(55.dp)
            ) {
                Text(text = "Navigation Drawer")
            }
            Button(
                onClick = { coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.expand() } },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(55.dp)
            ) {
                Text(text = "Bottom Sheet")
            }
            Button(onClick = { coroutineScope.launch { sheetState.show() } }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(55.dp)) {
                Text(text = "Modal Bottom sheet")
            }
        }
    }
}