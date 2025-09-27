package com.example.ipodlauncher.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.ipodlauncher.service.MediaScanner

@Composable
fun SongsScreen(navController: NavController) {
    val context = LocalContext.current
    val mediaScanner = MediaScanner(context)
    val songs = mediaScanner.scanForMusic()

    BaseListScreen(
        navController = navController,
        title = "Songs",
        items = songs,
        itemText = { it.title },
        onItemClick = { song ->
            // Navigate to Now Playing screen, passing song info
            navController.navigate("nowPlaying/${song.id}")
        }
    )
}