package com.sophos.documentmanager.ui.view.office

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
import com.sophos.documentmanager.ui.theme.SophosLight
import com.sophos.documentmanager.ui.view.login.HamburgerMenu
import com.sophos.documentmanager.ui.view.login.Home
import com.sophos.documentmanager.ui.viewmodel.OfficeShowViewModel


@Composable
fun OfficeShowScreen(navController: NavController, viewModel: OfficeShowViewModel, auth:String?) {
    if(!viewModel.created){
        viewModel.onCreate(auth)
    }
    OfficeShow(navController = navController, viewModel = viewModel)
}
@Composable
fun OfficeShow(navController: NavController, viewModel: OfficeShowViewModel){
    val auth:String by viewModel.auth.observeAsState(initial = "")
    Scaffold(
        content = { contentOfficeShow(viewModel) },
        topBar = { topBar(navController,"Office",{}, auth = auth) }
    )
}
@Composable
fun contentOfficeShow(viewModel: OfficeShowViewModel){
    Text(text = viewModel.auth.value?:"" + "Office")
}