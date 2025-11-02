package com.movieexplorer.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.movieexplorer.data.Movie
import com.movieexplorer.util.getResponsiveLayout

/**
 * Lista aprimorada de filmes com animações e scroll suave
 */
@Composable
fun MovieList(
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier,
    onRefresh: (() -> Unit)? = null
) {
    if (movies.isEmpty()) {
        EmptyMovieListState(modifier = modifier)
    } else {
        EnhancedMovieListContent(
            movies = movies,
            onMovieClick = onMovieClick,
            modifier = modifier,
            onRefresh = onRefresh
        )
    }
}

/**
 * Estado vazio da lista de filmes
 */
@Composable
private fun EmptyMovieListState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "🎬",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Text(
                text = "Nenhum filme encontrado",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Text(
                text = "Tente buscar por outro título ou verifique a ortografia",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Conteúdo aprimorado da lista de filmes
 */
@Composable
private fun EnhancedMovieListContent(
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier,
    onRefresh: (() -> Unit)? = null
) {
    val listState = rememberLazyListState()
    val isAtTop by remember {
        derivedStateOf { listState.firstVisibleItemIndex == 0 }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Cabeçalho com contador de resultados
        item {
            ResultsHeader(
                count = movies.size,
                onRefresh = onRefresh,
                isAtTop = isAtTop
            )
        }
        
        // Lista de filmes com animações
        items(
            items = movies,
            key = { movie -> movie.imdbID }
        ) { movie ->
            AnimatedMovieCard(
                movie = movie,
                onClick = { onMovieClick(movie) }
            )
        }
        
        // Espaçamento final
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/**
 * Cabeçalho dos resultados com contador e botão de refresh
 */
@Composable
private fun ResultsHeader(
    count: Int,
    onRefresh: (() -> Unit)?,
    isAtTop: Boolean
) {
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically() + fadeIn(),
        exit = slideOutVertically() + fadeOut()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "🎯 $count resultado${if (count != 1) "s" else ""} encontrado${if (count != 1) "s" else ""}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            
            if (onRefresh != null) {
                OutlinedButton(
                    onClick = onRefresh,
                    modifier = Modifier
                        .alpha(if (isAtTop) 1f else 0.7f),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "🔄 Atualizar",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

/**
 * MovieCard com animações de entrada
 */
@Composable
private fun AnimatedMovieCard(
    movie: Movie,
    onClick: () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    
    LaunchedEffect(movie.imdbID) {
        isVisible = true
    }
    
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(
                durationMillis = 400,
                easing = FastOutSlowInEasing
            )
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = 400,
                easing = FastOutSlowInEasing
            )
        )
    ) {
        MovieCard(
            movie = movie,
            onClick = onClick
        )
    }
}