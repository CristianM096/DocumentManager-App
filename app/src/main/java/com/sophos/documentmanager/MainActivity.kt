package com.sophos.documentmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.sophos.documentmanager.data.repository.UserRepository
import com.sophos.documentmanager.domain.LoginUseCase
import com.sophos.documentmanager.ui.view.login.LoginScreen
import com.sophos.documentmanager.ui.viewmodel.LoginViewModel
import com.sophos.documentmanager.ui.theme.DocumentManagerTheme
import com.sophos.documentmanager.ui.view.login.HomeScreen
import com.sophos.documentmanager.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var loginUseCase: LoginUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DocumentManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen(viewModel = HomeViewModel(loginUseCase))
                }
            }
        }
    }
}
