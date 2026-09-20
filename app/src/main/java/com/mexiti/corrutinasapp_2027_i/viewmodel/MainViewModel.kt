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
    var countN by mutableStateOf(2)
        private set
    var countTime by mutableStateOf(0)
        private set

    var isRunning by mutableStateOf(false)
        private set



    fun fetchDataTimer( ){
        viewModelScope.launch {
            isRunning = true
         for (i in 1..countN){
             delay(1000)
             countTime = i
         }
            resultState = "Respuesta obtenida de la Web"
            countN ++
            isRunning = false
        }
    }

    fun fetchData(){

        viewModelScope.launch {
            delay(5000)
            resultState = "Respuesta obtenida de la Web"
        }
    }

    fun limpiarContadores(){
        resultState = ""
        countN = 2
        countTime = 0
    }



 /*   fun bloqueoApp(){
        Thread.sleep(5000)
        resultState = "Respuesta obtenida de la Web"
    }*/

}