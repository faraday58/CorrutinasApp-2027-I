package com.mexiti.corrutinasapp_2027_i.ui

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mexiti.corrutinasapp_2027_i.R
import com.mexiti.corrutinasapp_2027_i.ui.theme.CorrutinasApp2027ITheme
import com.mexiti.corrutinasapp_2027_i.viewmodel.MainViewModel

@Composable
fun CoroutinesApp(viewModel: MainViewModel, modifier: Modifier = Modifier){
    var changeColor by remember {
        mutableStateOf(false)
    }


    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
            changeColor = !changeColor
        },
            colors = ButtonDefaults.buttonColors(
                if (changeColor) Color.Red else Color.Blue
            )

        ) {
            Text(stringResource(R.string.cambio_de_color))
        }
        Spacer(modifier.height(30.dp) )
        //Monitor de Estado y Contador
        Text(
            text = "Tiempo transcurrido: ${viewModel.countTime } [s] ",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = viewModel.resultState,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(16.dp))
        if(viewModel.isLoading){
            CircularProgressIndicator()
        }

        Spacer(Modifier.height(20.dp))
        //Controles de Sincronización
        Button(
            onClick = {viewModel.fetchDataSecuencial()},
            enabled = !viewModel.isLoading
        ) {
            Text("Sincronización Secuencial")
        }

        Spacer(Modifier.height(10.dp))
        Button(
            onClick = {viewModel.fetchDataSincronizada()},
            enabled = !viewModel.isLoading
        ) {
            Text("Sincronización Paralela (async/await)")
        }

    }
}

/*
@Preview
@Composable
fun CoroutinesAppPreview(){
    CorrutinasApp2027ITheme(darkTheme = false) {
        CoroutinesApp()
    }
}
*/