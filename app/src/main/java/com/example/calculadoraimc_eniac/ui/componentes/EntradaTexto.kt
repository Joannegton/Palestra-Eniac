package com.example.calculadoraimc_eniac.ui.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun EntradaTexto(
    valor: String, // Valor atual do campo de texto
    funcao: (String) -> Unit, // Função para atualizar o valor
    label: String, // Rótulo do campo de texto
    senha: Boolean = false, // Indica se o campo de texto é uma senha
    teclado: KeyboardType = KeyboardType.Text, // Tipo de teclado
    acao: ImeAction = ImeAction.Next // Ação de finalização no teclado
){
    OutlinedTextField(
        value = valor, // Valor atual do campo de texto
        onValueChange = funcao, // Função para atualizar o valor
        label = { Text(text = label) }, // Rótulo do campo de texto
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
            keyboardType = teclado, // Define o teclado numérico
            imeAction = acao // Ação de finalização no teclado
        ),
        visualTransformation = if (senha) PasswordVisualTransformation() else VisualTransformation.None
    )
}