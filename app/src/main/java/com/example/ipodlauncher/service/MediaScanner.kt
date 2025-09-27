package com.example.ipodlauncher.service

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.example.ipodlauncher.data.Album
import com.example.ipodlauncher.data.Artist
import com.example.ipodlauncher.data.Song

class MediaScanner(private val context: Context) {

    fun scanForMusic(): List<Song> {
        val musicList = mutableListOf<Song>()
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST_ID
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"

        context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val albumColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
            val albumIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
            val artistIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST_ID)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val title = cursor.getString(titleColumn)
                val artist = cursor.getString(artistColumn)
                val album = cursor.getString(albumColumn)
                val duration = cursor.getLong(durationColumn)
                val path = cursor.getString(dataColumn)
                val albumId = cursor.getLong(albumIdColumn)
                val artistId = cursor.getLong(artistIdColumn)

                val albumArtUri = getAlbumArtUri(albumId)

                musicList.add(Song(id, title, artist, album, duration, path, albumArtUri, albumId, artistId))
            }
        }
        return musicList
    }

    private fun getAlbumArtUri(albumId: Long): Uri? {
        val artworkUri = Uri.parse("content://media/external/audio/albumart")
        return Uri.withAppendedPath(artworkUri, albumId.toString())
    }

    fun getAlbums(): List<Album> {
        val albumsList = mutableListOf<Album>()
        val projection = arrayOf(
            MediaStore.Audio.Albums._ID,
            MediaStore.Audio.Albums.ALBUM,
            MediaStore.Audio.Albums.ARTIST,
            MediaStore.Audio.Albums.ALBUM_ART
        )

        context.contentResolver.query(
            MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums._ID)
            val albumColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM)
            val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ARTIST)
            val albumArtColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ART)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val album = cursor.getString(albumColumn)
                val artist = cursor.getString(artistColumn)
                val albumArt = cursor.getString(albumArtColumn)
                val albumArtUri = if (albumArt != null) Uri.parse(albumArt) else null

                albumsList.add(Album(id, album, artist, albumArtUri))
            }
        }
        return albumsList
    }

    fun getArtists(): List<Artist> {
        val artistsList = mutableListOf<Artist>()
        val projection = arrayOf(
            MediaStore.Audio.Artists._ID,
            MediaStore.Audio.Artists.ARTIST
        )

        context.contentResolver.query(
            MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Artists._ID)
            val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Artists.ARTIST)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val artist = cursor.getString(artistColumn)

                artistsList.add(Artist(id, artist))
            }
        }
        return artistsList
    }

    fun getPhotos(): List<com.example.ipodlauncher.data.Photo> {
        val photoList = mutableListOf<com.example.ipodlauncher.data.Photo>()
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME
        )

        context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val title = cursor.getString(titleColumn)
                val uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id.toString())

                photoList.add(com.example.ipodlauncher.data.Photo(id, title, uri))
            }
        }
        return photoList
    }

    fun getVideos(): List<com.example.ipodlauncher.data.Video> {
        val videoList = mutableListOf<com.example.ipodlauncher.data.Video>()
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION
        )

        context.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val title = cursor.getString(titleColumn)
                val duration = cursor.getLong(durationColumn)
                val uri = Uri.withAppendedPath(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id.toString())

                videoList.add(com.example.ipodlauncher.data.Video(id, title, duration, uri))
            }
        }
        return videoList
    }
}