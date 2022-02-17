package com.chuntian.composecookbookcopy.ui.home.androidViews

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.chuntian.composecookbookcopy.R
import com.chuntian.composecookbookcopy.ui.home.HomeScaffold
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions

@Composable
fun AndroidViewScreen(onBack: () -> Unit) {
    HomeScaffold(title = "AndroidViews", onBack = onBack) {
        val context = LocalContext.current
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Text(text = "Android Views Inside @Column()", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            AndroidTextView(context = context)
            AndroidButton(context = context)
            AndroidMapView(context = context)
        }
    }
}

@Composable
fun AndroidTextView(context: Context) {
    Text(text = "Below is Android Textview", style = MaterialTheme.typography.titleMedium)
    val textColor = MaterialTheme.colorScheme.onBackground.toArgb()
    val androidTextView = remember {
        TextView(context).apply {
            setText(R.string.about_me)
            setTextColor(textColor)
        }
    }
    AndroidView(modifier = Modifier.padding(8.dp), factory = { androidTextView })
}

@Composable
fun AndroidButton(context: Context) {
    var count by remember { mutableStateOf(0) }
    Text(text = "Bellow is Android Button")
    val androidButton = remember {
        Button(context).apply {
            text = "Click me : $count"
            setOnClickListener { count++ }
        }
    }
    AndroidView(factory = { androidButton }, modifier = Modifier.padding(8.dp)){
        it.text = "Click me : $count"
    }
}

@Composable
fun AndroidMapView(context: Context) {
    Text(text = "Android MapView", style = MaterialTheme.typography.titleMedium)
    val androidMapView = rememberMapViewWithLifecycle(context = context)
    AndroidView(
        factory = { androidMapView },
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .clip(
                RoundedCornerShape(10.dp)
            )
    ) { mapView ->
        mapView.getMapAsync {
            val position = LatLng(1.3521, 103.8198)
            it.addMarker(MarkerOptions().position(position))
            it.moveCamera(CameraUpdateFactory.newLatLng(position))
        }
    }
}

@Composable
fun rememberMapViewWithLifecycle(context: Context): MapView {
    val mapView = remember { MapView(context) }
    val lifecycleObserver = remember(mapView) {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
            }
        }
    }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(key1 = lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }
    return mapView
}