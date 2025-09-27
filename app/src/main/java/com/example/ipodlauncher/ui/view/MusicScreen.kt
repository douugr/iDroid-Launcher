package com.example.ipodlauncher.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MusicScreen(navController: NavController) {
    var scrollValue by remember { mutableStateOf(0f) }
    val menuItems = listOf("Artists", "Albums", "Songs")
    var selectedItemIndex by remember { mutableStateOf(0) }

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
        Column(modifier = modifier) {
            menuItems.forEachIndexed { index, item ->
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
