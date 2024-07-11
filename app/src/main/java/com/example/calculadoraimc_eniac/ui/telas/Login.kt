package com.example.calculadoraimc_eniac.ui.telas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.calculadoraimc_eniac.ui.componentes.EntradaTexto
import com.example.calculadoraimc_eniac.ui.theme.WHITE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController) {
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
                )
            )
        },
        containerColor = Color.White // Cor de fundo da tela
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            var usuario by remember { mutableStateOf("") }
            var senha by remember { mutableStateOf("") }

            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Realize seu login",
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(30.dp))

            // Campo de entrada de texto para o usuário
            EntradaTexto(
                valor = usuario,
                funcao = { novoUsuario -> usuario = novoUsuario },
                label = "Usuario"
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Campo de entrada de texto para a senha
            EntradaTexto(
                valor = senha,
                funcao = { novaSenha -> senha = novaSenha },
                label = "Senha",
                teclado = KeyboardType.Password,
                acao = ImeAction.Done,
                senha = true
            )
            Spacer(modifier = Modifier.height(15.dp))

            // Botão de login
            Button(
                onClick = {
                    if (usuario == "admin" && senha == "123") {
                        // Navega para a tela da calculadora IMC se o login for bem-sucedido
                        navController.navigate("calculadoraIMC")
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = WHITE
                ),
                modifier = Modifier
                    .fillMaxWidth() // O botão ocupa a largura total disponível
                    .padding(20.dp, 0.dp, 20.dp, 0.dp) // Espaçamento interno
            ) {
                Text(text = "Entrar", fontSize = 20.sp) // Texto do botão
            }
        }
    }
}

@Preview
@Composable
private fun Prev() {
    Login(navController = rememberNavController())
}
