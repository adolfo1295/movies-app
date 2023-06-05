package com.adolfo.android.moviesapp.domain.mapper

import com.adolfo.android.moviesapp.data.api.dto.credits.MovieCreditsResponseDto
import com.adolfo.android.moviesapp.data.api.dto.detail.MovieDetailResponseDto
import com.adolfo.android.moviesapp.data.api.dto.home.Movie
import com.adolfo.android.moviesapp.domain.model.UiCredits
import com.adolfo.android.moviesapp.domain.model.UiMovie

fun Movie.toUiMovie(): UiMovie {
    return UiMovie(
        title = this.title,
        id = this.id,
        backdropPath = this.backdrop_path,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.poster_path,
        releaseDate = this.release_date,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        price = 0.0
    )
}

fun MovieDetailResponseDto.toUiMovie(price: Double): UiMovie {
    return UiMovie(
        id = this.id,
        backdropPath = this.backdrop_path,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.poster_path,
        releaseDate = this.release_date,
        title = this.title,
        voteAverage = this.vote_average,
        voteCount = this.vote_count,
        price = price
    )
}

fun MovieCreditsResponseDto.toUiCredits(): UiCredits {
    return UiCredits(
        cast = this.cast,
    )
}