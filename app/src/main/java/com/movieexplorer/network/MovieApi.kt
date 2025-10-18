package com.movieexplorer.network

import com.movieexplorer.data.MovieDetails
import com.movieexplorer.data.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface da API OMDb para operações de busca de filmes
 */
interface MovieApi {
    
    /**
     * Busca filmes por título
     * @param apiKey Chave da API OMDb
     * @param query Termo de busca
     * @param page Página dos resultados (opcional)
     */
    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") query: String,
        @Query("page") page: Int = 1
    ): MovieSearchResponse
    
    /**
     * Obtém detalhes completos de um filme pelo ID do IMDb
     * @param apiKey Chave da API OMDb
     * @param imdbId ID do filme no IMDb
     */
    @GET("/")
    suspend fun getMovieDetails(
        @Query("apikey") apiKey: String,
        @Query("i") imdbId: String
    ): MovieDetails
}