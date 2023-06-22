@file:OptIn(ExperimentalMotionApi::class)

package com.adolfo.android.moviesapp.presentation.home.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import coil.compose.AsyncImage
import com.adolfo.android.moviesapp.R
import com.adolfo.android.moviesapp.data.api.MovieService
import com.adolfo.android.moviesapp.domain.model.UiMovie

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CollapsingTopAppBar(
    lazyScrollState: LazyStaggeredGridState,
    movie: UiMovie,
    onActionPopularMovieClick: (UiMovie) -> Unit,
    onCartClick: () -> Unit
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene_bar)
            .readBytes()
            .decodeToString()
    }
    val scroll = remember { derivedStateOf { lazyScrollState.firstVisibleItemIndex } }.value
    val progress by animateFloatAsState(
        targetValue = if (scroll in 0..0) 0f else 1f,
        label = ""
    )
    val motionHeight by animateDpAsState(
        targetValue = if (scroll in 0..0) 300.dp else 60.dp,
        label = ""
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
                                    0.5f to Color.Transparent, 1f to Color.Black
                                )
                            )
                        }
                    }
                    .layoutId("movie_image")
            )
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                ),
                modifier = Modifier.layoutId("cart_button"),
                onClick = onCartClick
            ) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = null)
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                modifier = Modifier
                    .layoutId("action_button")
                    .clip(RoundedCornerShape(6.dp)),
                onClick = {
                    onActionPopularMovieClick(movie)
                }) {
                Text(text = "Ver pelicula")
            }
            Text(
                textAlign = TextAlign.Center,
                text = if (progress == 0f) movie.title else "Now playing",
                fontSize = textSize.value.fontSize("txt_size"),
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("movie_name"),
            )
        }
    }
}