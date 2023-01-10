package com.sophos.documentmanager.ui.view.document

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sophos.documentmanager.ui.components.topBar
import com.sophos.documentmanager.ui.navigation.Destinations
import com.sophos.documentmanager.ui.theme.SophosLight
import com.sophos.documentmanager.ui.view.login.HamburgerMenu
import com.sophos.documentmanager.ui.viewmodel.DocumentShowViewModel
import com.sophos.documentmanager.ui.viewmodel.HomeViewModel


@Composable
fun DocumentShowScreen(navController: NavController, viewModel: DocumentShowViewModel, auth:String?) {
    if(!viewModel.created){
        viewModel.onCreate(auth)
    }
    DocumentShow(navController = navController, viewModel = viewModel)
}
@Composable
fun DocumentShow(navController: NavController, viewModel: DocumentShowViewModel){
    val auth:String by viewModel.auth.observeAsState(initial = "")
    Scaffold(
        content = { contentDocumentShow(viewModel) },
        topBar = { topBar(navController,
            {

            },
            auth = auth) }
    )
}
@Composable
fun contentDocumentShow(viewModel: DocumentShowViewModel){
    Text(text = viewModel.auth.value?:"" + "DocShow")
}