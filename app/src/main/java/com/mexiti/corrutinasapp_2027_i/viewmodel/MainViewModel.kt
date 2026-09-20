package com.mexiti.corrutinasapp_2027_i.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Estructura para registrar los resultados del benchmark
data class  BenchMarKResult(
    val factor: String,    // "1N", "2N", "3N", "4N", "5N"
    val numTasks: Int,     // Cantidad de tareas asociadas
    val timeSequentialMs: Long, // Tiempo en ejecución secuencial
    val timeConcurrentMs:Long // Tiempo en ejecución concurrente
)

data class BenchMarkUiState(
    val results: List<BenchMarKResult> = emptyList(),
    val isRunning: Boolean = false,
    val currentLog: String = "Listo para iniciar pruebas"

)


class MainViewModel:ViewModel(){
    private val _uiState = MutableStateFlow(BenchMarkUiState())
    val uiState: StateFlow<BenchMarkUiState> = _uiState.asStateFlow()

    // Constante N base de tareas
    private val baseN = 2

    // Simulación de un algoritmo con carga de trabajo (1 segundo por tarea)
    private suspend fun ejecutarAlgoritmo(id: Int){
        delay(1000)
    }

    // 1. Algoritmo Secuencial: Espera a que termine cada tarea una por una
    private suspend fun runSecuencial(totalTasks: Int): Long {
        val startTime = System.currentTimeMillis()
        for( i in 1..totalTasks){
            ejecutarAlgoritmo(i)
        }
        return System.currentTimeMillis() - startTime
    }
    // 2. Algoritmo Concurrente: Ejecuta todas las tareas simultáneamente con async
    private suspend fun runConcurrente(totalTasks: Int): Long = coroutineScope {
        val startTime = System.currentTimeMillis()
        val deferreds = (1..totalTasks).map { id ->
            async {
                ejecutarAlgoritmo(id)
            }
        }
        // Esperamos a que todas las corrutinas finalicen
        deferreds.forEach { it.await() }
        System.currentTimeMillis() - startTime
    }

    // Ejecuta las pruebas para N, 2N, 3N, 4N y 5N
    fun iniciarComparativa() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRunning = true, results = emptyList()) }

            val listaResultados = mutableListOf<BenchMarKResult>()
            val multiplicadores = listOf(1, 2, 3, 4, 5)

            for (m in multiplicadores) {
                val tasksCount = baseN * m
                val factorLabel = "${m}N"

                _uiState.update {
                    it.copy(currentLog = "Procesando $factorLabel ($tasksCount tareas)...")
                }

                // Ejecución Secuencial
                val timeSeq = runSecuencial(tasksCount)

                // Ejecución Concurrente
                val timeConc = runConcurrente(tasksCount)

                listaResultados.add(
                    BenchMarKResult(
                        factor = factorLabel,
                        numTasks = tasksCount,
                        timeSequentialMs = timeSeq,
                        timeConcurrentMs = timeConc
                    )
                )

                // Actualizamos la lista en la UI en tiempo real
                _uiState.update { it.copy(results = listaResultados.toList()) }
            }

            _uiState.update {
                it.copy(isRunning = false, currentLog = "¡Pruebas finalizadas exitosamente!")
            }
        }
    }

}