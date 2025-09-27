package com.example.ipodlauncher.service

import android.content.Context
import android.media.audiofx.Equalizer
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.ipodlauncher.data.Song

class MusicPlayer(context: Context) {

    private var exoPlayer: ExoPlayer = ExoPlayer.Builder(context).build().apply {
        setHandleAudioBecomingNoisy(true)
    }
    private lateinit var equalizer: Equalizer

    init {
        exoPlayer.addListener(object : androidx.media3.common.Player.Listener {
            override fun onAudioSessionIdChanged(audioSessionId: Int) {
                equalizer = Equalizer(0, audioSessionId)
                equalizer.enabled = true
            }
        })
    }

    fun play(song: Song) {
        val mediaItem = MediaItem.fromUri(song.path)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    fun pause() {
        exoPlayer.pause()
    }

    fun resume() {
        exoPlayer.play()
    }

    fun stop() {
        exoPlayer.stop()
    }

    fun release() {
        exoPlayer.release()
    }

    fun getEqualizer(): Equalizer {
        return equalizer
    }
}
