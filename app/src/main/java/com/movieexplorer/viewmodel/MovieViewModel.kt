package com.movieexplorer.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieexplorer.data.Movie
import com.movieexplorer.data.MovieDetails
import com.movieexplorer.network.RetrofitClient
import com.movieexplorer.util.MovieState
import com.movieexplorer.util.MovieDetailsState
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

/**
 * ViewModel para gerenciar o estado da aplicação Movie Explorer.
 * 
 * Responsável por:
 * - Gerenciar estados de busca, loading e erros
 * - Fazer chamadas para a API OMDb
 * - Manter dados de filmes e detalhes
 * - Validar entrada do usuário
 * 
 * @author Vicente
 * @since 1.0
 */
class MovieViewModel : ViewModel() {
    
    // Estados reativos da UI
    var movies by mutableStateOf<List<Movie>>(emptyList())
        private set
    
    var query by mutableStateOf("")
        private set
    
    var isLoading by mutableStateOf(false)
        private set
    
    var errorMessage by mutableStateOf<String?>(null)
        private set
    
    var selectedMovie by mutableStateOf<MovieDetails?>(null)
        private set
    
    var isLoadingDetails by mutableStateOf(false)
        private set
    
    // Estados melhorados usando sealed classes
    var movieState by mutableStateOf<MovieState>(MovieState.Idle)
        private set
    
    var movieDetailsState by mutableStateOf<MovieDetailsState>(MovieDetailsState.Idle)
        private set
    
    // Contador de tentativas para retry automático
    private var searchRetryCount = 0
    private val maxRetries = 3
    
    /**
     * Atualiza o termo de busca e limpa erros anteriores.
     * 
     * @param newQuery Nova string de busca inserida pelo usuário
     */
    fun updateQuery(newQuery: String) {
        query = newQuery
        // Limpa a mensagem de erro quando o usuário começa a digitar
        if (errorMessage != null) {
            errorMessage = null
        }
    }
    
    /**
     * Busca filmes na API OMDb com validação de entrada e retry automático.
     * 
     * Funcionalidades:
     * - Valida se a query tem pelo menos 2 caracteres
     * - Faz trim() para remover espaços
     * - Trata diferentes tipos de erros de rede
     * - Retry automático em caso de falha de rede
     * - Atualiza estados reativo de loading e erro
     */
    fun searchMovies() {
        val trimmedQuery = query.trim()
        if (trimmedQuery.isBlank()) {
            errorMessage = "Digite um título para buscar"
            movieState = MovieState.Error("Digite um título para buscar")
            return
        }
        
        if (trimmedQuery.length < 2) {
            errorMessage = "Digite pelo menos 2 caracteres"
            movieState = MovieState.Error("Digite pelo menos 2 caracteres")
            return
        }
        
        searchRetryCount = 0
        performSearch(trimmedQuery)
    }
    
    /**
     * Executa a busca com retry automático
     */
    private fun performSearch(query: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            movieState = MovieState.Loading
            
            try {
                val response = RetrofitClient.movieApi.searchMovies(query = query)
                
                if (response.response == "True") {
                    val movieList = response.search ?: emptyList()
                    movies = movieList
                    movieState = MovieState.Success(movieList)
                    
                    if (movieList.isEmpty()) {
                        errorMessage = "Nenhum resultado encontrado para \"$query\""
                        movieState = MovieState.Error("Nenhum resultado encontrado")
                    }
                    searchRetryCount = 0 // Reset counter on success
                } else {
                    val error = response.error ?: "Erro na busca"
                    errorMessage = error
                    movies = emptyList()
                    movieState = MovieState.Error(error)
                }
            } catch (e: java.net.UnknownHostException) {
                handleNetworkError("Erro de conexão. Verifique sua internet.", query)
            } catch (e: java.net.SocketTimeoutException) {
                handleNetworkError("Timeout na conexão.", query)
            } catch (e: Exception) {
                val error = "Erro inesperado: ${e.message}"
                errorMessage = error
                movies = emptyList()
                movieState = MovieState.Error(error)
            } finally {
                isLoading = false
            }
        }
    }
    
    /**
     * Trata erros de rede com retry automático
     */
    private suspend fun handleNetworkError(message: String, query: String) {
        if (searchRetryCount < maxRetries) {
            searchRetryCount++
            errorMessage = "$message Tentativa $searchRetryCount/$maxRetries..."
            delay(2000) // Aguarda 2 segundos antes de tentar novamente
            performSearch(query)
        } else {
            errorMessage = "$message Todas as tentativas falharam."
            movies = emptyList()
            movieState = MovieState.Error(message)
            searchRetryCount = 0
        }
    }
    
    /**
     * Obtém detalhes completos de um filme com retry automático
     */
    fun getMovieDetails(imdbId: String) {
        viewModelScope.launch {
            isLoadingDetails = true
            movieDetailsState = MovieDetailsState.Loading
            
            var detailsRetryCount = 0
            var success = false
            
            while (!success && detailsRetryCount <= maxRetries) {
                try {
                    val details = RetrofitClient.movieApi.getMovieDetails(imdbId = imdbId)
                    
                    if (details.response == "True") {
                        selectedMovie = details
                        movieDetailsState = MovieDetailsState.Success(details)
                        success = true
                    } else {
                        val error = "Erro ao carregar detalhes do filme"
                        errorMessage = error
                        movieDetailsState = MovieDetailsState.Error(error)
                        break
                    }
                } catch (e: java.net.UnknownHostException) {
                    detailsRetryCount++
                    if (detailsRetryCount <= maxRetries) {
                        errorMessage = "Erro de conexão. Tentativa $detailsRetryCount/$maxRetries..."
                        delay(1500)
                    } else {
                        val error = "Erro de conexão ao carregar detalhes"
                        errorMessage = error
                        movieDetailsState = MovieDetailsState.Error(error)
                    }
                } catch (e: java.net.SocketTimeoutException) {
                    detailsRetryCount++
                    if (detailsRetryCount <= maxRetries) {
                        errorMessage = "Timeout. Tentativa $detailsRetryCount/$maxRetries..."
                        delay(1500)
                    } else {
                        val error = "Timeout ao carregar detalhes"
                        errorMessage = error
                        movieDetailsState = MovieDetailsState.Error(error)
                    }
                } catch (e: Exception) {
                    val error = "Erro inesperado ao carregar detalhes: ${e.message}"
                    errorMessage = error
                    movieDetailsState = MovieDetailsState.Error(error)
                    break
                }
            }
            
            isLoadingDetails = false
        }
    }
    
    /**
     * Limpa o filme selecionado (fecha a tela de detalhes)
     */
    fun clearSelectedMovie() {
        selectedMovie = null
        movieDetailsState = MovieDetailsState.Idle
    }
    
    /**
     * Limpa a mensagem de erro
     */
    fun clearError() {
        errorMessage = null
    }
    
    /**
     * Limpa todos os estados e dados
     */
    fun clearAllData() {
        movies = emptyList()
        query = ""
        selectedMovie = null
        errorMessage = null
        movieState = MovieState.Idle
        movieDetailsState = MovieDetailsState.Idle
        searchRetryCount = 0
    }
    
    /**
     * Força uma nova busca (útil para pull-to-refresh)
     */
    fun forceRefresh() {
        if (query.isNotBlank()) {
            searchMovies()
        }
    }
}