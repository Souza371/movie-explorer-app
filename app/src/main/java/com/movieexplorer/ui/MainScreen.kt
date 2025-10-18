package com.movieexplorer.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.movieexplorer.viewmodel.MovieViewModel

/**
 * Tela principal do aplicativo
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MovieViewModel = viewModel()
) {
    // Estados do ViewModel
    val movies = viewModel.movies
    val query = viewModel.query
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage
    val selectedMovie = viewModel.selectedMovie
    val isLoadingDetails = viewModel.isLoadingDetails
    
    // Se um filme está selecionado, mostra os detalhes
    if (selectedMovie != null) {
        if (isLoadingDetails) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Carregando detalhes...")
                }
            }
        } else {
            MovieDetailsScreen(
                movieDetails = selectedMovie,
                onBackClick = { viewModel.clearSelectedMovie() }
            )
        }
    } else {
        // Tela principal com busca e lista
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // TopBar
            TopAppBar(
                title = { Text("Movie Explorer") }
            )
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                
                // Barra de busca
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::updateQuery,
                    onSearch = viewModel::searchMovies
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Mensagem de erro
                errorMessage?.let { message ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        )
                    ) {
                        Text(
                            text = message,
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
                
                // Conteúdo principal
                when {
                    isLoading -> {
                        // Loading
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator()
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "Buscando filmes...",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                    
                    movies.isEmpty() && query.isBlank() && errorMessage == null -> {
                        // Estado inicial
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "🎬",
                                    style = MaterialTheme.typography.displayLarge
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "Bem-vindo ao Movie Explorer!",
                                    style = MaterialTheme.typography.headlineSmall,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Digite o nome de um filme para começar a busca",
                                    style = MaterialTheme.typography.bodyLarge,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                    
                    else -> {
                        // Lista de filmes
                        MovieList(
                            movies = movies,
                            onMovieClick = { movie ->
                                viewModel.getMovieDetails(movie.imdbID)
                            }
                        )
                    }
                }
            }
        }
    }
}