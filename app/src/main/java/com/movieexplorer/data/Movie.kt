package com.movieexplorer.data

import com.google.gson.annotations.SerializedName

/**
 * Data class para representar um filme na resposta da API OMDb
 */
data class Movie(
    @SerializedName("Title")
    val title: String,
    
    @SerializedName("Year")
    val year: String,
    
    @SerializedName("imdbID")
    val imdbID: String,
    
    @SerializedName("Poster")
    val poster: String?,
    
    @SerializedName("Type")
    val type: String? = null
)

/**
 * Data class para a resposta da busca de filmes da API OMDb
 */
data class MovieSearchResponse(
    @SerializedName("Search")
    val search: List<Movie>?,
    
    @SerializedName("totalResults")
    val totalResults: String?,
    
    @SerializedName("Response")
    val response: String,
    
    @SerializedName("Error")
    val error: String? = null
)

/**
 * Data class para detalhes completos de um filme
 */
data class MovieDetails(
    @SerializedName("Title")
    val title: String?,
    
    @SerializedName("Year")
    val year: String?,
    
    @SerializedName("Rated")
    val rated: String?,
    
    @SerializedName("Released")
    val released: String?,
    
    @SerializedName("Runtime")
    val runtime: String?,
    
    @SerializedName("Genre")
    val genre: String?,
    
    @SerializedName("Director")
    val director: String?,
    
    @SerializedName("Writer")
    val writer: String?,
    
    @SerializedName("Actors")
    val actors: String?,
    
    @SerializedName("Plot")
    val plot: String?,
    
    @SerializedName("Language")
    val language: String?,
    
    @SerializedName("Country")
    val country: String?,
    
    @SerializedName("Awards")
    val awards: String?,
    
    @SerializedName("Poster")
    val poster: String?,
    
    @SerializedName("imdbRating")
    val imdbRating: String?,
    
    @SerializedName("imdbID")
    val imdbID: String,
    
    @SerializedName("Response")
    val response: String
)