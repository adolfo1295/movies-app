package com.adolfo.android.moviesapp.domain.model

import androidx.room.PrimaryKey

data class UiMovieCart(
    val id: Int,
    val backdropPath: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val price: Double,
    val favorite: Boolean = false
)