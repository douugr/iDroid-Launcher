package com.example.ipodlauncher.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun MusicScreen(navController: NavController) {
    var scrollValue by remember { mutableStateOf(0f) }
    val menuItems = listOf("Artists", "Albums", "Songs")
    var selectedItemIndex by remember { mutableStateOf(0) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    BaseScreen(
        navController = navController,
        title = "Music",
        onScroll = {
            scrollValue += it
            if (scrollValue > 20) {
                selectedItemIndex = (selectedItemIndex + 1).coerceIn(0, menuItems.size - 1)
                scrollValue = 0f
            } else if (scrollValue < -20) {
                selectedItemIndex = (selectedItemIndex - 1).coerceIn(0, menuItems.size - 1)
                scrollValue = 0f
            }
        },
        onCenterClick = {
            when (menuItems[selectedItemIndex]) {
                "Artists" -> navController.navigate("artists")
                "Albums" -> navController.navigate("albums")
                "Songs" -> navController.navigate("songs")
            }
        }
    ) { modifier ->
        LaunchedEffect(selectedItemIndex) {
            coroutineScope.launch {
                listState.animateScrollToItem(selectedItemIndex)
            }
        }
        LazyColumn(modifier = modifier, state = listState) {
            itemsIndexed(menuItems) { index, item ->
                val backgroundColor = if (index == selectedItemIndex) Color.Blue else Color.Transparent
                val textColor = if (index == selectedItemIndex) Color.White else Color.Black
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .padding(8.dp),
                    color = textColor
                )
            }
        }
    }
}
