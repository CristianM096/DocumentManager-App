package com.sophos.documentmanager.ui.view.document

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.navigation.NavController
import com.sophos.documentmanager.ui.components.ReturnButton
import com.sophos.documentmanager.ui.components.topBar
import com.sophos.documentmanager.ui.viewmodel.ImageShowViewModel

@Composable
fun ImageShowScreen(
    navController: NavController,
    viewModel: ImageShowViewModel,
    auth: String?,
    idAttachment: String?
) {
    ImageShow(navController = navController, viewModel = viewModel, auth, idAttachment)
    viewModel.getAttachment(idAttachment ?: "")
}

@Composable
fun ImageShow(
    navController: NavController,
    viewModel: ImageShowViewModel,
    auth: String?,
    idAttachment: String?
) {
    Scaffold(
        content = { contentImageShow(viewModel, idAttachment) },
        topBar = {
            topBar(
                navController,
                { ReturnButton(navController) },
                auth = auth ?: ""
            )
        }
    )
}

@Composable
fun contentImageShow(viewModel: ImageShowViewModel, idAttachment: String?) {
    val currentImage: String by viewModel.currentImage.observeAsState("")
    val state: Boolean by viewModel.state.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Box(
            Modifier.fillMaxSize()
        ) {
            if (state) {
                Column(Modifier.align(Alignment.Center)) {
                    Image(
                        bitmap = BitmapFactory.decodeByteArray(
                            Base64.decode(currentImage, Base64.DEFAULT),
                            0,
                            Base64.decode(currentImage, Base64.DEFAULT).size
                        ).asImageBitmap(),
                        contentDescription = "Imagen Cargada",
                        Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
