package com.adolfo.android.moviesapp.data.api.dto.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class MovieDetailResponseDto(
    @Json(name = "backdrop_path")
    val backdrop_path: String,
    @Json(name = "budget")
    val budget: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val poster_path: String,
    @Json(name = "release_date")
    val release_date: String,
    @Json(name = "revenue")
    val revenue: Int,
    @Json(name = "status")
    val status: String,
    @Json(name = "tagline")
    val tagline: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "video")
    val video: Boolean,
    @Json(name = "vote_average")
    val vote_average: Double,
    @Json(name = "vote_count")
    val vote_count: Int
)