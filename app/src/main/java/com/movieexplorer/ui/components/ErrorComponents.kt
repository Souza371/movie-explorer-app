package com.movieexplorer.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Componente para exibir mensagens de erro
 */
@Composable
fun ErrorMessage(
    message: String,
    onRetry: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Warning,
    emoji: String = "⚠️"
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = emoji,
                fontSize = 48.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier
                    .size(32.dp)
                    .padding(bottom = 8.dp)
            )
            
            Text(
                text = "Ops! Algo deu errado",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onErrorContainer,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.8f),
                textAlign = TextAlign.Center
            )
            
            if (onRetry != null) {
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(
                    onClick = onRetry,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Tentar Novamente")
                }
            }
        }
    }
}

/**
 * Componente para erro de conexão
 */
@Composable
fun NetworkErrorMessage(
    message: String = "Erro de conexão com a internet",
    onRetry: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    ErrorMessage(
        message = message,
        onRetry = onRetry,
        modifier = modifier,
        emoji = "📶"
    )
}

/**
 * Componente para erro genérico
 */
@Composable
fun GenericErrorMessage(
    message: String,
    onRetry: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    ErrorMessage(
        message = message,
        onRetry = onRetry,
        modifier = modifier,
        emoji = "❌"
    )
}

/**
 * Componente para quando nenhum resultado é encontrado
 */
@Composable
fun NoResultsFound(
    query: String,
    onClear: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🔍",
                fontSize = 64.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Text(
                text = "Nenhum resultado encontrado",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Não encontramos filmes para \"$query\"",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )
            
            if (onClear != null) {
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedButton(onClick = onClear) {
                    Text("Limpar Busca")
                }
            }
        }
    }
}