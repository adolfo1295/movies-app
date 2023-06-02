package com.adolfo.android.moviesapp.data.api.dto.credits


import com.squareup.moshi.Json

data class Cast(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "cast_id")
    val cast_id: Int,
    @Json(name = "character")
    val character: String,
    @Json(name = "credit_id")
    val credit_id: String,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "known_for_department")
    val known_for_department: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "order")
    val order: Int,
    @Json(name = "original_name")
    val original_name: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "profile_path")
    val profile_path: String?
)