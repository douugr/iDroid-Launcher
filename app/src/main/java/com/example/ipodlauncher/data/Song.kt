package com.example.ipodlauncher.data

import android.net.Uri

data class Song(
    val id: Long,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long,
    val path: String,
    val albumArtUri: Uri?,
    val albumId: Long,
    val artistId: Long
)
