package com.example.calculadoraimc_eniac.ui.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc_eniac.R

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