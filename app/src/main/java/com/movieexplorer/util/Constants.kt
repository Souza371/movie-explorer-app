package com.movieexplorer.util

/**
 * Constantes globais da aplicação Movie Explorer.
 * 
 * Centraliza valores constantes para facilitar manutenção
 * e evitar magic numbers/strings espalhados pelo código.
 * 
 * @author Vicente
 * @since 1.0
 */
object Constants {
    
    // API Configuration
    const val BASE_URL = "https://www.omdbapi.com/"
    const val API_KEY = "357576b4"
    const val MIN_SEARCH_LENGTH = 2
    
    // UI Constants
    const val CARD_ELEVATION_DP = 4
    const val POSTER_WIDTH_DP = 80
    const val POSTER_HEIGHT_DP = 120
    const val POSTER_DETAILS_WIDTH_DP = 150
    const val POSTER_DETAILS_HEIGHT_DP = 225
    
    // Spacing
    const val SPACING_SMALL_DP = 4
    const val SPACING_MEDIUM_DP = 8
    const val SPACING_LARGE_DP = 16
    const val SPACING_EXTRA_LARGE_DP = 24
    
    // Animation
    const val CROSSFADE_DURATION_MS = 300
    const val LOADING_DELAY_MS = 500
    
    // Error Messages
    const val ERROR_EMPTY_QUERY = "Digite um título para buscar"
    const val ERROR_SHORT_QUERY = "Digite pelo menos 2 caracteres"
    const val ERROR_NETWORK = "Erro de conexão. Verifique sua internet."
    const val ERROR_TIMEOUT = "Timeout na conexão. Tente novamente."
    const val ERROR_UNKNOWN = "Erro inesperado"
    
    // Image Placeholders
    const val PLACEHOLDER_NOT_AVAILABLE = "N/A"
    const val PLACEHOLDER_IMAGE_RES = android.R.drawable.ic_menu_gallery
}