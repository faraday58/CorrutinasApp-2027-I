package com.mexiti.corrutinasapp_2027_i.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class MainViewModel:ViewModel(){

    var resultState by mutableStateOf("")
        private set

    var countTime by mutableStateOf(0)
        private set
    var isLoading by mutableStateOf(false)
        private set


    fun fetchData(){

        viewModelScope.launch {
            delay(5000)
            resultState = "Respuesta obtenida de la Web"
        }
    }

    fun fetchDataSecuencial(){
        viewModelScope.launch {
            resetState()
            isLoading = true
            //Primer Paso
            resultState = "Iniciando descarga ..."
            for (i in 1..3){
                delay(1000)
                countTime = i
            }
            //Segundo paso se ejecuta hasta que termina el anterior
            resultState = "Procesando datos ..."
            delay(2000)
            resultState = "¡Proceso terminado exitosamente"
            isLoading = false
        }
    }

    fun fetchDataSincronizada(){
        viewModelScope.launch {
            resetState()
            isLoading = true
            resultState = "Consultando 2 APIs en paralelo ..."

            //Lanzamos dos tareas concurrentes
            val job1 = async {
                for ( i in 1..5){
                    delay(1000)
                    countTime = 1
                }
                "Datos de Usuario Ok"
            }
            val job2 = async {
                delay(3000)
                "Configuración Ok"
            }
            //Esperar el resultado de ambas tareas
            val res1 = job1.await()
            val res2 = job2.await()

            resultState = "Sincronizando $res1  y $res2"
            isLoading  = false

        }
    }

    private fun resetState(){
        countTime = 0
        resultState = ""
    }

 /*   fun bloqueoApp(){
        Thread.sleep(5000)
        resultState = "Respuesta obtenida de la Web"
    }*/

}