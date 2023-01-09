package com.sophos.documentmanager

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.sophos.documentmanager.data.network.FragmentService
import com.sophos.documentmanager.domain.LoginUseCase
import com.sophos.documentmanager.ui.view.login.LoginScreen
import com.sophos.documentmanager.ui.viewmodel.LoginViewModel
import com.sophos.documentmanager.ui.theme.DocumentManagerTheme
import com.sophos.documentmanager.ui.theme.SophosLight
import com.sophos.documentmanager.ui.theme.SophosLightDisable
import com.sophos.documentmanager.ui.view.login.HomeScreen
import com.sophos.documentmanager.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var loginUseCase: LoginUseCase
    @Inject
    lateinit var fragment: FragmentService
    private var canAuthenticate = false
    private lateinit var promptInfo: BiometricPrompt.PromptInfo



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DocumentManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    LoginScreen(viewModel = LoginViewModel(loginUseCase,this,fragment))
                    HomeScreen(viewModel = HomeViewModel())
                }
                
            }
        }
        setupAuth()
    }

    private fun setupAuth() {
        Log.d("DeviceCredential",BiometricManager.from(this)
            .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG).toString())
        if (BiometricManager.from(this)
                .canAuthenticate( BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
        ){
            canAuthenticate = true

            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Authenticate Biometric")
                .setSubtitle("Authenticate yourself using the biometric sensor")
                .setNegativeButtonText("Enter your Sophos credentials")
                .build()
        }
    }

    private fun authenticate(auth:(Boolean) -> Unit){
        if (canAuthenticate){
            BiometricPrompt(this, ContextCompat.getMainExecutor(this),
                object : BiometricPrompt.AuthenticationCallback(){
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        auth(true)
                    }
                }).authenticate(promptInfo)
        }else{
            auth(true)
        }
    }

    @Composable
    fun FingerPrintLoginButton() {
        var auth by remember{ mutableStateOf(false) }
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

            ){
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.fingerprint), contentDescription = "Icon finger print login")
                Text(text = "Ingresar con huella")
            }
        }

    }
}
