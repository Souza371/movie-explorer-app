package com.movieexplorer.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.movieexplorer.R
import com.movieexplorer.viewmodel.MovieViewModel
import com.movieexplorer.ui.components.*

/**
 * Tela principal que orquestra a UI do aplicativo.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MovieViewModel = viewModel()) {
    val selectedMovie = viewModel.selectedMovie
    val isLoadingDetails = viewModel.isLoadingDetails

    // Se um filme for selecionado, mostra a tela de detalhes ou seu estado de carregamento.
    // Caso contrário, mostra a tela de busca principal.
    if (selectedMovie != null || isLoadingDetails) {
        DetailsContent(viewModel)
    } else {
        SearchContent(viewModel)
    }
}

/**
 * Conteúdo da tela principal (busca e lista de filmes).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchContent(viewModel: MovieViewModel) {
    val movies = viewModel.movies
    val query = viewModel.query
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            SearchBar(
                query = query,
                onQueryChange = viewModel::updateQuery,
                onSearch = viewModel::searchMovies
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Conteúdo central que muda com base no estado
            when {
                isLoading -> {
                    MovieLoadingIndicator(
                        message = "Buscando filmes para \"$query\"..."
                    )
                }
                errorMessage != null -> {
                    if (errorMessage.contains("conexão") || errorMessage.contains("internet")) {
                        NetworkErrorMessage(
                            message = errorMessage,
                            onRetry = { viewModel.forceRefresh() }
                        )
                    } else if (movies.isEmpty() && errorMessage.contains("Nenhum resultado")) {
                        NoResultsFound(
                            query = query,
                            onClear = { viewModel.clearAllData() }
                        )
                    } else {
                        GenericErrorMessage(
                            message = errorMessage,
                            onRetry = { viewModel.forceRefresh() }
                        )
                    }
                }
                movies.isEmpty() && query.isBlank() -> {
                    InitialState()
                }
                movies.isEmpty() && query.isNotBlank() -> {
                    NoResultsFound(
                        query = query,
                        onClear = { viewModel.clearAllData() }
                    )
                }
                else -> {
                    MovieList(
                        movies = movies,
                        onMovieClick = { movie -> viewModel.getMovieDetails(movie.imdbID) }
                    )
                }
            }
        }
    }
}

/**
 * Conteúdo da tela de detalhes de um filme.
 */
@Composable
private fun DetailsContent(viewModel: MovieViewModel) {
    val selectedMovie = viewModel.selectedMovie
    val isLoadingDetails = viewModel.isLoadingDetails
    val errorMessage = viewModel.errorMessage

    when {
        isLoadingDetails -> {
            FullScreenLoading(message = "Carregando detalhes do filme...")
        }
        selectedMovie != null -> {
            MovieDetailsScreen(
                movieDetails = selectedMovie,
                onBackClick = { viewModel.clearSelectedMovie() }
            )
        }
        errorMessage != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                GenericErrorMessage(
                    message = errorMessage,
                    onRetry = { viewModel.clearSelectedMovie() }
                )
            }
        }
    }
}

/**
 * Composable para o estado de carregamento.
 */
@Composable
private fun LoadingState(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = message, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

/**
 * Composable para o estado inicial, antes de qualquer busca.
 */
@Composable
private fun InitialState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(R.string.welcome_emoji), style = MaterialTheme.typography.displayLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.welcome_title),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.welcome_subtitle),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Composable para exibir uma mensagem de erro.
 */
@Composable
private fun ErrorState(message: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
    ) {
        Text(
            text = message,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onErrorContainer,
            textAlign = TextAlign.Center
        )
    }
}