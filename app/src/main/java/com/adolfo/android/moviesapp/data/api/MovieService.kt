package com.adolfo.android.moviesapp.data.api

import com.adolfo.android.moviesapp.data.api.dto.credits.MovieCreditsResponseDto
import com.adolfo.android.moviesapp.data.api.dto.detail.MovieDetailResponseDto
import com.adolfo.android.moviesapp.data.api.dto.home.HomeMoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getHomeMovies(): HomeMoviesResponseDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String
    ): MovieDetailResponseDto

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movieId: String
    ): MovieCreditsResponseDto

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "e9ba2bff1c00cd46a61c1896636fa978"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
    }

}