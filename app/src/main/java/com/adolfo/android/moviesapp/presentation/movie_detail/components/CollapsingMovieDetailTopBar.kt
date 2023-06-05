@file:OptIn(ExperimentalMotionApi::class)

package com.adolfo.android.moviesapp.presentation.movie_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.Dp
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
    movie: UiMovie,
    onPopUp: () -> Unit,
    progress: Float,
    motionHeight: Dp
) {

    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene_movie_detail_bar)
            .readBytes()
            .decodeToString()
    }


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .height(motionHeight)
        ) {
            val textSize = motionProperties("movie_name")

            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
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
                    contentColor = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier
                    .layoutId("up_button")
                    .clip(RoundedCornerShape(50.dp)),
                onClick = onPopUp
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
            Text(
                color = MaterialTheme.colorScheme.onSurface,
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
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = null
                )
            }
        }
    }
}
