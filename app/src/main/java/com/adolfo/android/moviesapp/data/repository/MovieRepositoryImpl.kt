package com.adolfo.android.moviesapp.data.repository

import com.adolfo.android.moviesapp.data.api.MovieService
import com.adolfo.android.moviesapp.data.local.cart.MovieCartDatabase
import com.adolfo.android.moviesapp.data.local.cart.dao.MovieCartDao
import com.adolfo.android.moviesapp.data.local.cart.entity.MovieCartEntity
import com.adolfo.android.moviesapp.domain.mapper.toUiCredits
import com.adolfo.android.moviesapp.domain.mapper.toUiMovie
import com.adolfo.android.moviesapp.domain.model.UiCredits
import com.adolfo.android.moviesapp.domain.model.UiMovie
import com.adolfo.android.moviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.random.Random

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val movieDatabase: MovieCartDao
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
            Result.success(response.toUiMovie(generateRandomPrice()))
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

    override suspend fun addMovieToCart(movieCartEntity: MovieCartEntity) {
        movieDatabase.addMovieToCart(movieCartEntity)
    }

    override fun getMovieCart(): Flow<List<MovieCartEntity>> {
        return movieDatabase.getMovieCart()
    }

    private fun generateRandomPrice(): Double {
        return Random.nextDouble(120.0, 300.0)
    }
}
