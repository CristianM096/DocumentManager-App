package com.sophos.documentmanager.ui.view.document

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sophos.documentmanager.R
import com.sophos.documentmanager.ui.components.ReturnButton
import com.sophos.documentmanager.ui.components.topBar
import com.sophos.documentmanager.ui.theme.SophosLight


@Composable
fun CopyScren(navController: NavController) {
    Copy(navController)
}

@Composable
fun Copy(navController: NavController) {

    Scaffold(content = { contentCopy() }, topBar = {
        topBar(
            navController, {
                ReturnButton(navController)
            }, auth = "Cualquier Cosa"
        )
    })
}

@Composable
fun contentCopy() {
    val contextForToast = LocalContext.current.applicationContext //TODO: Probar este contexto
    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")
    val listCities = arrayOf("Cali","Popayab", "Bogota", "Medellin")
    val selectedItem: String by remember { mutableStateOf(listItems[0]) }
    val selectedCity: String by remember { mutableStateOf(listCities[0]) }
    val expanded by remember { mutableStateOf(false) }
    Box(Modifier.fillMaxSize()) {
        Text(
            text = "Documentos",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.add_a_photo),
                contentDescription = "Agregar una foto o imagen"
            )
        }
        Box() {
            TextField(value = selectedItem, onValueChange = {})
            DropdownMenu(expanded = expanded, onDismissRequest = { !expanded/*TODO*/ }) {
                listItems.forEach {selectedOption->
                    DropdownMenuItem(onClick = { /*TODO*/ }) {
                        Text(text = selectedOption )
                    }
                }

            }
        }
        DocumentField(fieldEntry = "Nombres", onTextFieldChanged = {})
        DocumentField(fieldEntry = "Apellidos", onTextFieldChanged = {})
        DocumentField(fieldEntry = "Ciudad", onTextFieldChanged = {})
        Box() {
            TextField(value = selectedCity, onValueChange = {})
            DropdownMenu(expanded = expanded, onDismissRequest = { !expanded/*TODO*/ }) {
                listCities.forEach {selectedOption->
                    DropdownMenuItem(onClick = { /*TODO*/ }) {
                        Text(text = selectedOption )
                    }
                }

            }
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = Documento)
        }
    }
}
@Composable
fun DocumentField(fieldEntry: String, onTextFieldChanged: (String) -> Unit) {
    OutlinedTextField(
        value = fieldEntry, onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = SophosLight,
            backgroundColor = Color.White,
            focusedIndicatorColor = SophosLight,
            unfocusedIndicatorColor = SophosLight,
        ),
        shape = RoundedCornerShape(12.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "User",
                tint = SophosLight
            )
        },

        )
}
@