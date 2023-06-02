package com.adolfo.android.moviesapp.presentation.movie_detail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adolfo.android.moviesapp.domain.model.UiMovie

@Composable
fun MovieTitleComponent(
    movie: UiMovie
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 32.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 10.dp
            )
    ) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        AnimatedVisibility(visible = !movie.releaseDate.isNullOrEmpty()) {
            IconTextComponent(
                data = movie.releaseDate.orEmpty(),
                icon = Icons.Filled.DateRange
            )
        }
        IconTextComponent(
            data = movie.voteAverage.toString(),
            icon = Icons.Filled.Star
        )
    }
}