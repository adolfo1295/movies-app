package com.adolfo.android.moviesapp.presentation.home

import com.adolfo.android.moviesapp.domain.model.UiMovie
import com.adolfo.android.moviesapp.util.UiText

data class HomeUiState(
    val movies: List<UiMovie> = emptyList(),
    val error: UiText? = null,
    val loading: Boolean = false
)
