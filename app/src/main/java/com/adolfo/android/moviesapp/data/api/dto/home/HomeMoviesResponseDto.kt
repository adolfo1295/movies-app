package com.adolfo.android.moviesapp.data.api.dto.home


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class HomeMoviesResponseDto(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)