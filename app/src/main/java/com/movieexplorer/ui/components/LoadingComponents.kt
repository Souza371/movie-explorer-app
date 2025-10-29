package com.movieexplorer.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.movieexplorer.ui.theme.*

/**
 * Componente de loading personalizado com animação
 */
@Composable
fun MovieLoadingIndicator(
    message: String = "Carregando filmes...",
    modifier: Modifier = Modifier
) {
    // Animação de rotação infinita
    val infiniteTransition = rememberInfiniteTransition(label = "loading")
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Círculo de loading com gradiente brasileiro
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(
                    Brush.sweepGradient(
                        colors = listOf(
                            BrazilGreen,
                            TropicalGreen,
                            LimeGreen,
                            SunshineYellow,
                            BrazilYellow,
                            GoldenYellow,
                            BrazilGreen
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .size(48.dp)
                    .rotate(rotationAngle)
            ) {
                val strokeWidth = 6.dp.toPx()
                drawArc(
                    color = androidx.compose.ui.graphics.Color.White,
                    startAngle = 0f,
                    sweepAngle = 300f,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // Ícone de filme brasileiro
        Text(
            text = "🎬🇧🇷",
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        
        // Indicador linear de progresso brasileiro
        LinearProgressIndicator(
            modifier = Modifier
                .padding(top = 20.dp)
                .width(250.dp)
                .clip(CircleShape),
            color = TropicalGreen,
            trackColor = BrazilYellow.copy(alpha = 0.3f)
        )
    }
}

/**
 * Componente de loading para tela cheia
 */
@Composable
fun FullScreenLoading(
    message: String = "Carregando...",
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        MovieLoadingIndicator(message = message)
    }
}

/**
 * Componente de estado vazio
 */
@Composable
fun EmptyState(
    title: String = "Nenhum filme encontrado",
    subtitle: String = "Tente buscar por outro título",
    emoji: String = "🔍",
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = emoji,
            fontSize = 64.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )
    }
}