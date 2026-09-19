package com.mexiti.corrutinasapp_2027_i

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.mexiti.corrutinasapp_2027_i.ui.CoroutinesApp
import com.mexiti.corrutinasapp_2027_i.ui.theme.CorrutinasApp2027ITheme
import com.mexiti.corrutinasapp_2027_i.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            CorrutinasApp2027ITheme(false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CoroutinesApp(
                        viewModel,
                        modifier = Modifier.padding(
                            innerPadding
                        ))
                }
            }
        }
    }
}

