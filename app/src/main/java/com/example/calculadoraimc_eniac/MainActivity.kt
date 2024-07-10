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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc_eniac.ui.theme.CalculadoraIMCEniacTheme
import com.example.calculadoraimc_eniac.ui.theme.WHITE
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCEniacTheme {
                CalculadoraIMC()
            }
        }
    }
}


@Composable
fun CalculadoraIMC() {

    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    var mostrarResultado by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Calculadora IMC", fontSize = 25.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = WHITE
                ),
                actions = {
                    IconButton(onClick = { peso = ""; altura = ""; mostrarResultado = false }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Zerar",
                            tint = WHITE
                        )
                    }

                }
            )
        },
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Calcule seu IMC",
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = peso.replace(",", "."),
                onValueChange = { novoPeso ->
                    peso = novoPeso
                },
                label = { Text(text = "Peso") },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.secondary
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = altura.replace(",", "."),
                onValueChange = { novaAltura ->
                    altura = novaAltura
                },
                label = { Text(text = "Altura") },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.secondary
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    resultado = calcularIMC(peso.toDouble(), altura.toDouble())
                    mostrarResultado = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = WHITE
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp)
            ) {
                Text(text = "Calcular", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))

            if (mostrarResultado) {
                Text(text = "Resultado: $resultado")
            }
        }
    }
}


fun calcularIMC(peso: Double, altura: Double): String {

    val imc = peso / (altura * altura)
    val decimalFormat = DecimalFormat("0.00")

    val resultado = when {
        imc < 18.5 -> "Peso Baixo \n IMC: ${decimalFormat.format(imc)}"
        imc < 24.9 -> "Peso Normal \n IMC: ${decimalFormat.format(imc)}"
        imc < 29.9 -> "Sobrepeso \n IMC: ${decimalFormat.format(imc)}"
        imc < 34.9 -> "Obesidade (Grau 1) \n IMC: ${decimalFormat.format(imc)}"
        imc < 39.9 -> "Obesidade Severa (Grau 2) \n IMC: ${decimalFormat.format(imc)}"
        else -> "Obesidade Mórbida (Grau 3) \n IMC: ${decimalFormat.format(imc)}"
    }
    return resultado
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculadoraIMCEniacTheme {
        CalculadoraIMC()
    }
}