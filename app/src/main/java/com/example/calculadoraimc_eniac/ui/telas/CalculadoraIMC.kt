package com.example.calculadoraimc_eniac.ui.telas

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.calculadoraimc_eniac.ui.componentes.CaixaSaidaTexto
import com.example.calculadoraimc_eniac.ui.componentes.EntradaTexto
import com.example.calculadoraimc_eniac.ui.theme.CalculadoraIMCEniacTheme
import com.example.calculadoraimc_eniac.ui.theme.WHITE
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculadoraIMC(navController: NavController) {

    var peso by remember { mutableStateOf("") } // Estado para armazenar o peso
    var altura by remember { mutableStateOf("") } // Estado para armazenar a altura
    var resultado by remember { mutableStateOf("") } // Estado para armazenar o resultado do cálculo
    var mostrarResultado by remember { mutableStateOf(false) } // Estado para controlar a visibilidade do resultado

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Calculadora IMC",
                        fontSize = 25.sp
                    )
                }, // Título da barra superior
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = WHITE
                ),
                actions = {
                    IconButton(onClick = {
                        peso = ""; altura = ""; mostrarResultado = false
                    } // Botão para resetar os campos
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
        containerColor = Color.White // Cor de fundo da tela
    ) {
        Column(
            modifier = Modifier.padding(it), // Espaçamento interno
            horizontalAlignment = Alignment.CenterHorizontally // Alinhamento horizontal centralizado
        ) {
            Spacer(modifier = Modifier.height(30.dp)) // Espaço vertical
            Text(
                text = "Calcule seu IMC",
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(20.dp)) // Espaço vertical

            EntradaTexto(
                valor = peso.replace(",", "."), // Substitui vírgula por ponto no valor do peso
                funcao = { novoPeso ->
                    peso = novoPeso // Atualiza o estado do peso
                },
                label = "Peso",// Rótulo do campo de texto
                teclado = KeyboardType.Decimal // Tipo de teclado para números decimais
            )
            Spacer(modifier = Modifier.height(15.dp)) // Espaço vertical

            EntradaTexto(
                valor = altura.replace(",", "."), // Substitui vírgula por ponto no valor da altura
                funcao = { novaAltura -> // Função para atualizar o estado da altura
                    altura = novaAltura // Atualiza o estado da altura
                },
                label = "Altura",// Rótulo do campo de texto
                teclado = KeyboardType.Decimal, // Tipo de teclado para números decimais
                acao = ImeAction.Done, // Ação de finalização do teclado
            )
            Spacer(modifier = Modifier.height(20.dp)) // Espaço vertical

            Button(
                onClick = {
                    resultado = calcularIMC(peso.toDouble(), altura.toDouble()) // Calcula o IMC
                    mostrarResultado = true // Exibe o resultado
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = WHITE
                ),
                modifier = Modifier
                    .fillMaxWidth() // O botão ocupa a largura total disponível
                    .padding(20.dp, 0.dp, 20.dp, 0.dp) // Espaçamento interno
            ) {
                Text(text = "Calcular", fontSize = 20.sp) // Texto do botão
            }
            Spacer(modifier = Modifier.height(30.dp)) // Espaço vertical

            if (mostrarResultado) {
                CaixaSaidaTexto(resultado = resultado) // Exibe o resultado do cálculo
            }


        }
    }
}

fun calcularIMC(peso: Double, altura: Double): String {

    val imc = peso / (altura * altura) // Calcula o IMC
    val decimalFormat = DecimalFormat("0.00") // Formata o resultado para duas casas decimais

    // Verifica a faixa do IMC e retorna a classificação correspondente
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
    val res = calcularIMC(100.0, 1.80)
    CalculadoraIMCEniacTheme {
        CalculadoraIMC(navController = rememberNavController()) // Chama a função composable principal no modo de preview
    }
}