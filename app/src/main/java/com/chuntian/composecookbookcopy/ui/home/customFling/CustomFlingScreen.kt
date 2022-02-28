package com.chuntian.composecookbookcopy.ui.home.customFling

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.chuntian.composecookbookcopy.utils.TestTags
import com.chuntian.data.DemoDataProvider
import com.chuntian.data.model.Item
import com.chuntian.theme.helper.TextFieldDefaultsMaterial
import io.iamjosephmj.flinger.bahaviours.StockFlingBehaviours

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomFlingScreen(onBack: () -> Unit) {
    val showDialog = remember { mutableStateOf(false) }
    val model = FlingViewModel()
    Scaffold(topBar = {
        SmallTopAppBar(
            title = { Text(text = "Custom Fling") },
            navigationIcon = {
                IconButton(
                    onClick = onBack
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            actions = {
                IconButton(onClick = { showDialog.value = true }) {
                    Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
                }
            }
        )
    }, content = {
        SettingView(showDialog, model)
        CustomFlingView(model)
    })
}

@Composable
fun CustomFlingView(model: FlingViewModel) {
    val list = DemoDataProvider.itemList
    val flingState = model.flingStateStore.observeAsState(FlingStateStore())
    val behavior = when (flingState.value.type) {
        FlingListViewTypes.CUSTOM -> flingState.value.buildScrollBehaviour()
        FlingListViewTypes.NATIVE -> StockFlingBehaviours.getAndroidNativeScroll()
        FlingListViewTypes.SMOOTH -> StockFlingBehaviours.smoothScroll()
    }
    LazyColumn(flingBehavior = behavior, modifier = Modifier.testTag(TestTags.HOME_FLING_LIST)) {
        items(items = list, itemContent = { item ->
            CustomFlingItemView(item = item)
            Divider(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
            )
        })
    }
}

@Composable
fun CustomFlingItemView(item: Item) {
    val typography = MaterialTheme.typography
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = item.imageId), contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = item.title, style = typography.titleLarge)
        Text(text = item.subtitle, style = typography.titleMedium)
        Text(text = item.source, style = typography.titleSmall)
    }
}

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun SettingView(showDialog: MutableState<Boolean>, model: FlingViewModel) {
    val onClose: () -> Unit = {
        showDialog.value = false
    }
    val items = FlingListViewTypes.values().toList()
    val stateStore = model.flingStateStore.observeAsState(FlingStateStore())
    val selectItem = remember { mutableStateOf(stateStore.value.type) }
    if (showDialog.value) {
        Dialog(
            onDismissRequest = onClose,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            HomeScaffold(title = "Setting", onBack = onClose) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Row(modifier = Modifier) {
                            for (item in items) {
                                Row(
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .weight(1f),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = selectItem.value == item,
                                        onClick = { selectItem.value = item })
                                    Text(
                                        text = item.toName(),
                                        modifier = Modifier.clickable { selectItem.value = item })
                                }
                            }
                        }
                    }
                    BuildCustomItems(
                        flingCopyStore = stateStore.value,
                        selectItem = selectItem,
                        model = model,
                        onClose = onClose,
                    )
                }
            }
        }
    }
}

class CustomItem(val name: String, val state: MutableState<String>)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BuildCustomItems(
    flingCopyStore: FlingStateStore,
    selectItem: MutableState<FlingListViewTypes>,
    model: FlingViewModel,
    onClose: () -> Unit,
) {
    val scrollFriction = remember { mutableStateOf(flingCopyStore.scrollFriction.toString()) }
    val absVelocityThreshold =
        remember { mutableStateOf(flingCopyStore.absVelocityThreshold.toString()) }
    val gravitationalForce =
        remember { mutableStateOf(flingCopyStore.gravitationalForce.toString()) }
    val inchesPerMeter = remember { mutableStateOf(flingCopyStore.inchesPerMeter.toString()) }
    val decelerationFriction =
        remember { mutableStateOf(flingCopyStore.decelerationFriction.toString()) }
    val decelerationRate = remember { mutableStateOf(flingCopyStore.decelerationRate.toString()) }
    val splineInflection = remember { mutableStateOf(flingCopyStore.splineInflection.toString()) }
    val splineStartTension =
        remember { mutableStateOf(flingCopyStore.splineStartTension.toString()) }
    val splineEndTension = remember { mutableStateOf(flingCopyStore.splineEndTension.toString()) }
    val numberOfSplinePoints =
        remember { mutableStateOf(flingCopyStore.numberOfSplinePoints.toString()) }
    val items = listOf(
        CustomItem(name = "ScrollFriction", state = scrollFriction),
        CustomItem(name = "AbsVelocityThreshold", state = absVelocityThreshold),
        CustomItem(name = "GravitationalForce", state = gravitationalForce),
        CustomItem(name = "InchesPerMeter", state = inchesPerMeter),
        CustomItem(name = "DecelerationFriction", state = decelerationFriction),
        CustomItem(name = "DecelerationRate", state = decelerationRate),
        CustomItem(name = "SplineInflection", state = splineInflection),
        CustomItem(name = "SplineStartTension", state = splineStartTension),
        CustomItem(name = "SplineEndTension", state = splineEndTension),
        CustomItem(name = "NumberOfSplinePoints", state = numberOfSplinePoints),
    )
    val focusManager = LocalFocusManager.current
    val onApply: () -> Unit = {
        when (selectItem.value) {
            FlingListViewTypes.CUSTOM -> {
                val newData = FlingStateStore(
                    type = FlingListViewTypes.CUSTOM,
                    scrollFriction = scrollFriction.value.toFloat(),
                    absVelocityThreshold = absVelocityThreshold.value.toFloat(),
                    gravitationalForce = gravitationalForce.value.toFloat(),
                    inchesPerMeter = inchesPerMeter.value.toFloat(),
                    decelerationFriction = decelerationFriction.value.toFloat(),
                    decelerationRate = decelerationRate.value.toFloat(),
                    splineInflection = splineInflection.value.toFloat(),
                    splineStartTension = splineStartTension.value.toFloat(),
                    splineEndTension = splineEndTension.value.toFloat(),
                    numberOfSplinePoints = numberOfSplinePoints.value.toInt(),
                )
                model.updateFlingState(newData)
            }
            else -> model.updateFlingState(flingCopyStore.copy(type = selectItem.value))
        }
        onClose()
    }

    if (selectItem.value == FlingListViewTypes.CUSTOM) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { focusManager.clearFocus() })
        ) {
            for (item in items) {
                item {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp)
                        )
                        OutlinedTextField(
                            value = item.state.value,
                            maxLines = 1,
                            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Number,
                            ),
                            colors = TextFieldDefaultsMaterial.outlinedTextFieldColors(),
                            onValueChange = {
                                when {
                                    it.count { c -> c == '.' } > 1 -> {}
                                    it.isEmpty() -> item.state.value = ""
                                    it.isNotEmpty() -> {
                                        item.state.value = it
                                    }
                                }
                            },
                        )
                    }
                }
            }
        }
    }

    Button(onClick = onApply) {
        Text(text = "Apply", style = MaterialTheme.typography.bodyLarge)
    }
}