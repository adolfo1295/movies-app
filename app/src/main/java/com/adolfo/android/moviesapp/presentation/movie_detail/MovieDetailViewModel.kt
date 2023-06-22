package com.adolfo.android.moviesapp.presentation.movie_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adolfo.android.moviesapp.domain.mapper.toEntity
import com.adolfo.android.moviesapp.domain.model.UiMovie
import com.adolfo.android.moviesapp.domain.model.UiMovieCart
import com.adolfo.android.moviesapp.domain.repository.MovieRepository
import com.adolfo.android.moviesapp.domain.use_case.GetMoviesInCartUseCase
import com.adolfo.android.moviesapp.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle,
    private val getMoviesInCartUseCase: GetMoviesInCartUseCase
) : ViewModel() {

    private val movieId = checkNotNull(savedStateHandle.get<String>("movieId"))
    var _movieCartList = getMoviesInCartUseCase.invoke()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private var _movieDetailUiState = MutableStateFlow(MovieDetailUiState())

    val movieDetailUiState =
        combine(_movieDetailUiState, _movieCartList) { movieDetailUiState, uiMovieCarts ->
            movieDetailUiState.copy(
                moviesInCart = uiMovieCarts
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MovieDetailUiState())


    init {
        if (movieId.isNotEmpty()) {
            getAllMovieData()
        }
    }

    private fun getAllMovieData() {
        _movieDetailUiState.value = MovieDetailUiState(loading = true)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val movieDetailResponse = movieRepository.getMovieDetail(movieId)
                val movieCreditsResponse = movieRepository.getMovieCredits(movieId)

                if (movieDetailResponse.isSuccess && movieCreditsResponse.isSuccess) {
                    val movieD = movieDetailResponse.getOrNull()
                    val movieCredits = movieCreditsResponse.getOrNull()
                    _movieDetailUiState.update {
                        it.copy(
                            loading = false,
                            movie = movieD,
                            isSuccess = true,
                            movieCredits = movieCredits
                        )
                    }
                } else {
                    _movieDetailUiState.update {
                        it.copy(
                            loading = false,
                            error = UiText.DynamicString("Api error")
                        )
                    }
                }
            }
        }
    }

    fun addMovieToCart(uiMovie: UiMovie) {
        viewModelScope.launch {
            movieRepository.addMovieToCart(uiMovie.toEntity())
        }
    }
}
