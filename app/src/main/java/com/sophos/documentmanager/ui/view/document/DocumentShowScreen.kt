package com.sophos.documentmanager.ui.view.document

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sophos.documentmanager.R
import com.sophos.documentmanager.ui.components.ReturnButton
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
        content = { contentDocumentShow(viewModel,navController) },
        topBar = { topBar(navController,
            {
                ReturnButton(navController)
            },
            auth = auth) }
    )
}
@Composable
fun contentDocumentShow(viewModel: DocumentShowViewModel, navController: NavController){
    val attachments = viewModel.attachments
    var id = remember { mutableStateOf(R.drawable.home_corporative) }

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.align(Alignment.TopCenter)) {
            Column() {
                Text(
                    text = "Documentos",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            LazyColumn( modifier = Modifier.fillMaxWidth() ){
                item(attachments.size){
                    attachments.forEach{
                        attachment ->
                        DocumentItem(
                            onClick = {
                                navController.navigate(route = Destinations.ImageShow.route + "/" + viewModel.auth+ "/" + attachment.idAttachment)
                            },
                            date = attachment.date,
                            reason = attachment.attachmentType,
                            name = "${attachment.userName} ${attachment.userSurname}"
                        )
                    }
                }
            }

        }
    }
}
@Composable
fun DocumentItem(onClick:()->Unit, date:String, reason:String,name:String ,){
    TextButton(onClick = { onClick() },
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Text(text = "$date - $reason\n\n$name",
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal
        )
    }

}