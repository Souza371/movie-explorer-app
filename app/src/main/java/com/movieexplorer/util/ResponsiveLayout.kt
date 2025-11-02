package com.movieexplorer.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

/**
 * Utilitário para layout responsivo
 * Criado por: Vicente de Souza
 */

enum class ScreenSize {
    SMALL,    // Celular pequeno (< 600dp)
    MEDIUM,   // Celular grande/Tablet pequeno (600-839dp) 
    LARGE     // Tablet grande (>= 840dp)
}

data class ResponsiveLayout(
    val screenSize: ScreenSize,
    val screenWidth: Int,
    val screenHeight: Int,
    val isLandscape: Boolean,
    val columns: Int,
    val cardWidth: Int,
    val horizontalPadding: Int,
    val verticalSpacing: Int,
    val titleFontSize: Int,
    val subtitleFontSize: Int
)

@Composable
fun getResponsiveLayout(): ResponsiveLayout {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    val isLandscape = screenWidth > screenHeight

    val screenSize = when {
        screenWidth < 600 -> ScreenSize.SMALL
        screenWidth < 840 -> ScreenSize.MEDIUM
        else -> ScreenSize.LARGE
    }

    return ResponsiveLayout(
        screenSize = screenSize,
        screenWidth = screenWidth,
        screenHeight = screenHeight,
        isLandscape = isLandscape,
        columns = when {
            screenSize == ScreenSize.LARGE && isLandscape -> 4
            screenSize == ScreenSize.LARGE -> 3
            screenSize == ScreenSize.MEDIUM && isLandscape -> 3
            screenSize == ScreenSize.MEDIUM -> 2
            isLandscape -> 2
            else -> 1
        },
        cardWidth = when (screenSize) {
            ScreenSize.SMALL -> if (isLandscape) 160 else 180
            ScreenSize.MEDIUM -> if (isLandscape) 140 else 160
            ScreenSize.LARGE -> 150
        },
        horizontalPadding = when (screenSize) {
            ScreenSize.SMALL -> 12
            ScreenSize.MEDIUM -> 16
            ScreenSize.LARGE -> 24
        },
        verticalSpacing = when (screenSize) {
            ScreenSize.SMALL -> 8
            ScreenSize.MEDIUM -> 12
            ScreenSize.LARGE -> 16
        },
        titleFontSize = when (screenSize) {
            ScreenSize.SMALL -> 16
            ScreenSize.MEDIUM -> 18
            ScreenSize.LARGE -> 20
        },
        subtitleFontSize = when (screenSize) {
            ScreenSize.SMALL -> 12
            ScreenSize.MEDIUM -> 14
            ScreenSize.LARGE -> 16
        }
    )
}

/**
 * Função para obter padding seguro nas bordas da tela
 */
@Composable
fun getSafePadding(): Int {
    val layout = getResponsiveLayout()
    return when (layout.screenSize) {
        ScreenSize.SMALL -> 8
        ScreenSize.MEDIUM -> 12
        ScreenSize.LARGE -> 16
    }
}