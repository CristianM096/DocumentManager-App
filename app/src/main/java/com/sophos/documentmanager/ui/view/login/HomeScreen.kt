package com.sophos.documentmanager.ui.view.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sophos.documentmanager.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: HomeViewModel){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        Home(Modifier.align(Alignment.Center), viewModel)
    }
}
@Composable
fun  Home(modifier: Modifier, viewModel: HomeViewModel){
    val email: String by viewModel.email.observeAsState(initial = "cristianorb@unicauca.edu.co")
    val password: String by viewModel.password.observeAsState(initial = "PIpP0553v058")
    val coroutineScope = rememberCoroutineScope()
    Column {
        Text(text = email + " " + password)
        button(true) {
            coroutineScope.launch {
                viewModel.onLoginChanged(email,password)
            }
        }
    }
}

@Composable
fun button(loginEnable: Boolean, onLoginChanged: () -> Unit) {
    Button(
        onClick = { onLoginChanged() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF4303),
            disabledBackgroundColor = Color(0xFFF78058),
            contentColor = Color.White,
            disabledContentColor = Color.White),
        enabled = loginEnable) {
        Text(text = "Login")
    }
}