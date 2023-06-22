package com.adolfo.android.moviesapp.domain.use_case

import com.adolfo.android.moviesapp.domain.mapper.toUiMovie
import com.adolfo.android.moviesapp.domain.model.UiMovie
import com.adolfo.android.moviesapp.domain.model.UiMovieCart
import com.adolfo.android.moviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMoviesInCartUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    fun invoke(): Flow<List<UiMovie>> {
        return movieRepository.getMovieCart().map {cartList ->
            cartList.map {
                it.toUiMovie()
            }
        }
    }
}