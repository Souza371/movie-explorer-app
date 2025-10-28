package com.movieexplorer.util

/**
 * Classe selada para representar os diferentes estados de uma operação de rede
 */
sealed class NetworkResult<T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error<T>(val message: String) : NetworkResult<T>()
    data class Loading<T>(val message: String = "Carregando...") : NetworkResult<T>()
}

/**
 * Estados específicos para operações de filmes
 */
sealed class MovieState {
    object Idle : MovieState()
    object Loading : MovieState()
    data class Success(val movies: List<com.movieexplorer.data.Movie>) : MovieState()
    data class Error(val message: String) : MovieState()
}

/**
 * Estados para detalhes de filmes
 */
sealed class MovieDetailsState {
    object Idle : MovieDetailsState()
    object Loading : MovieDetailsState()
    data class Success(val details: com.movieexplorer.data.MovieDetails) : MovieDetailsState()
    data class Error(val message: String) : MovieDetailsState()
}