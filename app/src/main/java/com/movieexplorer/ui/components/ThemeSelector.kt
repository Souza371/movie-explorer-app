package com.movieexplorer.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.movieexplorer.ui.theme.*

/**
 * 🎨 Seletor de temas brasileiro
 * Por: Vicente de Souza
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrazilianThemeSelector(
    currentTheme: ThemeMode,
    onThemeChange: (ThemeMode) -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            PaleGreen.copy(alpha = 0.1f),
                            CreamYellow.copy(alpha = 0.1f),
                            MintGreen.copy(alpha = 0.05f)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            // Header do seletor
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = !isExpanded },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Seletor de Tema",
                    tint = TropicalGreen,
                    modifier = Modifier.size(24.dp)
                )
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Text(
                    text = "🎨 Temas Brasileiros",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                Text(
                    text = if (isExpanded) "▲" else "▼",
                    color = TropicalGreen,
                    fontSize = 16.sp
                )
            }
            
            // Opções de tema (expansível)
            if (isExpanded) {
                Spacer(modifier = Modifier.height(16.dp))
                
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ThemeOption(
                        theme = ThemeMode.BRAZILIAN_FESTIVAL,
                        title = "🇧🇷 Festival Brasileiro",
                        description = "Verde e amarelo vibrante",
                        colors = listOf(BrazilGreen, BrazilYellow),
                        isSelected = currentTheme == ThemeMode.BRAZILIAN_FESTIVAL,
                        onClick = { onThemeChange(ThemeMode.BRAZILIAN_FESTIVAL) }
                    )
                    
                    ThemeOption(
                        theme = ThemeMode.LIGHT,
                        title = "☀️ Tropical Claro",
                        description = "Cores suaves e naturais",
                        colors = listOf(TropicalGreen, CreamYellow),
                        isSelected = currentTheme == ThemeMode.LIGHT,
                        onClick = { onThemeChange(ThemeMode.LIGHT) }
                    )
                    
                    ThemeOption(
                        theme = ThemeMode.DARK,
                        title = "🌙 Floresta Noturna",
                        description = "Verde escuro elegante",
                        colors = listOf(DeepForestGreen, GoldenYellow),
                        isSelected = currentTheme == ThemeMode.DARK,
                        onClick = { onThemeChange(ThemeMode.DARK) }
                    )
                    
                    ThemeOption(
                        theme = ThemeMode.AUTO,
                        title = "🔄 Automático",
                        description = "Segue o sistema",
                        colors = listOf(BrazilGreen, BrazilYellow, DeepForestGreen),
                        isSelected = currentTheme == ThemeMode.AUTO,
                        onClick = { onThemeChange(ThemeMode.AUTO) }
                    )
                }
            }
        }
    }
}

/**
 * Opção individual de tema
 */
@Composable
private fun ThemeOption(
    theme: ThemeMode,
    title: String,
    description: String,
    colors: List<Color>,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.05f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 8.dp else 2.dp
        ),
        border = if (isSelected) {
            androidx.compose.foundation.BorderStroke(2.dp, TropicalGreen)
        } else null,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Preview das cores
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                colors.forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(color)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            // Indicador de seleção
            if (isSelected) {
                Text(
                    text = "✓",
                    color = TropicalGreen,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

/**
 * 🎭 Componente de créditos brasileiro
 * Por: Vicente de Souza
 */
@Composable
fun BrazilianCredits(
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
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            CreamYellow.copy(alpha = 0.3f),
                            PaleGreen.copy(alpha = 0.2f),
                            Color.Transparent
                        ),
                        radius = 300f
                    )
                )
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🌟 Criado por Vicente de Souza 🌟",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DeepForestGreen
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "🎨 Tema Verde e Amarelo Brasileiro 🇧🇷",
                style = MaterialTheme.typography.bodyLarge,
                color = EmeraldDark
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = "Inspirado nas cores vibrantes do Brasil",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "🌿", fontSize = 16.sp)
                Text(text = "🌻", fontSize = 16.sp, modifier = Modifier.padding(horizontal = 4.dp))
                Text(text = "🎬", fontSize = 16.sp)
                Text(text = "🇧🇷", fontSize = 16.sp, modifier = Modifier.padding(horizontal = 4.dp))
                Text(text = "⭐", fontSize = 16.sp)
            }
        }
    }
}