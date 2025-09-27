package com.example.ipodlauncher.ui.view

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.ipodlauncher.data.Photo
import kotlinx.coroutines.launch

@Composable
fun BaseGridScreen(
    navController: NavController,
    title: String,
    items: List<Photo>,
    onItemClick: (Photo) -> Unit = {}
) {
    var scrollValue by remember { mutableStateOf(0f) }
    var selectedItemIndex by remember { mutableStateOf(0) }
    val gridState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    BaseScreen(
        navController = navController,
        title = title,
        onScroll = {
            scrollValue += it
            if (scrollValue > 20) {
                selectedItemIndex = (selectedItemIndex + 1).coerceIn(0, if (items.isEmpty()) 0 else items.size - 1)
                scrollValue = 0f
            } else if (scrollValue < -20) {
                selectedItemIndex = (selectedItemIndex - 1).coerceIn(0, if (items.isEmpty()) 0 else items.size - 1)
                scrollValue = 0f
            }
        },
        onCenterClick = {
            if (items.isNotEmpty()) {
                onItemClick(items[selectedItemIndex])
            }
        }
    ) { modifier ->
        LaunchedEffect(selectedItemIndex) {
            coroutineScope.launch {
                gridState.animateScrollToItem(selectedItemIndex)
            }
        }
        SelectableGrid(
            modifier = modifier,
            items = items,
            selectedItemIndex = selectedItemIndex,
            gridState = gridState
        )
    }
}