package com.example.ipodlauncher.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ipodlauncher.service.MediaScanner

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val mediaScanner = MediaScanner(context)

    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController = navController) }
        composable("apps") { AppDrawerScreen(navController = navController) }
        composable("music") { MusicScreen(navController = navController) }
        composable("artists") { ArtistsScreen(navController = navController) }
        composable(
            "artistSongs/{artistId}",
            arguments = listOf(navArgument("artistId") { type = NavType.LongType })
        ) { backStackEntry ->
            val artistId = backStackEntry.arguments?.getLong("artistId")
            if (artistId != null) {
                ArtistSongsScreen(navController = navController, artistId = artistId)
            }
        }
        composable("albums") { AlbumsScreen(navController = navController) }
        composable(
            "albumSongs/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.LongType })
        ) { backStackEntry ->
            val albumId = backStackEntry.arguments?.getLong("albumId")
            if (albumId != null) {
                AlbumSongsScreen(navController = navController, albumId = albumId)
            }
        }
        composable("songs") { SongsScreen(navController = navController) }
        composable("photos") { PhotosScreen(navController = navController) }
        composable("videos") { VideosScreen(navController = navController) }
        composable("settings") { SettingsScreen(navController = navController) }
        composable("downloads") { DownloadsScreen(navController = navController) }
        composable("documents") { DocumentsScreen(navController = navController) }
        composable(
            "nowPlaying/{songId}",
            arguments = listOf(navArgument("songId") { type = NavType.LongType })
        ) { backStackEntry ->
            val songId = backStackEntry.arguments?.getLong("songId")
            val songs = mediaScanner.scanForMusic()
            val song = songs.find { it.id == songId }
            if (song != null) {
                val songIndex = songs.indexOf(song)
                NowPlayingScreen(navController = navController, songs = songs, initialSongIndex = songIndex)
            }
        }
    }
}
