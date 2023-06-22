package com.adolfo.android.moviesapp.data.local.cart.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieCartEntity(
    @PrimaryKey val id: Int,
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
