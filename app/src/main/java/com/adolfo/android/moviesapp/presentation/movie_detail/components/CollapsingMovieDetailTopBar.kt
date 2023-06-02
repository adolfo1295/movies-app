@file:OptIn(ExperimentalMotionApi::class)

package com.adolfo.android.moviesapp.presentation.movie_detail.components

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import coil.compose.AsyncImage
import com.adolfo.android.moviesapp.R
import com.adolfo.android.moviesapp.data.api.MovieService
import com.adolfo.android.moviesapp.domain.model.UiMovie

@Composable
fun CollapsingMovieDetailTopBar(
    lazyListState: LazyListState,
    movie: UiMovie,
    onPopUp: () -> Unit,
) {

    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene_movie_detail_bar)
            .readBytes()
            .decodeToString()
    }


    val scrollIndex = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }.value

    val progress by animateFloatAsState(
        targetValue = if (movie.overview.length < 100) {
            if (scrollIndex < 2) 0f else 1f
        } else {
            if (scrollIndex < 1) 0f else 1f
        },
        tween(500), label = ""
    )
    val motionHeight by animateDpAsState(
        targetValue = if (movie.overview.length < 100) {
            if (scrollIndex < 2) 350.dp else 60.dp
        } else {
            if (scrollIndex < 1) 350.dp else 60.dp
        },
        tween(500), label = ""
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .height(motionHeight)
        ) {
            val textSize = motionProperties("movie_name")

            Box(
                modifier = Modifier
                    .background(Color.Transparent)
                    .layoutId("background")
            )
            AsyncImage(
                contentScale = ContentScale.Crop,
                model = MovieService.IMAGE_URL + movie.backdropPath,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(
                                Brush.verticalGradient(
                                    0f to Color.Black, 0.2f to Color.Transparent
                                )
                            )
                        }
                    }
                    .layoutId("movie_image")
            )
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .layoutId("up_button")
                    .clip(RoundedCornerShape(50.dp)),
                onClick = onPopUp
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
            Text(
                modifier = Modifier.layoutId("movie_name"),
                text = movie.title,
                fontSize = textSize.value.fontSize("txt_size"),
                maxLines = 1,
                overflow = TextOverflow.Clip,
                style = MaterialTheme.typography.titleMedium
            )
            FloatingActionButton(
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .offset(y = 16.dp)
                    .layoutId("action_button"),
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    }
}
