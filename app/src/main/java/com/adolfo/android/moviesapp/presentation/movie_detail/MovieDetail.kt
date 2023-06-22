package com.adolfo.android.moviesapp.presentation.movie_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.adolfo.android.moviesapp.domain.model.UiMovie
import com.adolfo.android.moviesapp.presentation.movie_detail.components.MovieDetailSuccess

@Composable
fun MovieDetail(
    movieDetailUiState: MovieDetailUiState,
    onPopUp: () -> Unit,
    onCartClick: (UiMovie, Boolean) -> Unit,
    isMovieInCart: Boolean
) {

    if (movieDetailUiState.loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else if (movieDetailUiState.isSuccess) {
        movieDetailUiState.movie?.let {
            MovieDetailSuccess(
                uiMovie = it,
                onPopUp = onPopUp,
                movieDetailUiState = movieDetailUiState,
                onCartClick = {movieCart, isInCart ->
                    onCartClick(movieCart, isInCart)
                },
                isMovieInCart = isMovieInCart
            )
        }
    }
}
