package com.movieexplorer.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.movieexplorer.ui.theme.*

/**
 * 🌿🌻 Header especial com tema brasileiro 🌻🌿
 */
@Composable
fun BrazilianMovieHeader(
    title: String = "Movie Explorer Brasil",
    modifier: Modifier = Modifier
) {
    // Animação de brilho sutil
    val infiniteTransition = rememberInfiniteTransition(label = "shine")
    val shineOffset by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shine"
    )
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            BrazilGreen,
                            TropicalGreen,
                            LimeGreen.copy(alpha = 0.8f),
                            SunshineYellow.copy(alpha = 0.9f),
                            BrazilYellow,
                            GoldenYellow
                        ),
                        start = androidx.compose.ui.geometry.Offset(shineOffset * 1000, 0f),
                        end = androidx.compose.ui.geometry.Offset((shineOffset + 0.5f) * 1000, 100f)
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Ícones brasileiros
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🎬",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = "🇧🇷",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    Text(
                        text = "🌟",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Título principal
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = DeepForestGreen,
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.2.sp
                )
                
                // Subtítulo
                Text(
                    text = "Descubra o cinema com estilo brasileiro",
                    style = MaterialTheme.typography.bodyMedium,
                    color = EmeraldDark,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            
            // Efeito de brilho sutil
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                androidx.compose.ui.graphics.Color.Transparent,
                                androidx.compose.ui.graphics.Color.White.copy(alpha = 0.3f),
                                androidx.compose.ui.graphics.Color.Transparent
                            )
                        )
                    )
            )
        }
    }
}

/**
 * 🎭 Componente de boas-vindas brasileiro
 */
@Composable
fun BrazilianWelcomeMessage(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .sunshineBackground()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🌟 Bem-vindo ao Cinema Brasileiro! 🌟",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = DeepForestGreen,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = "Pesquise filmes com o vibrante tema verde e amarelo! 🎬🇧🇷",
                style = MaterialTheme.typography.bodyLarge,
                color = EmeraldDark,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "🌿", fontSize = 20.sp)
                Text(text = "🌻", fontSize = 20.sp, modifier = Modifier.padding(horizontal = 4.dp))
                Text(text = "🎭", fontSize = 20.sp)
                Text(text = "🎪", fontSize = 20.sp, modifier = Modifier.padding(horizontal = 4.dp))
                Text(text = "🌿", fontSize = 20.sp)
            }
        }
    }
}