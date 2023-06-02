package com.adolfo.android.moviesapp.data.repository

import com.adolfo.android.moviesapp.data.api.MovieService
import com.adolfo.android.moviesapp.domain.mapper.toUiCredits
import com.adolfo.android.moviesapp.domain.mapper.toUiMovie
import com.adolfo.android.moviesapp.domain.model.UiCredits
import com.adolfo.android.moviesapp.domain.model.UiMovie
import com.adolfo.android.moviesapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRepository {
    override suspend fun getHomeMovies(): Result<List<UiMovie>> {
        return try {
            val movies = movieService.getHomeMovies().results
            Result.success(movies.map { it.toUiMovie() })
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getMovieDetail(movieId: String): Result<UiMovie> {
        return try {
            val response = movieService.getMovieDetail(movieId = movieId)
            Result.success(response.toUiMovie())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getMovieCredits(movieId: String): Result<UiCredits> {
        return try {
            val response = movieService.getCredits(movieId = movieId)
            Result.success(response.toUiCredits())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}
