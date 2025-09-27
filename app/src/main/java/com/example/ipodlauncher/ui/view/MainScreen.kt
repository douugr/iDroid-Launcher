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
fun MainScreen(navController: NavController) {
    var scrollValue by remember { mutableStateOf(0f) }
    val menuItems = listOf("Music", "Photos", "Videos", "Apps", "Downloads", "Documents", "Settings", "Now Playing")
    var selectedItemIndex by remember { mutableStateOf(0) }

    BaseScreen(
        navController = navController,
        title = "iPod",
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
                "Music" -> navController.navigate("music")
                "Photos" -> navController.navigate("photos")
                "Videos" -> navController.navigate("videos")
                "Apps" -> navController.navigate("apps")
                "Downloads" -> navController.navigate("downloads")
                "Documents" -> navController.navigate("documents")
                "Settings" -> navController.navigate("settings")
                // Add other navigation destinations here
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
