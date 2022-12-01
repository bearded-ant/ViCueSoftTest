package com.tests.vicuesofttest.domain

import com.squareup.moshi.Json

data class DataResponse(
    val color: Any?,
    @Json(name = "file_url")
    val fileUrl: String,
    val group: String,
    val id: String,
    @Json(name = "is_favorite")
    val isFavorite: Boolean,
    @Json(name = "poster_url")
    val posterUrl: String,
    @Json(name = "small_poster_url")
    val smallPosterUrl: String,
    @Json(name = "small_thumbnail_url")
    val smallThumbnailUrl: String,
    @Json(name = "thumbnail_url")
    val thumbnailUrl: String
)