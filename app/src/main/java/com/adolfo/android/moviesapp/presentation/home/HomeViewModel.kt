package com.adolfo.android.moviesapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adolfo.android.moviesapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<HomeUiState>(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getHomeMovies()
    }

    fun getHomeMovies() {
        _uiState.value = HomeUiState(loading = true)
        viewModelScope.launch {
            delay(1.seconds)
            movieRepository.getHomeMovies()
                .onSuccess { movies ->
                    val list = movies
                    println(list)
                    _uiState.update {
                        it.copy(movies = list, loading = false)
                    }
                }
                .onFailure { error ->
                    println(error)
                    _uiState.update {
                        it.copy(movies = emptyList(), loading = false)
                    }
                }
        }
    }
}
