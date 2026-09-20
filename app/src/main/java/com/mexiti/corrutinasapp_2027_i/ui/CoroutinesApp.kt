package com.mexiti.corrutinasapp_2027_i.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
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
fun CoroutinesApp(viewModel: MainViewModel, modifier: Modifier = Modifier) {
  CoroutinesAppContent(
      viewModel.resultState,
      { viewModel.fetchDataTimer() },
      {viewModel.limpiarContadores()},
      viewModel.countTime,
      isRunning = viewModel.isRunning
  )
}

@Composable
fun CoroutinesAppContent(
  resultState: String,
  onFetchData: () -> Unit,
  reset:() -> Unit,
  timer:Int,
  isRunning: Boolean,
  modifier : Modifier = Modifier
){
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
        Text("$timer [s]")

        Spacer(modifier.height(20.dp))

        Text(resultState)
        Spacer(modifier.height(30.dp))
        Button(
            {
                 onFetchData()
        },
            enabled = !isRunning
        ) {
            Text(stringResource(R.string.realizar_consulta))
        }
        Spacer( modifier.height(10.dp))
        Button(
            {reset},
            colors = ButtonDefaults.buttonColors(Color.DarkGray)
        ) {
            Text("Reset")
        }
    }
}


@Preview
@Composable
fun CoroutinesAppPreview(){
    CorrutinasApp2027ITheme(darkTheme = false) {
        CoroutinesAppContent(
            "Respuesta de la Web",
            {},
            {},
            4,
            true,
        )
    }
}
