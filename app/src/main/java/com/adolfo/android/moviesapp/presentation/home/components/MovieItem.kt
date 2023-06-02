package com.adolfo.android.moviesapp.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adolfo.android.moviesapp.data.api.MovieService
import com.adolfo.android.moviesapp.domain.model.UiMovie

@Composable
fun MovieItem(
    movie: UiMovie,
    onMovieClick: (UiMovie) -> Unit
) {
    movie.posterPath?.let { poster ->
        if (poster.isNotBlank()) {
            AsyncImage(
                contentScale = ContentScale.Crop,
                model = MovieService.IMAGE_URL + poster,
                contentDescription = "poster",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable {
                        onMovieClick(movie)
                    }
            )
        }
    }
}