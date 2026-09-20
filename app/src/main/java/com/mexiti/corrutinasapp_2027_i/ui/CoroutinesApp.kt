package com.mexiti.corrutinasapp_2027_i.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
        Text("Tiempo de respuesta del servidor")
        Spacer(modifier.height(10.dp))
        Text("${viewModel.countTime} [s]")
        Spacer(modifier.height(30.dp))
        Text(viewModel.resultState)
        Spacer(modifier.height(30.dp))
        Button({
                 viewModel.fetchDataNoSincronizado()
        }) {
            Text(stringResource(R.string.realizar_consulta))
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