package de.peacemoon.androidcourse.photogallery

import java.io.Serializable

data class Image(val id: String,
            val pageURL: String?,
            val previewURL: String?,
            val largeImageURL: String?): Serializable {}

data class ImageList(val total: Int,
                val totalHits: Int,
                val hits: Array<Image>): Serializable {}