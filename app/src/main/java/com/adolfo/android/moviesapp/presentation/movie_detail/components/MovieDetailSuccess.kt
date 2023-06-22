@file:OptIn(ExperimentalMaterial3Api::class)

package com.adolfo.android.moviesapp.presentation.movie_detail.components

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.adolfo.android.moviesapp.domain.model.UiMovie
import com.adolfo.android.moviesapp.presentation.movie_detail.MovieDetailUiState

@Composable
fun MovieDetailSuccess(
    uiMovie: UiMovie,
    onPopUp: () -> Unit,
    movieDetailUiState: MovieDetailUiState,
    onCartClick: (UiMovie, Boolean) -> Unit,
    isMovieInCart: Boolean
) {

    val context = LocalContext.current
    val lazyListState = rememberLazyListState()
    val scrollIndex = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }.value

    val progress by animateFloatAsState(
        targetValue = if (uiMovie.overview.length < 100) {
            if (scrollIndex < 2) 0f else 1f
        } else {
            if (scrollIndex < 1) 0f else 1f
        },
        tween(500), label = ""
    )
    val motionHeight by animateDpAsState(
        targetValue = if (uiMovie.overview.length < 100) {
            if (scrollIndex < 2) 350.dp else 56.dp
        } else {
            if (scrollIndex < 1) 350.dp else 56.dp
        },
        tween(500), label = ""
    )

    Log.d("Detail", "Is in cart: $isMovieInCart")

    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        topBar = {
            CollapsingMovieDetailTopBar(
                movie = uiMovie,
                onPopUp = onPopUp,
                progress = progress,
                motionHeight = motionHeight
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .padding(padding)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                item {
                    MovieTitleComponent(movie = uiMovie)
                }
                item {
                    MovieDescriptionComponent(movie = uiMovie)
                }
                item {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        movieDetailUiState.movieCredits?.let {
                            items(it.cast) {
                                CreditsItem(cast = it)
                            }
                        }
                    }
                }
                item {
                    movieDetailUiState.movie?.let {
                        MovieDescriptionComponent(movie = it)
                    }
                }
                item {
                    movieDetailUiState.movie?.let {
                        MovieDescriptionComponent(movie = it)
                    }
                }
                item {
                    movieDetailUiState.movie?.let {
                        MovieDescriptionComponent(movie = it)
                    }
                }
            }
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .navigationBarsPadding(),
                onClick = {
                    onCartClick(uiMovie, isMovieInCart)
                }) {
                Icon(
                    imageVector = if (isMovieInCart)
                        Icons.Default.ShoppingCart else Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    }

}