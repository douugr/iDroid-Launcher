package com.example.ipodlauncher.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.ipodlauncher.service.MediaScanner

@Composable
fun PhotosScreen(navController: NavController) {
    val context = LocalContext.current
    val mediaScanner = MediaScanner(context)
    val photos = mediaScanner.getPhotos()

    BaseGridScreen(
        navController = navController,
        title = "Photos",
        items = photos
    )
}