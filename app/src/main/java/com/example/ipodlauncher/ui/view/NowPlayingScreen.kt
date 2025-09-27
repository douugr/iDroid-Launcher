package com.example.ipodlauncher.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.ipodlauncher.data.Song
import androidx.compose.material3.Text
@Composable
fun NowPlayingScreen(navController: NavController, songs: List<Song>, initialSongIndex: Int) {
    var currentSongIndex by remember { mutableStateOf(initialSongIndex) }

    BaseScreen(
        navController = navController,
        title = "Now Playing",
        onForwardClick = {
            currentSongIndex = (currentSongIndex + 1).coerceIn(0, songs.size - 1)
        },
        onBackClick = {
            currentSongIndex = (currentSongIndex - 1).coerceIn(0, songs.size - 1)
        }
    ) { modifier ->
        val song = songs[currentSongIndex]
        Column(modifier = modifier) {
            Text(text = song.title)
            Text(text = song.artist)
            Text(text = song.album)
        }
    }
}