package com.movieexplorer.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.movieexplorer.ui.theme.*
import com.movieexplorer.util.getResponsiveLayout
import kotlinx.coroutines.delay

/**
 * 🇧🇷 Tela de apresentação brasileira
 * Por: Vicente de Souza
 */
@Composable
fun BrazilianSplashScreen(
    onFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Layout responsivo
    val layout = getResponsiveLayout()
    
    // Animações coordenadas
    val infiniteTransition = rememberInfiniteTransition(label = "splash")
    
    // Rotação do ícone principal
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )
    
    // Pulsação dos elementos
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )
    
    // Brilho do gradiente
    val shimmer by infiniteTransition.animateFloat(
        initialValue = -0.5f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer"
    )
    
    // Auto-dismiss após 4 segundos
    LaunchedEffect(Unit) {
        delay(4000)
        onFinished()
    }
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        BrazilYellow.copy(alpha = 0.3f),
                        SunshineYellow.copy(alpha = 0.2f),
                        TropicalGreen.copy(alpha = 0.1f),
                        DeepForestGreen.copy(alpha = 0.05f)
                    ),
                    center = androidx.compose.ui.geometry.Offset(shimmer * 1000f, 500f),
                    radius = 800f
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo principal animado - responsivo
            Card(
                modifier = Modifier
                    .size((layout.cardWidth * 0.8f).dp)
                    .scale(pulse)
                    .rotate(rotation),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                shape = RoundedCornerShape((layout.cardWidth * 0.4f).dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
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
                    Text(
                        text = "🎬",
                        fontSize = 48.sp,
                        modifier = Modifier.rotate(-rotation) // Contra-rotação para o emoji
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Título principal
            Text(
                text = "Movie Explorer App",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = DeepForestGreen,
                letterSpacing = 2.sp
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Subtítulo brasileiro
            Text(
                text = "🇧🇷 Edição Brasileira 🇧🇷",
                style = MaterialTheme.typography.titleLarge,
                color = BrazilGreen,
                fontWeight = FontWeight.Medium
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Assinatura do criador
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "✨ Criado por Vicente de Souza ✨",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = EmeraldDark,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Indicador de carregamento brasileiro
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(3) { index ->
                    val delay = index * 200
                    val dotScale by infiniteTransition.animateFloat(
                        initialValue = 0.5f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(600, delay, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "dot$index"
                    )
                    
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .scale(dotScale)
                            .background(
                                when (index) {
                                    0 -> BrazilGreen
                                    1 -> BrazilYellow
                                    else -> TropicalGreen
                                },
                                RoundedCornerShape(6.dp)
                            )
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Mensagem de carregamento
            Text(
                text = "Carregando experiência brasileira...",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
        
        // Elementos decorativos nas bordas
        BrazilianDecorations()
    }
}

/**
 * Decorações brasileiras nas bordas da tela
 */
@Composable
private fun BrazilianDecorations() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Canto superior esquerdo
        Text(
            text = "🌿",
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(32.dp)
        )
        
        // Canto superior direito
        Text(
            text = "🌻",
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(32.dp)
        )
        
        // Canto inferior esquerdo
        Text(
            text = "🎭",
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(32.dp)
        )
        
        // Canto inferior direito
        Text(
            text = "⭐",
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp)
        )
    }
}

/**
 * 🎉 Componente de boas-vindas especial
 */
@Composable
fun BrazilianWelcomeBanner(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            BrazilGreen.copy(alpha = 0.9f),
                            TropicalGreen.copy(alpha = 0.8f),
                            LimeGreen.copy(alpha = 0.7f),
                            SunshineYellow.copy(alpha = 0.8f),
                            BrazilYellow.copy(alpha = 0.9f)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🇧🇷 BEM-VINDO AO BRASIL! 🇧🇷",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = androidx.compose.ui.graphics.Color.White,
                    letterSpacing = 1.5.sp
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "Tema criado especialmente por Vicente de Souza",
                    style = MaterialTheme.typography.bodyLarge,
                    color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.9f),
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "🌿", fontSize = 20.sp)
                    Text(text = "Cinema", 
                         color = androidx.compose.ui.graphics.Color.White,
                         modifier = Modifier.padding(horizontal = 8.dp))
                    Text(text = "🎬", fontSize = 20.sp)
                    Text(text = "Brasileiro", 
                         color = androidx.compose.ui.graphics.Color.White,
                         modifier = Modifier.padding(horizontal = 8.dp))
                    Text(text = "🌻", fontSize = 20.sp)
                }
            }
        }
    }
}