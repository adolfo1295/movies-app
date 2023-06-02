package com.adolfo.android.moviesapp.domain.repository

import com.adolfo.android.moviesapp.domain.model.UiCredits
import com.adolfo.android.moviesapp.domain.model.UiMovie

interface MovieRepository {
    suspend fun getHomeMovies(): Result<List<UiMovie>>
    suspend fun getMovieDetail(movieId: String): Result<UiMovie>
    suspend fun getMovieCredits(movieId: String): Result<UiCredits>
}