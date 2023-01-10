package com.sophos.documentmanager

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.sophos.documentmanager.ui.navigation.AppNavigation
import com.sophos.documentmanager.ui.theme.SophosLight
import com.sophos.documentmanager.ui.theme.SophosLightDisable
import com.sophos.documentmanager.ui.viewmodel.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity @Inject constructor() : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val documentShowViewModel: DocumentShowViewModel by viewModels()
    private val documentCreateViewModel: DocumentCreateViewModel by viewModels()
    private val officeShowViewModel: OfficeShowViewModel by viewModels()
    private val imageShowViewModel : ImageShowViewModel by viewModels()

    private var canAuthenticate = false
    private lateinit var promptInfo: BiometricPrompt.PromptInfo


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AppNavigation(
                loginViewModel = loginViewModel,
                homeViewModel = homeViewModel,
                documentCreateViewModel = documentCreateViewModel,
                documentShowViewModel = documentShowViewModel,
                officeShowViewModel = officeShowViewModel,
                imageShowViewModel = imageShowViewModel
            )
        }
//        setupAuth()
    }

    @Composable
    fun huella(){
        authenticate {  }
    }

    private fun setupAuth() {
        Log.d(
            "DeviceCredential", BiometricManager.from(this)
                .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG).toString()
        )
        if (BiometricManager.from(this)
                .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
        ) {
            canAuthenticate = true

            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Authenticate Biometric")
                .setSubtitle("Authenticate yourself using the biometric sensor")
                .setNegativeButtonText("Enter your Sophos credentials")
                .build()
        }
    }

    private fun authenticate(auth: (Boolean) -> Unit) {
        if (canAuthenticate) {
            BiometricPrompt(this, ContextCompat.getMainExecutor(this),
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        auth(true)
                    }
                }).authenticate(promptInfo)
        } else {
            auth(true)
        }
    }

    @Composable
    fun FingerPrintLoginButton() {
        var auth by remember { mutableStateOf(false) }
        Column() {
            Button(
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    authenticate { auth = it }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    disabledBackgroundColor = Color.White,
                    contentColor = SophosLight,
                    disabledContentColor = SophosLightDisable,
                ),
                border = BorderStroke(1.dp, SophosLight)

            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.fingerprint),
                    contentDescription = "Icon finger print login"
                )
                Text(text = "Ingresar con huella")
            }
        }

    }
}

//@ExperimentalAnimationApi
//fun NavGraphBuilder.addLogin(
//    navController: NavHostController,
//    viewModel: LoginViewModel
//) {
//    composable(
//        route = Destinations.Login.route,
//        enterTransition = { _, _ ->
//            slideInHorizontally(
//                initialOffsetX = { 1000 },
//                animationSpec = tween(500)
//            )
//        },
//        exitTransition = { _, _ ->
//            slideOutHorizontally(
//                targetOffsetX = { -1000 },
//                animationSpec = tween(500)
//            )
//        },
//        popEnterTransition = { _, _ ->
//            slideInHorizontally(
//                initialOffsetX = { -1000 },
//                animationSpec = tween(500)
//            )
//        },
//        popExitTransition = { _, _ ->
//            slideOutHorizontally(
//                targetOffsetX = { 1000 },
//                animationSpec = tween(500)
//            )
//        }
//    ) {
//        val user = viewModel.state.value.user
//        if (viewModel.state.value.successLogin){
//            LaunchedEffect(key1 = Unit){
//                navController.navigate(Destinations.Home.route + "/$user"){
//                    popUpTo(Destinations.Login.route){
//                        inclusive = true
//                    }
//                }
//            }
//        }else{
//            LoginScreen(
//                viewModel = viewModel,
//                onNavigateToHome = { navController.navigate(Destinations.Home.route) }
//            )
//        }
//    }
//}
//@RequiresApi(Build.VERSION_CODES.TIRAMISU)
//@ExperimentalAnimationApi
//fun NavGraphBuilder.addHome(
//    navController: NavHostController,
//    viewModel: HomeViewModel
//){
//    composable(
//        route = Destinations.Home.route + "/{auth}",
//        arguments = Destinations.Home.arguments,
//        enterTransition = {_, _ ->
//            slideInHorizontally (
//                initialOffsetX = {1000},
//                animationSpec = tween(500)
//            )
//        },
//        exitTransition = {_,_ ->
//            slideOutHorizontally (
//                targetOffsetX = {-1000},
//                animationSpec = tween(500)
//            )
//        },
//        popEnterTransition = {_, _ ->
//            slideInHorizontally (
//                initialOffsetX = {-1000},
//                animationSpec = tween(500)
//            )
//        },
//        popExitTransition = {_, _ ->
//            slideOutHorizontally (
//                targetOffsetX = {1000},
//                animationSpec = tween(500)
//            )
//        }
//    ){ backStackEntry ->
//        val user = backStackEntry.arguments?.getParcelable<UserModel>("auth",UserModel::class.java)
//        //TODO: onBack = {navController.popBackStack()}
//        HomeScreen(viewModel = viewModel, userModel = user)
//    }
//}
//@ExperimentalAnimationApi
//fun NavGraphBuilder.addDocumentCreate(
//){
//    composable(
//        route=Destinations.DocumentCreate.route,
//    ){
//        DocumentCreateScreen()
//    }
//}
//@ExperimentalAnimationApi
//fun NavGraphBuilder.addDocumentShow(
//){
//    composable(
//        route=Destinations.DocumentShow.route,
//    ){
//        DocumentShowScreen()
//    }
//}
//@ExperimentalAnimationApi
//fun NavGraphBuilder.addOfficeShow(
//){
//    composable(
//        route=Destinations.OfficeShow.route,
//    ){
//        OfficeShowScreen()
//    }
//}