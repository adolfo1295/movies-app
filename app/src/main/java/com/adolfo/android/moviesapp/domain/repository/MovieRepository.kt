package com.adolfo.android.moviesapp.domain.repository

import com.adolfo.android.moviesapp.data.local.cart.entity.MovieCartEntity
import com.adolfo.android.moviesapp.domain.model.UiCredits
import com.adolfo.android.moviesapp.domain.model.UiMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getHomeMovies(): Result<List<UiMovie>>
    suspend fun getMovieDetail(movieId: String): Result<UiMovie>
    suspend fun getMovieCredits(movieId: String): Result<UiCredits>
    suspend fun addMovieToCart(movieCartEntity: MovieCartEntity)
    fun getMovieCart(): Flow<List<MovieCartEntity>>
}