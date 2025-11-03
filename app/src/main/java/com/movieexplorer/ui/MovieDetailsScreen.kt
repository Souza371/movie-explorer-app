package com.movieexplorer.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Edit

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.scale
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.movieexplorer.data.MovieDetails
import com.movieexplorer.ui.components.*
import com.movieexplorer.util.TranslationUtils

/**
 * Tela de detalhes completos e aprimorada do filme
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    movieDetails: MovieDetails,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(false) }
    
    LaunchedEffect(movieDetails) {
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(500)
        ) + fadeIn(animationSpec = tween(500))
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                // TopBar com gradiente
                DetailTopBar(
                    title = movieDetails.title ?: "Filme",
                    onBackClick = onBackClick
                )
                
                // Conteúdo scrollável
                DetailContent(movieDetails = movieDetails)
            }
            
            // 🔙 Botão flutuante adicional de voltar - Por: Vicente de Souza
            FloatingBackButton(
                onBackClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }
    }
}

/**
 * TopBar customizada para detalhes
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailTopBar(
    title: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            )
                        )
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar ao Menu Principal",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)
        )
    )
}

/**
 * Conteúdo principal dos detalhes
 */
@Composable
private fun DetailContent(movieDetails: MovieDetails) {
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header principal com poster e informações básicas
        MovieHeader(movieDetails)
        
        // Avaliações e ratings
        RatingsSection(movieDetails)
        
        // Sinopse
        SynopsisSection(movieDetails)
        
        // Informações detalhadas
        DetailedInfoSection(movieDetails)
        
        // Informações técnicas
        TechnicalInfoSection(movieDetails)
        
        // Espaçamento final
        Spacer(modifier = Modifier.height(32.dp))
    }
}

/**
 * Header com poster e informações principais
 */
@Composable
private fun MovieHeader(movieDetails: MovieDetails) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Poster
            Card(
                modifier = Modifier
                    .width(120.dp)
                    .height(180.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(if (movieDetails.poster == "N/A") null else movieDetails.poster)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Pôster de ${movieDetails.title}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    error = painterResource(android.R.drawable.ic_menu_gallery),
                    placeholder = painterResource(android.R.drawable.ic_menu_gallery)
                )
            }
            
            // Informações básicas
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = movieDetails.title ?: "Título não disponível",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                
                movieDetails.year?.let { year ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = year,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                
                movieDetails.rated?.let { rated ->
                    AssistChip(
                        onClick = { },
                        label = { 
                            Text(
                                "🎯 ${TranslationUtils.translateRating(rated)}",
                                style = MaterialTheme.typography.bodySmall
                            ) 
                        }
                    )
                }
                
                movieDetails.runtime?.let { runtime ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "⏰",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = TranslationUtils.translateRuntime(runtime),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

/**
 * Seção de avaliações e ratings
 */
@Composable
private fun RatingsSection(movieDetails: MovieDetails) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "⭐ Avaliações",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                movieDetails.imdbRating?.let { rating ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "⭐ IMDb:",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(100.dp)
                        )
                        Text(
                            text = "$rating/10",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFFFFD700)
                        )
                    }
                }
                

            }
        }
    }
}

/**
 * Seção da sinopse
 */
@Composable
private fun SynopsisSection(movieDetails: MovieDetails) {
    movieDetails.plot?.let { plot ->
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "📖 Sinopse",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                Text(
                    text = plot,
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

/**
 * Seção de informações detalhadas
 */
@Composable
private fun DetailedInfoSection(movieDetails: MovieDetails) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "🎬 Informações",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            movieDetails.director?.let { director ->
                DetailRow(
                    icon = "🎬",
                    label = "Diretor",
                    value = director
                )
            }
            
            movieDetails.actors?.let { actors ->
                DetailRow(
                    icon = "🎭",
                    label = "Elenco",
                    value = actors
                )
            }
            
            movieDetails.writer?.let { writer ->
                DetailRow(
                    icon = "✍️",
                    label = "Roteirista",
                    value = writer
                )
            }
            
            movieDetails.genre?.let { genre ->
                DetailRow(
                    icon = "🎪",
                    label = "Gêneros",
                    value = TranslationUtils.translateGenre(genre)
                )
            }
        }
    }
}

/**
 * Seção de informações técnicas
 */
@Composable
private fun TechnicalInfoSection(movieDetails: MovieDetails) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "🔧 Informações Técnicas",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            movieDetails.language?.let { language ->
                TechnicalInfo(label = "Idioma", value = TranslationUtils.translateLanguage(language))
            }
            
            movieDetails.country?.let { country ->
                TechnicalInfo(label = "País", value = TranslationUtils.translateCountry(country))
            }
            
            movieDetails.awards?.let { awards ->
                TechnicalInfo(label = "Prêmios", value = TranslationUtils.translateAwards(awards))
            }
            

            
            movieDetails.imdbID?.let { imdbID ->
                TechnicalInfo(label = "IMDb ID", value = imdbID)
            }
        }
    }
}

/**
 * Componente para informações técnicas
 */
@Composable
private fun TechnicalInfo(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.End
        )
    }
}

/**
 * Componente para detalhes com ícone emoji
 */
@Composable
private fun DetailRow(icon: String, label: String, value: String) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = icon,
            fontSize = 16.sp,
            modifier = Modifier.padding(end = 8.dp, top = 2.dp)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp
            )
        }
    }
}

/**
 * 🔙 Botão flutuante de voltar - Por: Vicente de Souza
 */
@Composable
private fun FloatingBackButton(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isPressed by remember { mutableStateOf(false) }
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    FloatingActionButton(
        onClick = {
            isPressed = true
            onBackClick()
        },
        modifier = modifier.scale(scale),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Menu",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}