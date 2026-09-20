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




    fun fetchData(){

        viewModelScope.launch {
            delay(5000)
            resultState = "Respuesta obtenida de la Web"
        }
    }



 /*   fun bloqueoApp(){
        Thread.sleep(5000)
        resultState = "Respuesta obtenida de la Web"
    }*/

}