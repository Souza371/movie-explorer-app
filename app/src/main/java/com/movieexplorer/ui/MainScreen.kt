package com.movieexplorer.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.activity.compose.BackHandler
import com.movieexplorer.R
import com.movieexplorer.viewmodel.MovieViewModel
import com.movieexplorer.ui.components.*
import com.movieexplorer.ui.theme.ThemeMode
import com.movieexplorer.util.MovieState
import com.movieexplorer.util.getResponsiveLayout

/**
 * Tela principal que orquestra a UI do aplicativo.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MovieViewModel = viewModel()) {
    val selectedMovie = viewModel.selectedMovie
    val isLoadingDetails = viewModel.isLoadingDetails
    
    // 🌿🌻 Estado do tema brasileiro - Por: Vicente de Souza 🌻🌿
    var currentTheme by remember { mutableStateOf(ThemeMode.BRAZILIAN_FESTIVAL) }
    
    // � Estado para controlar o diálogo de saída
    var showExitDialog by remember { mutableStateOf(false) }

    // �🔙 Controle inteligente do botão back do Android - Por: Vicente de Souza
    BackHandler(enabled = true) {
        when {
            selectedMovie != null || isLoadingDetails -> {
                // Volta para a tela principal quando estiver na tela de detalhes
                viewModel.clearSelectedMovie()
            }
            else -> {
                // Na tela principal, mostra diálogo de confirmação para sair
                showExitDialog = true
            }
        }
    }

    // 🚪 Diálogo de confirmação de saída
    if (showExitDialog) {
        val context = LocalContext.current
        ExitConfirmationDialog(
            onConfirmExit = {
                // Sai do aplicativo
                val activity = context as? androidx.activity.ComponentActivity
                activity?.finish()
            },
            onDismiss = {
                showExitDialog = false
            }
        )
    }

    // Se um filme for selecionado, mostra a tela de detalhes ou seu estado de carregamento.
    // Caso contrário, mostra a tela de busca principal.
    if (selectedMovie != null || isLoadingDetails) {
        DetailsContent(viewModel)
    } else {
        SearchContent(viewModel, currentTheme) { newTheme ->
            currentTheme = newTheme
        }
    }
}

/**
 * Conteúdo da tela principal (busca e lista de filmes).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchContent(
    viewModel: MovieViewModel,
    currentTheme: ThemeMode,
    onThemeChange: (ThemeMode) -> Unit
) {
    val movies = viewModel.movies
    val query = viewModel.query
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage
    
    // Layout responsivo
    val layout = getResponsiveLayout()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // 🌿🌻 Header brasileiro personalizado 🌻🌿
        BrazilianMovieHeader()
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = layout.horizontalPadding.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

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
                    InitialState(currentTheme, onThemeChange)
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
private fun InitialState(
    currentTheme: ThemeMode,
    onThemeChange: (ThemeMode) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Componente brasileiro de boas-vindas
            BrazilianWelcomeMessage()
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Mensagem adicional com emojis brasileiros
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Digite o nome de um filme na barra de busca acima",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "🔍 Batman • Avengers • Star Wars • Matrix 🎬",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // 🎨 Seletor de temas brasileiro - Por: Vicente de Souza
            BrazilianThemeSelector(
                currentTheme = currentTheme,
                onThemeChange = onThemeChange
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 🌟 Créditos do criador
            BrazilianCredits()
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

/**
 * 🚪 Diálogo de confirmação para sair do app - Por: Vicente de Souza
 */
@Composable
private fun ExitConfirmationDialog(
    onConfirmExit: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        title = {
            Text(
                text = "Sair do Movie Explorer App?",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(
                text = "Tem certeza que deseja sair do aplicativo?\n\nVocê pode continuar explorando filmes incríveis! 🎬",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirmExit,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(
                    text = "Sair",
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Continuar no App",
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}