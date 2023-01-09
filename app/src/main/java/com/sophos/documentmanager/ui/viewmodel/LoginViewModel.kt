package com.sophos.documentmanager.ui.viewmodel

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sophos.documentmanager.domain.LoginUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    @ApplicationContext val context: Context,
    private val fragment: Fragment

) : ViewModel()  {

    private val _canAuthenticate = MutableLiveData<Boolean>()
    val canAuthenticate: LiveData<Boolean> = _canAuthenticate

    private val _auth = MutableLiveData<Boolean>()
    val auth: LiveData<Boolean> = _auth

    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _passwordVisibility = MutableLiveData<Boolean>()
    val passwordVisibility: LiveData<Boolean> = _passwordVisibility

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _titleDialog = MutableLiveData<String>()
    val titleDialog: LiveData<String> = _titleDialog

    private val _textDialog = MutableLiveData<String>()
    val textDialog: LiveData<String> = _textDialog


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6
    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    suspend fun onLoginSelected() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = loginUseCase(email.value ?: "", password.value ?: "")
            if (result != null) {
                if (result.access) {
                    println(result)
                    //TODO: Redirect to Home view
                } else {
                    _titleDialog.value = "Incorrect credentials"
                    _textDialog.value = "Email or Password incorrect"
                    changeShowDialog(true)
                }
            } else {
                _titleDialog.value = "Incorrect Connection"
                _textDialog.value =
                    "Incorrect connection check your internet connection and try again."
                changeShowDialog(true)
            }
            _isLoading.value = false
        }
    }

    suspend fun changePasswordVisibility() {
        _passwordVisibility.value = true
        delay(4000)
        _passwordVisibility.value = false
    }

    suspend fun changeShowDialog(value: Boolean) {
        _showDialog.value = value
    }
     fun setupAuth() {
        if (BiometricManager.from(fragment.context?:context)
                .canAuthenticate( BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
        ){
            _canAuthenticate.value = true

            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Authenticate Biometric")
                .setSubtitle("Authenticate yourself using the biometric sensor")
                .setNegativeButtonText("Enter your Sophos credentials")
                .build()
        }
    }

    suspend fun authenticate(){
        if (canAuthenticate.value == true){
            BiometricPrompt(fragment, ContextCompat.getMainExecutor(fragment.context?:context),
                object : BiometricPrompt.AuthenticationCallback(){
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        _auth.value = true
                    }
                }).authenticate(promptInfo)
        }else{
            _auth.value = false
        }
    }


}