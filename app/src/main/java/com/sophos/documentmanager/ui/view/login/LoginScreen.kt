package com.sophos.documentmanager.ui.view.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material.icons.twotone.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sophos.documentmanager.R
import com.sophos.documentmanager.ui.theme.SophosLight
import com.sophos.documentmanager.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        Login(Modifier.align(Alignment.Center),viewModel)
    }
}

@Composable
fun Login(modifier: Modifier,viewModel: LoginViewModel){
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()
    val passwordVisibility by viewModel.passwordVisibility.observeAsState(initial = false)
    if(isLoading){
        Box(Modifier.fillMaxSize()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }else {
        Column(modifier = modifier) {

            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            TitleText(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email, { viewModel.onLoginChanged(it, password) })
            Spacer(modifier = Modifier.padding(16.dp))
            PasswordField(password,passwordVisibility, { viewModel.onLoginChanged(email, it) }){
                coroutineScope.launch {
                    viewModel.changePasswordVisibility()
                }
            }
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton(loginEnable) {
                coroutineScope.launch {
                    viewModel.onLoginSelected()
                }
            }
        }
    }
}

@Composable
fun HeaderImage(modifier: Modifier){
    Image(painter = painterResource(id = R.drawable.sophos_solutions),
        contentDescription = "Header",
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
fun TitleText(modifier: Modifier) {
    Text(text = "Ingresa tus datos para \nacceder",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = SophosLight,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Composable
fun EmailField(email: String, onTextFieldChanged:(String)  -> Unit) {

    OutlinedTextField(
        value = email, onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = SophosLight,
            backgroundColor = Color.White,
            focusedIndicatorColor = SophosLight,
            unfocusedIndicatorColor = SophosLight,
        ),
        shape = RoundedCornerShape(12.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "User", tint = SophosLight)
        },

    )
}

@Composable
fun PasswordField(password: String,passwordVisibility:Boolean, onTextFieldChanged:(String)  -> Unit, changePasswordVisibility:() -> Unit) {
    OutlinedTextField(
        value = password, onValueChange = {onTextFieldChanged(it)},
        placeholder = { Text(text = "Password")},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color.White,
            focusedIndicatorColor = SophosLight,
            unfocusedIndicatorColor = SophosLight
        ),
        shape = RoundedCornerShape(12.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Lock, contentDescription = "User", tint = SophosLight)
        },
        trailingIcon = {
            IconButton(
                onClick = { changePasswordVisibility() }
            ) {
                Icon(
                    imageVector = if(passwordVisibility) {
                        ImageVector.vectorResource(id = R.drawable.visibility)
                    } else {
                        ImageVector.vectorResource(id = R.drawable.visibilityoff)
                    },
                    contentDescription = "Toggle Password Icon"
                )
            }
        },
        visualTransformation = if(passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
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





