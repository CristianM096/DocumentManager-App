package com.sophos.documentmanager.ui.view.login

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
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sophos.documentmanager.R
import com.sophos.documentmanager.ui.components.ContentOptions
import com.sophos.documentmanager.ui.components.topBar
import com.sophos.documentmanager.ui.navigation.Destinations
import com.sophos.documentmanager.ui.theme.*
import com.sophos.documentmanager.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavController,viewModel: HomeViewModel, auth:String?) {

    if(!viewModel.created){
        viewModel.onCreate(auth)
    }
    Home(navController = navController, viewModel = viewModel)

}
@Composable
fun Home(navController:NavController, viewModel: HomeViewModel){
    val auth:String by viewModel.auth.observeAsState(initial = "")
    Scaffold(
        content = { content(Modifier.background(Color.White), viewModel = HomeViewModel(),navController) },
        topBar = {
            topBar(navController,
            leftElement = { Text(
                text = viewModel.user.name,
                color = SophosLight,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )},
            auth = auth)
        }
    )
}

@Composable
fun content(modifier: Modifier,viewModel: HomeViewModel, navController: NavController) {
    ContentBody(modifier = modifier, viewModel,navController)
    ContentImageCorporative(modifier = modifier.fillMaxWidth())
    ContentTextImage(modifier = modifier)
}



@Composable
fun HamburgerMenu(navController: NavController){
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
                    navController.navigate(route = Destinations.DocumentCreate.route)},

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
                    navController.navigate(route = Destinations.DocumentShow.route)}
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
                    navController.navigate(route = Destinations.OfficeShow.route)}
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
fun ContentBody(modifier: Modifier, viewModel: HomeViewModel,navController: NavController) {
    LazyColumn(modifier = modifier
        .padding(20.dp, 30.dp)
        .fillMaxSize()) {
        item{
            Spacer(modifier = Modifier.padding(140.dp))
            ContentOptions("Enviar Documentos", ImageVector.vectorResource(R.drawable.send_doc),
                SendDocsColorLight, { navController.navigate(route = Destinations.DocumentCreate.route + "/" + viewModel.auth) })
            Spacer(modifier = Modifier.padding(10.dp))
            ContentOptions("Ver Documentos", ImageVector.vectorResource(R.drawable.show_docs),
                ShowDocsColorLight, {navController.navigate(route = Destinations.DocumentShow.route + "/" + viewModel.auth)})
            Spacer(modifier = Modifier.padding(10.dp))
            ContentOptions("Oficinas", ImageVector.vectorResource(R.drawable.office),
                MapColorLight, {navController.navigate(route = Destinations.OfficeShow.route + "/" + viewModel.auth) })
            Spacer(modifier = Modifier.padding(10.dp))
        }

    }
}