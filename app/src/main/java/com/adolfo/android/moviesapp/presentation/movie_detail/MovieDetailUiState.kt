package com.adolfo.android.moviesapp.presentation.movie_detail

import com.adolfo.android.moviesapp.domain.model.UiCredits
import com.adolfo.android.moviesapp.domain.model.UiMovie
import com.adolfo.android.moviesapp.util.UiText

data class MovieDetailUiState(
    val movie: UiMovie? = null,
    val loading: Boolean = false,
    val error: UiText? = null,
    val isSuccess: Boolean = false,
    val movieCredits: UiCredits? = UiCredits(emptyList())
)
