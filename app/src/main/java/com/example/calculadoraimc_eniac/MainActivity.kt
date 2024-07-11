@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.calculadoraimc_eniac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculadoraimc_eniac.ui.telas.CalculadoraIMC
import com.example.calculadoraimc_eniac.ui.telas.Login
import com.example.calculadoraimc_eniac.ui.theme.CalculadoraIMCEniacTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita o modo edge-to-edge
        setContent {
            CalculadoraIMCEniacTheme {
                val navController =
                    rememberNavController() // Cria o NavController para gerenciar a navegação

                // Define o NavHost com duas telas: login e calculadoraIMC
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { Login(navController) } // Tela de login
                    composable("calculadoraIMC") { CalculadoraIMC(navController) } // Tela da calculadora IMC
                }
            }
        }
    }
}
