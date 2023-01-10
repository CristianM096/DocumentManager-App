package com.sophos.documentmanager.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.sophos.documentmanager.R
import com.sophos.documentmanager.ui.navigation.Destinations
import com.sophos.documentmanager.ui.theme.SophosLight

@Composable
fun HamburgerMenu(navController: NavController, auth:String){
    var showMenu: Boolean by remember { mutableStateOf(false) }
    IconButton(
        onClick = { showMenu = true }
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.menu2),
            contentDescription = "Menu",
            tint = SophosLight
        )
        DropdownMenu(
            expanded = showMenu, onDismissRequest = { showMenu = false },
        ) {
            DropdownMenuItem(
                onClick = {
                    showMenu = false
                    navController.navigate(route = Destinations.Home.route + "/" + auth)},
                ) {
                Text(
                    text = "Pagina Principal", color = SophosLight,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            DropdownMenuItem(
                onClick = {
                    showMenu = false
                    navController.navigate(route = Destinations.DocumentCreate.route + "/" + auth)},

                ) {
                Text(
                    text = "Enviar documentos", color = SophosLight,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            DropdownMenuItem(
                onClick = {
                    showMenu = false
                    navController.navigate(route = Destinations.DocumentShow.route + "/" + auth)}
            ) {
                Text(
                    text = "Ver documentos", color = SophosLight,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            DropdownMenuItem(
                onClick = {
                    showMenu = false
                    navController.navigate(route = Destinations.OfficeShow.route + "/" + auth)}
            ) {
                Text(
                    text = "Oficinas", color = SophosLight,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            DropdownMenuItem(
                onClick = {
                    showMenu = false/*TODO: Cambio de Color*/ }
            ) {
                Text(
                    text = "Modo nocturno", color = SophosLight,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            DropdownMenuItem(
                onClick = { showMenu = false/*TODO: Cambio de Idioma*/ }
            ) {
                Text(
                    text = "Idioma Inglés", color = SophosLight,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            DropdownMenuItem(
                onClick = {
                    navController.navigate(route = Destinations.Login.route)
                    showMenu = false },

                ) {
                Text(
                    text = "Cerrar Sesion", color = SophosLight,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}