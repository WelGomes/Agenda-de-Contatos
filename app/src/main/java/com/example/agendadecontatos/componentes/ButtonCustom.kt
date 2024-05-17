package com.example.agendadecontatos.componentes

import Purple500
import WHITE
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ButtonCustom(
    onClick: () -> Unit,
    modifier: Modifier,
    texto: String
) {

    androidx.compose.material.Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Purple500),
        modifier = modifier
    ) {
        Text(
            text = "$texto",
            color = WHITE,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }

}