package com.adolfo.android.moviesapp.data.api.dto.home


data class Movie(
    val backdrop_path: String?,
    val id: Int,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String?,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)