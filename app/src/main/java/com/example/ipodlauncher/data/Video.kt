package com.example.ipodlauncher.data

import android.net.Uri

data class Video(
    val id: Long,
    val title: String,
    val duration: Long,
    val uri: Uri
)
