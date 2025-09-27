package com.example.ipodlauncher.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.ipodlauncher.service.MediaScanner

@Composable
fun AlbumsScreen(navController: NavController) {
    val context = LocalContext.current
    val mediaScanner = MediaScanner(context)
    val albums = mediaScanner.getAlbums()

    BaseListScreen(
        navController = navController,
        title = "Albums",
        items = albums,
        itemText = { it.title },
        onItemClick = { album ->
            navController.navigate("albumSongs/${album.id}")
        }
    )
}