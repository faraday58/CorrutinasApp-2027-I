package com.mexiti.corrutinasapp_2027_i.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mexiti.corrutinasapp_2027_i.viewmodel.MainViewModel

@Composable
fun CoroutinesApp(mainViewModel: MainViewModel, modifier: Modifier = Modifier){
    // Escuchamos el StateFlow directamente como estado de Compose
    val uiState by mainViewModel.uiState.collectAsState()

    Column(
        modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Indicador del estado actual
        Text(
            text = uiState.statusMessage,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Barra de progreso si hay un proceso corriendo
        if (uiState.isBusy || uiState.progress > 0) {
            LinearProgressIndicator(progress = { uiState.progress / 100f })
            Text(text = "${uiState.progress}%")
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Si existe error por choque de ejecución
        uiState.errorMessage?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        // PRUEBA 1: Lanza error si presionas mientras corre otro
        Button(
            onClick = { mainViewModel.ejecutarConErrorSiOcupado("Tarea A") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
        ) {
            Text("Lanzar Tarea (Falla si hay otra)")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // PRUEBA 2: Solución con Mutex (Pone en cola y espera a que la anterior libere)
        Button(
            onClick = { mainViewModel.ejecutarSincronizadoConMutex("Tarea B (Sincronizada)") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
        ) {
            Text("Lanzar Tarea Sincronizada (Mutex)")
        }
    }
}

