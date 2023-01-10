package com.sophos.documentmanager.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sophos.documentmanager.ui.theme.SophosLightDisable

@Composable
fun ContentOptions(
    textOption: String,
    imageVector: ImageVector,
    color: Color,
    entryFunction: () -> Unit
) {
    Column(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = color,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 10.dp, vertical = 15.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = textOption,
                tint = color,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(text = textOption, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = color)
        }
        Button(
            onClick = { entryFunction() },

            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, color),
            modifier = Modifier
                .align(Alignment.End)
                .size(height = 40.dp, width = 150.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                disabledBackgroundColor = Color.White,
                contentColor = color,
                disabledContentColor = SophosLightDisable,
            )
        ) {
            Text(text = "Ingresar", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = color)
            Icon(Icons.Default.ArrowForward, contentDescription = "Flecha para ingresar")
        }
    }
}