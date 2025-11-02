package com.movieexplorer.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.movieexplorer.data.Movie
import com.movieexplorer.ui.theme.*
import com.movieexplorer.util.getResponsiveLayout

/**
 * Card aprimorado para exibir informações de um filme
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isPressed by remember { mutableStateOf(false) }
    var isHovered by remember { mutableStateOf(false) }
    
    // Layout responsivo
    val layout = getResponsiveLayout()
    
    // Animação de escala suave
    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.02f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clickable { 
                isPressed = true
                onClick() 
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isPressed) 12.dp else 6.dp
        ),
        border = BorderStroke(
            width = if (isPressed) 3.dp else 1.dp,
            color = TropicalGreen.copy(alpha = if (isPressed) 0.8f else 0.3f)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        // Fundo com gradiente sutil
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surface,
                            MaterialTheme.colorScheme.surface,
                            PaleGreen.copy(alpha = 0.1f),
                            CreamYellow.copy(alpha = 0.05f)
                        )
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(layout.horizontalPadding.dp)
            ) {
            // Container do pôster com bordas arredondadas - responsivo
            Card(
                modifier = Modifier
                    .width((layout.cardWidth * 0.6f).dp)
                    .height((layout.cardWidth * 0.9f).dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(if (movie.poster == "N/A") null else movie.poster)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Pôster de ${movie.title}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    error = painterResource(android.R.drawable.ic_menu_gallery),
                    placeholder = painterResource(android.R.drawable.ic_menu_gallery)
                )
            }
            
            Spacer(modifier = Modifier.width(layout.horizontalPadding.dp))
            
            // Informações do filme
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = movie.title,
                    fontSize = layout.titleFontSize.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = if (layout.screenSize.name == "SMALL") 1 else 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(layout.verticalSpacing.dp))
                
                // Ano com ícone
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
                        text = movie.year,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Medium
                    )
                }
                
                // Tipo do conteúdo (filme, série, etc.)
                movie.type?.let { type ->
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        
                        // Chip para o tipo
                        AssistChip(
                            onClick = { },
                            label = { 
                                Text(
                                    text = getTypeEmoji(type) + " " + type.replaceFirstChar { it.uppercase() },
                                    style = MaterialTheme.typography.bodySmall
                                ) 
                            },
                            modifier = Modifier.height(28.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // ID do IMDb
                Text(
                    text = "IMDb: ${movie.imdbID}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Light
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Indicador de "Toque para detalhes"
                Text(
                    text = "👆 Toque para mais detalhes",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        }
    }
}

/**
 * Retorna emoji baseado no tipo de conteúdo
 */
private fun getTypeEmoji(type: String): String {
    return when (type.lowercase()) {
        "movie" -> "🎬"
        "series" -> "📺"
        "episode" -> "📽️"
        "game" -> "🎮"
        else -> "🎭"
    }
}