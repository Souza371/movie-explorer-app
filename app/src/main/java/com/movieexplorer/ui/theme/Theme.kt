package com.movieexplorer.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// 🌙 Tema escuro com verde e amarelo vibrantes
val DarkColorScheme = darkColorScheme(
    primary = BrazilGreen,              // Verde Brasil como cor principal
    onPrimary = androidx.compose.ui.graphics.Color.White,
    secondary = BrazilYellow,           // Amarelo Brasil como secundária
    onSecondary = DeepForestGreen,
    tertiary = TropicalGreen,           // Verde tropical como terciária
    onTertiary = androidx.compose.ui.graphics.Color.White,
    background = DeepForestGreen,       // Fundo verde escuro
    onBackground = LightGray,
    surface = EmeraldDark,              // Superfícies em verde esmeralda
    onSurface = LightGray,
    surfaceVariant = DarkGolden,        // Variações em dourado escuro
    onSurfaceVariant = CreamYellow,
    error = ErrorRed,
    onError = androidx.compose.ui.graphics.Color.White
)

// ☀️ Tema claro com verde e amarelo suaves
val LightColorScheme = lightColorScheme(
    primary = TropicalGreen,            // Verde tropical brilhante
    onPrimary = androidx.compose.ui.graphics.Color.White,
    secondary = SunshineYellow,         // Amarelo sol radiante
    onSecondary = DeepForestGreen,
    tertiary = LimeGreen,               // Verde limão fresco
    onTertiary = androidx.compose.ui.graphics.Color.White,
    background = CreamYellow,           // Fundo amarelo creme suave
    onBackground = DarkGray,
    surface = androidx.compose.ui.graphics.Color.White,
    onSurface = DarkGray,
    surfaceVariant = PaleGreen,         // Variações em verde pálido
    onSurfaceVariant = EmeraldDark,
    error = ErrorRed,
    onError = androidx.compose.ui.graphics.Color.White
)

@Composable
fun MovieExplorerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Desabilitado para usar nosso tema personalizado
    themeMode: ThemeMode = ThemeMode.AUTO,
    content: @Composable () -> Unit
) {
    val themeState = rememberMovieThemeState(themeMode)
    
    val colorScheme = when {
        // Sempre usar nosso tema personalizado brasileiro
        themeMode == ThemeMode.BRAZILIAN_FESTIVAL -> BrazilianFestivalColorScheme
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme || themeMode == ThemeMode.DARK -> DarkColorScheme
        else -> LightColorScheme
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Status bar com cor brasileira
            window.statusBarColor = when (themeMode) {
                ThemeMode.BRAZILIAN_FESTIVAL -> BrazilYellow.toArgb()
                else -> colorScheme.primary.toArgb()
            }
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = 
                when (themeMode) {
                    ThemeMode.DARK -> false
                    ThemeMode.BRAZILIAN_FESTIVAL -> true
                    else -> !darkTheme
                }
        }
    }

    // Provedor do estado do tema
    CompositionLocalProvider(
        LocalMovieThemeState provides themeState
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}