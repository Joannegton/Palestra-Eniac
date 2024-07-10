@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.calculadoraimc_eniac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc_eniac.ui.theme.CalculadoraIMCEniacTheme
import com.example.calculadoraimc_eniac.ui.theme.WHITE
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita o modo edge-to-edge
        setContent {
            CalculadoraIMCEniacTheme {
                CalculadoraIMC() // Chama a função composable principal
            }
        }
    }
}

@Composable
fun CalculadoraIMC() {

    var peso by remember { mutableStateOf("") } // Estado para armazenar o peso
    var altura by remember { mutableStateOf("") } // Estado para armazenar a altura
    var resultado by remember { mutableStateOf("") } // Estado para armazenar o resultado do cálculo
    var mostrarResultado by remember { mutableStateOf(false) } // Estado para controlar a visibilidade do resultado

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Calculadora IMC", fontSize = 25.sp) }, // Título da barra superior
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = WHITE
                ),
                actions = {
                    IconButton(onClick = { peso = ""; altura = ""; mostrarResultado = false } // Botão para resetar os campos
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

            OutlinedTextField(
                value = peso.replace(",", "."), // Substitui vírgula por ponto no valor do peso
                onValueChange = { novoPeso ->
                    peso = novoPeso // Atualiza o estado do peso
                },
                label = { Text(text = "Peso") }, // Rótulo do campo de texto
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.secondary
                ),
                singleLine = true, // Define que o campo de texto é de uma única linha
                modifier = Modifier
                    .fillMaxWidth() // O campo de texto ocupa a largura total disponível
                    .padding(20.dp, 0.dp, 20.dp, 0.dp), // Espaçamento interno
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal, // Define o teclado numérico
                    imeAction = ImeAction.Next // Ação de avançar no teclado
                )
            )
            Spacer(modifier = Modifier.height(15.dp)) // Espaço vertical

            OutlinedTextField(
                value = altura.replace(",", "."), // Substitui vírgula por ponto no valor da altura
                onValueChange = { novaAltura ->
                    altura = novaAltura // Atualiza o estado da altura
                },
                label = { Text(text = "Altura") }, // Rótulo do campo de texto
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.secondary
                ),
                singleLine = true, // Define que o campo de texto é de uma única linha
                modifier = Modifier
                    .fillMaxWidth() // O campo de texto ocupa a largura total disponível
                    .padding(20.dp, 0.dp, 20.dp, 0.dp), // Espaçamento interno
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal, // Define o teclado numérico
                    imeAction = ImeAction.Done // Ação de finalização no teclado
                )
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

@Composable
fun CaixaSaidaTexto(resultado: String){
    Column {
        Row {
            Image(
                painter = painterResource(id = R.drawable.baseline_info_24),
                contentDescription = "informações",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = resultado, textAlign = TextAlign.Center, fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val res = calcularIMC(100.0, 1.80)
    CalculadoraIMCEniacTheme {
        CalculadoraIMC() // Chama a função composable principal no modo de preview
    }
}
