package com.example.ipodlauncher.ui.view

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.ipodlauncher.data.Photo

@Composable
fun BaseGridScreen(
    navController: NavController,
    title: String,
    items: List<Photo>,
    onItemClick: (Photo) -> Unit = {}
) {
    var scrollValue by remember { mutableStateOf(0f) }
    var selectedItemIndex by remember { mutableStateOf(0) }

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
        SelectableGrid(
            modifier = modifier,
            items = items,
            selectedItemIndex = selectedItemIndex
        )
    }
}