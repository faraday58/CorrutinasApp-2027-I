package com.mexiti.corrutinasapp_2027_i.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mexiti.corrutinasapp_2027_i.R
import com.mexiti.corrutinasapp_2027_i.ui.theme.CorrutinasApp2027ITheme
import com.mexiti.corrutinasapp_2027_i.viewmodel.MainViewModel

@Composable
fun CoroutinesApp(viewModel: MainViewModel, modifier: Modifier = Modifier){
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier.fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Comparativa Secuencial vs Concurrente",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = uiState.currentLog,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { viewModel.iniciarComparativa() },
            enabled = !uiState.isRunning,
        ){
            Text(text = "Ejecutar pruebas (N a 5N)")
        }

        Spacer(modifier = Modifier.height(20.dp))

        if(uiState.isRunning && uiState.results.isEmpty()){
            CircularProgressIndicator()
        }

        //Encabezado de la Tabla
        if(uiState.results.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text("Escenario", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Tareas", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Secuencial", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                Text("Concurrente", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
            }
            HorizontalDivider()

            // Filas de resultados
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(uiState.results) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(item.factor, modifier = Modifier.weight(1f))
                        Text("${item.numTasks}", modifier = Modifier.weight(1f))
                        Text("${item.timeSequentialMs} ms", color = Color.Red, modifier = Modifier.weight(1.2f))
                        Text("${item.timeConcurrentMs} ms", color = Color(0xFF2E7D32), modifier = Modifier.weight(1.2f))
                    }
                    HorizontalDivider()
                }
            }
        }

    }
}

