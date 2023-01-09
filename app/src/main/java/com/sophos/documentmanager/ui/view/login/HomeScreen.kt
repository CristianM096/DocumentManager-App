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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sophos.documentmanager.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
@Preview
@Composable
fun preview(){

}

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
}