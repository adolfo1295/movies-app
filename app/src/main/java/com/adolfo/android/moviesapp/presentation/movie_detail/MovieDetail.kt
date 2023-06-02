@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.adolfo.android.moviesapp.presentation.movie_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adolfo.android.moviesapp.presentation.movie_detail.components.CollapsingMovieDetailTopBar
import com.adolfo.android.moviesapp.presentation.movie_detail.components.CreditsItem
import com.adolfo.android.moviesapp.presentation.movie_detail.components.MovieDescriptionComponent
import com.adolfo.android.moviesapp.presentation.movie_detail.components.MovieTitleComponent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetail(
    movieDetailUiState: MovieDetailUiState,
    onPopUp: () -> Unit
) {

    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = {
            movieDetailUiState.movie?.let {
                CollapsingMovieDetailTopBar(
                    movie = it,
                    onPopUp = onPopUp,
                    lazyListState = lazyListState
                )
            }
        }
    ) { padding ->

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .padding(padding)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                item {
                    movieDetailUiState.movie?.let {
                        MovieTitleComponent(movie = it)
                    }
                }
                item {
                    movieDetailUiState.movie?.let {
                        MovieDescriptionComponent(movie = it)
                    }
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
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                onClick = { /*TODO*/ }) {
                Text(text = "Ver más")
            }
        }
    }

}
