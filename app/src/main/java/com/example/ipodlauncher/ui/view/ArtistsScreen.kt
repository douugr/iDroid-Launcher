package com.example.ipodlauncher.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.ipodlauncher.service.MediaScanner

@Composable
fun ArtistsScreen(navController: NavController) {
    val context = LocalContext.current
    val mediaScanner = MediaScanner(context)
    val artists = mediaScanner.getArtists()

    BaseListScreen(
        navController = navController,
        title = "Artists",
        items = artists,
        itemText = { it.name }
    )
}