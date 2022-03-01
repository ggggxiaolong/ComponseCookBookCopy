package com.chuntian.composecookbookcopy.ui.templates.profile

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chuntian.data.R
import com.chuntian.theme.components.InterestTag

const val initialImageFloat = 170f
const val name = "Gurupreet Singh"
const val email = "gurpreet.usit@gmail.com"

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScree() {
    val scrollState = rememberScrollState()
    Scaffold(topBar = {
        if (scrollState.value > initialImageFloat + 5) {
            SmallTopAppBar(
                title = { Text(text = name) },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.p1),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                            .size(32.dp)
                            .clip(CircleShape)
                    )
                },
                actions = { Icon(imageVector = Icons.Default.Settings, contentDescription = null) },
            )
        }
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            TopBackground()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = scrollState)
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                TopScrollContent(scrollState)
                BottomContent()
            }
        }
    }
}

@Composable
private fun TopBackground() {
    val gradient = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
    )
    Spacer(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .background(brush = Brush.verticalGradient(colors = gradient))
    )
}

@Composable
fun TopScrollContent(scrollState: ScrollState) {
    val isInVisible = scrollState.value > initialImageFloat - 20
    Row(
        modifier = Modifier.alpha(
            animateFloatAsState(targetValue = if (isInVisible) 0f else 1f).value
        )
    ) {
        AnimatedImage(scrollState.value.toFloat())
        Column(modifier = Modifier.padding(start = 8.dp, top = 16.dp)) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Android Developer",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun AnimatedImage(scroll: Float) {
    val imageSize = (initialImageFloat - scroll).coerceIn(36f, initialImageFloat)
    Image(
        painter = painterResource(id = R.drawable.khalid),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(start = 16.dp)
            .size(animateDpAsState(targetValue = imageSize.dp).value)
            .clip(CircleShape)
    )
}

@Composable
fun BottomContent() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp)
    ) {
        SocialRow()
        Text(
            text = "About Me",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 8.dp, top = 12.dp)
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
        Text(
            text = stringResource(id = com.chuntian.composecookbookcopy.R.string.about_me),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        InterestsSection()
        PhotosSection()
        Text(
            text = "About Project",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 8.dp, top = 16.dp)
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
        Text(
            text = stringResource(id = com.chuntian.composecookbookcopy.R.string.about_project),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        MoreInfoSection()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialRow() {
    Card {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp)
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = com.chuntian.theme.R.drawable.ic_github_square_brands),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = com.chuntian.theme.R.drawable.ic_twitter_square_brands),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = com.chuntian.theme.R.drawable.ic_linkedin_brands),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun InterestsSection() {
    Text(
        text = "My Interests", style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(start = 8.dp, top = 16.dp),
        color = MaterialTheme.colorScheme.primary
    )
    Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
    Row(modifier = Modifier.padding(start = 8.dp, top = 8.dp)) {
        for (item in listOf("Android", "Compose", "Flutter", "SwiftUI")) {
            InterestTag(text = item)
        }
    }
    Row(modifier = Modifier.padding(top = 8.dp)) {
        for (item in listOf("Video games", "Podcasts", "Basketball")) {
            InterestTag(text = item)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosSection() {
    Text(
        text = "My Photography",
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 16.dp, start = 8.dp)
    )
    Divider(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
    val imageModifier = Modifier
        .padding(vertical = 8.dp, horizontal = 4.dp)
        .size(120.dp)
        .clip(RoundedCornerShape(8.dp))
    val images = listOf(
        R.drawable.food2,
        R.drawable.food3,
        R.drawable.food6,
        R.drawable.food12,
        R.drawable.food13,
        R.drawable.food15
    )
    Row(
        modifier = Modifier
            .padding(start = 8.dp, top = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (item in images.take(3)) {
            Image(
                painter = painterResource(id = item),
                contentDescription = null,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
        }
    }
    Row(
        modifier = Modifier
            .padding(start = 8.dp, top = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (item in images.takeLast(3)) {
            Image(
                painter = painterResource(id = item),
                contentDescription = null,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoreInfoSection() {
    Text(
        text = "More Info",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(start = 8.dp, top = 16.dp),
    )
    Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
    ListItem(
        icon = {
            Icon(
                painter = painterResource(id = com.chuntian.theme.R.drawable.ic_github_square_brands),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        text = {
            Text(text = "Compose CookBook Github", style = MaterialTheme.typography.bodyMedium)
        },
        secondaryText = {
            Text(text = "Tap to checkout the repo for the project")
        }
    )
    ListItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Email,
                contentDescription = null,
            )
        },
        text = {
            Text(text = "Contact Me", style = MaterialTheme.typography.bodyMedium)
        },
        secondaryText = {
            Text(text = "Tap to write me about any concern or info at $email")
        }
    )
    ListItem(
        icon = {
            Icon(
                painter = painterResource(id = com.chuntian.theme.R.drawable.ic_github_square_brands),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        text = {
            Text(text = "Demo Setting", style = MaterialTheme.typography.bodyMedium)
        },
        secondaryText = {
            Text(text = "Not included yet. coming soon..")
        }
    )
}