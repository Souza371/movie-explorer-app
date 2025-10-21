package com.movieexplorer.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieexplorer.data.Movie
import com.movieexplorer.data.MovieDetails
import com.movieexplorer.network.RetrofitClient
import kotlinx.coroutines.launch

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
     * Busca filmes na API OMDb com validação de entrada.
     * 
     * Funcionalidades:
     * - Valida se a query tem pelo menos 2 caracteres
     * - Faz trim() para remover espaços
     * - Trata diferentes tipos de erros de rede
     * - Atualiza estados reativo de loading e erro
     */
    fun searchMovies() {
        val trimmedQuery = query.trim()
        if (trimmedQuery.isBlank()) {
            errorMessage = "Digite um título para buscar"
            return
        }
        
        if (trimmedQuery.length < 2) {
            errorMessage = "Digite pelo menos 2 caracteres"
            return
        }
        
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                val response = RetrofitClient.movieApi.searchMovies(query = trimmedQuery)
                
                if (response.response == "True") {
                    movies = response.search ?: emptyList()
                    if (movies.isEmpty()) {
                        errorMessage = "Nenhum resultado encontrado para \"$query\""
                    }
                } else {
                    errorMessage = response.error ?: "Erro na busca"
                    movies = emptyList()
                }
            } catch (e: java.net.UnknownHostException) {
                errorMessage = "Erro de conexão. Verifique sua internet."
                movies = emptyList()
            } catch (e: java.net.SocketTimeoutException) {
                errorMessage = "Timeout na conexão. Tente novamente."
                movies = emptyList()
            } catch (e: Exception) {
                errorMessage = "Erro inesperado: ${e.message}"
                movies = emptyList()
            } finally {
                isLoading = false
            }
        }
    }
    
    /**
     * Obtém detalhes completos de um filme
     */
    fun getMovieDetails(imdbId: String) {
        viewModelScope.launch {
            isLoadingDetails = true
            
            try {
                val details = RetrofitClient.movieApi.getMovieDetails(imdbId = imdbId)
                
                if (details.response == "True") {
                    selectedMovie = details
                } else {
                    errorMessage = "Erro ao carregar detalhes do filme"
                }
            } catch (e: java.net.UnknownHostException) {
                errorMessage = "Erro de conexão ao carregar detalhes"
            } catch (e: java.net.SocketTimeoutException) {
                errorMessage = "Timeout ao carregar detalhes. Tente novamente."
            } catch (e: Exception) {
                errorMessage = "Erro inesperado ao carregar detalhes: ${e.message}"
            } finally {
                isLoadingDetails = false
            }
        }
    }
    
    /**
     * Limpa o filme selecionado (fecha a tela de detalhes)
     */
    fun clearSelectedMovie() {
        selectedMovie = null
    }
    
    /**
     * Limpa a mensagem de erro
     */
    fun clearError() {
        errorMessage = null
    }
}