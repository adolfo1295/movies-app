package com.adolfo.android.moviesapp.domain.model

data class UiMovie(
    val backdropPath: String?,
    val id: Int,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val price: Double,
    val favorite: Boolean = false
)
