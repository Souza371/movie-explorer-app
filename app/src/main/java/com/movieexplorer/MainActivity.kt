package com.movieexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.movieexplorer.ui.MainScreen
import com.movieexplorer.ui.theme.MovieExplorerTheme
import com.movieexplorer.ui.theme.ThemeMode

/**
 * Atividade principal do aplicativo
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            // 🌿🌻 Usando o tema brasileiro verde e amarelo! 🌻🌿
            MovieExplorerTheme(
                themeMode = ThemeMode.BRAZILIAN_FESTIVAL
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}