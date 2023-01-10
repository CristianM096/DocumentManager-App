package com.sophos.documentmanager.ui.view.document

import android.content.ClipData.Item
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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


@Composable
fun CopyScren(navController: NavController) {
    Copy(navController)
}
@Composable
fun Copy(navController: NavController){

    Scaffold(
        content = { contentCopy()},
        topBar = { topBar(navController,
            {
                ReturnButton()
            },
            auth = "Cualquier Cosa") }
    )
}
@Composable
fun contentCopy(){
    var id = remember { mutableStateOf(R.drawable.home_corporative) }
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.align(Alignment.TopCenter)) {
            Column() {
                Text(text = "Documentos",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Image(
                    painter = painterResource(id = id.value),
                    contentDescription = "Header",
                    modifier = Modifier
                        .size(width = 300.dp, height = 250.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            LazyColumn( modifier = Modifier.fillMaxWidth() ){
                item{
                    DocumentItem(
                        onClick = {
                            id.value = R.drawable.home_corporative
                        },
                        date = "17/08/22",
                        reason = "Incapacidad",
                        name = "Cristian Manzano"
                    )
                }
                item{
                    DocumentItem(
                        onClick = {
                            id.value = R.drawable.sophos_solutions
                        },
                        date = "25/10/22",
                        reason = "Factura",
                        name = "Cristian Manzano"
                    )
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