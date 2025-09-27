package com.example.ipodlauncher.ui.view

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.ipodlauncher.service.MediaScanner

@Composable
fun VideosScreen(navController: NavController) {
    val context = LocalContext.current
    val mediaScanner = MediaScanner(context)
    val videos = mediaScanner.getVideos()

    BaseListScreen(
        navController = navController,
        title = "Videos",
        items = videos,
        itemText = { "${it.title} - ${it.duration}" },
        onItemClick = { video ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(video.uri, "video/*")
            context.startActivity(intent)
        }
    )
}