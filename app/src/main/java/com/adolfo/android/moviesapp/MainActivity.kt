package com.adolfo.android.moviesapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adolfo.android.moviesapp.presentation.home.HomeScreen
import com.adolfo.android.moviesapp.presentation.home.HomeViewModel
import com.adolfo.android.moviesapp.presentation.movie_detail.MovieDetail
import com.adolfo.android.moviesapp.presentation.movie_detail.MovieDetailViewModel
import com.adolfo.android.moviesapp.routes.Routes
import com.adolfo.android.moviesapp.ui.theme.MoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme {
                val homeViewModel: HomeViewModel = hiltViewModel()
                val homeState by homeViewModel.uiState.collectAsStateWithLifecycle()
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.Home
                ) {
                    composable(route = Routes.Home) {
                        HomeScreen(homeState, onMovieClick = { movie ->
                            navController.navigate(Routes.MovieDetail + movie.id) {
                                popUpTo(Routes.Home)
                            }
                        }, onPopularBannerClick = { movie ->
                            navController.navigate(Routes.MovieDetail + movie.id) {
                                popUpTo(Routes.Home)
                            }
                        })
                    }
                    composable(
                        route = Routes.MovieDetail + "{movieId}",
                        arguments = listOf(navArgument("movieId") { type = NavType.StringType })
                    ) { navBackStackEntry ->
                        navBackStackEntry.arguments?.getString("movieId")!!
                        val movieDetailViewModel = hiltViewModel<MovieDetailViewModel>()
                        val movieDetailUiState by movieDetailViewModel.movieDetailUiState.collectAsStateWithLifecycle()
                        MovieDetail(
                            movieDetailUiState = movieDetailUiState,
                            onPopUp = {
                                navController.popBackStack()
                            })
                    }
                }
            }
        }
    }
}