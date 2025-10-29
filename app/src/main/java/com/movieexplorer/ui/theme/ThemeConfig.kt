package com.movieexplorer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

/**
 * 🌿🌻 Configurações avançadas do tema brasileiro 🌻🌿
 */

// Estados do tema
enum class ThemeMode {
    LIGHT, DARK, AUTO, BRAZILIAN_FESTIVAL
}

// Cores especiais para festivais brasileiros
val CarnavalGradient = listOf(
    BrazilYellow,
    SunshineYellow,
    GoldenYellow,
    TropicalGreen,
    BrazilGreen,
    LimeGreen
)

val CopacabanaGradient = listOf(
    Color(0xFF87CEEB), // Azul céu
    Color(0xFFFFD700), // Areia dourada  
    TropicalGreen,     // Palmeiras
    BrazilYellow       // Sol
)

/**
 * Tema especial para festivais brasileiros
 */
val BrazilianFestivalColorScheme = lightColorScheme(
    primary = BrazilYellow,
    onPrimary = DeepForestGreen,
    secondary = TropicalGreen,
    onSecondary = Color.White,
    tertiary = AccentOrange,
    onTertiary = Color.White,
    background = CreamYellow,
    onBackground = DarkGray,
    surface = Color.White,
    onSurface = DarkGray,
    surfaceVariant = MintGreen,
    onSurfaceVariant = EmeraldDark,
    error = ErrorRed,
    onError = Color.White,
    outline = BrazilGreen.copy(alpha = 0.5f),
    outlineVariant = LimeGreen.copy(alpha = 0.3f)
)

/**
 * Estado do tema atual
 */
@Stable
class MovieThemeState(
    initialThemeMode: ThemeMode = ThemeMode.AUTO
) {
    var themeMode by mutableStateOf(initialThemeMode)
        private set
    
    var useGradientBackgrounds by mutableStateOf(true)
        private set
    
    var useAnimations by mutableStateOf(true)
        private set

    fun updateThemeMode(mode: ThemeMode) {
        themeMode = mode
    }
    
    fun toggleGradientBackgrounds() {
        useGradientBackgrounds = !useGradientBackgrounds
    }
    
    fun toggleAnimations() {
        useAnimations = !useAnimations
    }
}

/**
 * Provider do estado do tema
 */
val LocalMovieThemeState = compositionLocalOf { MovieThemeState() }

/**
 * Função para obter o esquema de cores correto
 */
@Composable
fun getColorScheme(
    themeState: MovieThemeState = LocalMovieThemeState.current,
    darkTheme: Boolean = isSystemInDarkTheme()
): ColorScheme {
    return when (themeState.themeMode) {
        ThemeMode.LIGHT -> LightColorScheme
        ThemeMode.DARK -> DarkColorScheme  
        ThemeMode.BRAZILIAN_FESTIVAL -> BrazilianFestivalColorScheme
        ThemeMode.AUTO -> if (darkTheme) DarkColorScheme else LightColorScheme
    }
}

/**
 * Hook para controlar o tema
 */
@Composable
fun rememberMovieThemeState(
    initialThemeMode: ThemeMode = ThemeMode.AUTO
): MovieThemeState {
    return remember { MovieThemeState(initialThemeMode) }
}