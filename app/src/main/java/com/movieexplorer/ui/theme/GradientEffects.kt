package com.movieexplorer.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode

/**
 * 🌿🌻 Gradientes e efeitos visuais inspirados nas imagens verde e amarelo 🌻🌿
 */

/**
 * Gradiente principal inspirado nas cores vibrantes das imagens
 * Simula o efeito marmorizado verde-amarelo
 */
val BrazilianMarbleGradient = Brush.linearGradient(
    colors = listOf(
        BrazilGreen,
        TropicalGreen,
        LimeGreen,
        SunshineYellow,
        BrazilYellow,
        GoldenYellow
    ),
    start = Offset(0f, 0f),
    end = Offset(1000f, 1000f),
    tileMode = TileMode.Mirror
)

/**
 * Gradiente para fundo escuro com efeito de profundidade
 */
val DarkForestGradient = Brush.radialGradient(
    colors = listOf(
        EmeraldDark,
        DeepForestGreen,
        Color(0xFF0A3D1F),
        Color(0xFF051A0D)
    ),
    radius = 800f
)

/**
 * Gradiente para fundo claro com suavidade
 */
val SunshineGradient = Brush.linearGradient(
    colors = listOf(
        CreamYellow,
        LightLemon,
        Color(0xFFFFF8DC),
        PaleGreen,
        MintGreen
    ),
    start = Offset(0f, 0f),
    end = Offset(1000f, 500f)
)

/**
 * Gradiente de destaque para cards e botões
 */
val TropicalHighlightGradient = Brush.horizontalGradient(
    colors = listOf(
        TropicalGreen.copy(alpha = 0.8f),
        BrazilGreen,
        SunshineYellow.copy(alpha = 0.8f)
    )
)

/**
 * Gradiente sutil para overlays
 */
val SubtleOverlayGradient = Brush.verticalGradient(
    colors = listOf(
        Color.Transparent,
        DeepForestGreen.copy(alpha = 0.1f),
        BrazilYellow.copy(alpha = 0.05f),
        Color.Transparent
    )
)

/**
 * Modifier para aplicar o fundo gradiente principal
 */
fun Modifier.brazilianMarbleBackground() = this.background(BrazilianMarbleGradient)

/**
 * Modifier para aplicar fundo escuro gradiente
 */
fun Modifier.darkForestBackground() = this.background(DarkForestGradient)

/**
 * Modifier para aplicar fundo claro gradiente
 */
fun Modifier.sunshineBackground() = this.background(SunshineGradient)

/**
 * Modifier para aplicar destaque tropical
 */
fun Modifier.tropicalHighlight() = this.background(TropicalHighlightGradient)