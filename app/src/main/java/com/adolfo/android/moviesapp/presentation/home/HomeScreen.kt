@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)

package com.adolfo.android.moviesapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adolfo.android.moviesapp.domain.model.UiMovie
import com.adolfo.android.moviesapp.presentation.home.components.CollapsingTopAppBar
import com.adolfo.android.moviesapp.presentation.home.components.MovieItem

@Composable
fun HomeScreen(
    homeState: HomeUiState, onMovieClick: (UiMovie) -> Unit, onPopularBannerClick: (UiMovie) -> Unit
) {
    val lazyScrollState = rememberLazyStaggeredGridState()
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .navigationBarsPadding()
        .background(MaterialTheme.colorScheme.surface),
        topBar = {
            if (homeState.movies.isNotEmpty()) {
                CollapsingTopAppBar(
                    lazyScrollState = lazyScrollState,
                    movie = homeState.movies[0],
                    onActionPopularMovieClick = { movie ->
                        onPopularBannerClick(movie)
                    }
                )
            }
        }) { padding ->
        if (homeState.loading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                val movieList = homeState.movies
                if (movieList.isNotEmpty()) {
                    LazyVerticalStaggeredGrid(
                        state = lazyScrollState,
                        columns = StaggeredGridCells.Fixed(2),
                        verticalItemSpacing = 4.dp,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        content = {
                            itemsIndexed(movieList) { index, item ->
                                MovieItem(movie = item, onMovieClick = {
                                    onMovieClick(it)
                                })
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

