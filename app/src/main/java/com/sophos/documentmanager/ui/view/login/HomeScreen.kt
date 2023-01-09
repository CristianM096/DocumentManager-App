package com.sophos.documentmanager.ui.view.login

import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sophos.documentmanager.R
import com.sophos.documentmanager.ui.theme.*
import com.sophos.documentmanager.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    Scaffold(
        content = { content(Modifier.background(Color.White)) },
        topBar = { topBar() }
    )
}

@Composable
fun content(modifier: Modifier) {
    ContentBody(modifier = modifier)
    ContentImageCorporative(modifier = modifier.fillMaxWidth())
    ContentTextImage(modifier = modifier)
}

@Composable
fun topBar() {
    val options = listOf( // (2)
        "Cambiar nombre",
        "Enviar por email",
        "Copiar enlace",
        "Ocultar subtareas completas",
        "Eliminar"
    )
    var showMenu: Boolean by remember { mutableStateOf(false) }
    TopAppBar(

        backgroundColor = Color.White,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
        modifier = Modifier.fillMaxWidth(),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Cristian",
                    color = SophosLight,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
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
                            onClick = { showMenu = false/*TODO: Cambio de Screen*/ },

                            ) {
                            Text(
                                text = "Enviar documentos", color = SophosLight,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        DropdownMenuItem(
                            onClick = { showMenu = false/*TODO: Cambio de Screen*/ }
                        ) {
                            Text(
                                text = "Ver documentos", color = SophosLight,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        DropdownMenuItem(
                            onClick = { showMenu = false/*TODO: Cambio de Screen*/ }
                        ) {
                            Text(
                                text = "Oficinas", color = SophosLight,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        DropdownMenuItem(
                            onClick = { showMenu = false/*TODO: Cambio de Color*/ }
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
                    }
                }

            }
        }
    )
}

@Composable
fun ContentImageCorporative(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.home_corporative),
        contentDescription = "Corporation Image",
        modifier = modifier.fillMaxWidth(),
        alignment = Alignment.TopCenter,
    )

}

@Composable
fun ContentTextImage(modifier: Modifier){
    Column(modifier = Modifier
        .padding(20.dp, 30.dp)
        .background(Color.Transparent)
        .fillMaxSize())  {
        Text(
            text = "Bienvenido",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.padding(30.dp))
        Text(
            text = "Estas son las opciones \nque tenemos para ti",
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(24.dp),
            textAlign = TextAlign.Center
        )    
    }
    
}
@Composable
fun ContentBody(modifier: Modifier) {
    LazyColumn(modifier = modifier
        .padding(20.dp, 30.dp)
        .background(Color.Black)
        .fillMaxSize()) {
        item{
            Spacer(modifier = Modifier.padding(140.dp))
            ContentOptions("Enviar Documentos", ImageVector.vectorResource(R.drawable.send_doc),
                SendDocsColorLight, {/*TODO: Agregar funcion para ir a Enviar documentos*/ })
            Spacer(modifier = Modifier.padding(10.dp))
            ContentOptions("Ver Documentos", ImageVector.vectorResource(R.drawable.show_docs),
                ShowDocsColorLight, {/*TODO: Agregar funcion para ir a Ver documentos*/ })
            Spacer(modifier = Modifier.padding(10.dp))
            ContentOptions("Oficinas", ImageVector.vectorResource(R.drawable.office),
                MapColorLight, {/*TODO: Agregar funcion para ir a Oficinas*/ })
            Spacer(modifier = Modifier.padding(10.dp))
        }

    }
}

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