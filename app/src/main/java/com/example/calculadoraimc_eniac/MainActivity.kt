@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.calculadoraimc_eniac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculadoraimc_eniac.ui.componentes.CaixaSaidaTexto
import com.example.calculadoraimc_eniac.ui.componentes.EntradaTexto
import com.example.calculadoraimc_eniac.ui.telas.CalculadoraIMC
import com.example.calculadoraimc_eniac.ui.telas.Login
import com.example.calculadoraimc_eniac.ui.theme.CalculadoraIMCEniacTheme
import com.example.calculadoraimc_eniac.ui.theme.WHITE
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita o modo edge-to-edge
        setContent {
            CalculadoraIMCEniacTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login" ){
                    composable("login"){ Login(navController)}
                    composable("calculadoraIMC"){ CalculadoraIMC(navController)}
                }
            }
        }
    }
}




