package com.sophos.documentmanager.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sophos.documentmanager.ui.theme.SophosLight
import com.sophos.documentmanager.ui.theme.SophosLightDisable

@Preview
@Composable
fun preview(){
    
}

@Composable
fun ReturnButton(navController: NavController){
    TextButton(
        onClick = { navController.popBackStack() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            disabledBackgroundColor = Color.Transparent,
            contentColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
        )
    ) {
        Icon(Icons.Default.ArrowBack, contentDescription = "Flecha para regresar", tint = SophosLight, modifier = Modifier.size(28.dp))
        Text(text = "Regresar", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = SophosLight)
    }
}