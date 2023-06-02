package com.adolfo.android.moviesapp.data.api.dto.credits


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class MovieCreditsResponseDto(
    @Json(name = "cast")
    val cast: List<Cast>,
    @Json(name = "id")
    val id: Int
)