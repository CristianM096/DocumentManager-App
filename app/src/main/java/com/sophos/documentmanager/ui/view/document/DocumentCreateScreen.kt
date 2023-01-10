package com.sophos.documentmanager.ui.view.document

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.sophos.documentmanager.ui.components.topBar
import com.sophos.documentmanager.ui.viewmodel.DocumentCreateViewModel

@Composable
fun DocumentCreateScreen(navController: NavController, viewModel: DocumentCreateViewModel, auth:String?) {
    if(!viewModel.created){
        viewModel.onCreate(auth)
    }
    DocumentCreate(navController = navController, viewModel = viewModel)
}
@Composable
fun DocumentCreate(navController:NavController,viewModel: DocumentCreateViewModel){
    val auth:String by viewModel.auth.observeAsState(initial = "")

    Scaffold(
        content = { contentDocumentCreate(viewModel) },
        topBar = { topBar(navController,
            { navController },
            auth = auth) }
    )
}
@Composable
fun contentDocumentCreate(viewModel: DocumentCreateViewModel){
    Text(text = viewModel.auth.value?:"" + "DocCreate")
}
