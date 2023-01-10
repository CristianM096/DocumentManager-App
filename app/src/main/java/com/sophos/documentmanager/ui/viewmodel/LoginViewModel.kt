package com.sophos.documentmanager.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sophos.documentmanager.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel()  {

    private val _auth = MutableLiveData<String>()
    val auth: LiveData<String> = _auth

    private val _authenticated = MutableLiveData<Boolean>()
    val authenticated: LiveData<Boolean> = _authenticated

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
            //val result = loginUseCase(email.value ?: "", password.value ?: "") TODO: DESCOMENTAR Y BORRAR LA LINEA DE ABAJO
            val result = loginUseCase(email = "cristianorb@unicauca.edu.co", password = "PIpP0553v058")
            print(result)
            if (result != null) {
                if (result.access) {
                    println(result)
                    println(Gson().toJson(result))
                    _auth.value = Gson().toJson(result)
                    _authenticated.value = true
                } else {
                    _titleDialog.value = "Incorrect credentials"
                    _textDialog.value = "Email or Password incorrect"
                    changeShowDialog(true)
                    _authenticated.value = false
                }
            } else {
                _titleDialog.value = "Incorrect Connection"
                _textDialog.value = "Incorrect connection check your internet connection and try again."
                changeShowDialog(true)
                _authenticated.value = false
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
//     fun setupAuth() {
//        if (BiometricManager.from(fragment.context?:context)
//                .canAuthenticate( BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
//        ){
//            _canAuthenticate.value = true
//
//            promptInfo = BiometricPrompt.PromptInfo.Builder()
//                .setTitle("Authenticate Biometric")
//                .setSubtitle("Authenticate yourself using the biometric sensor")
//                .setNegativeButtonText("Enter your Sophos credentials")
//                .build()
//        }
//    }
//
//    suspend fun authenticate(){
//        if (canAuthenticate.value == true){
//            BiometricPrompt(fragment, ContextCompat.getMainExecutor(fragment.context?:context),
//                object : BiometricPrompt.AuthenticationCallback(){
//                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
//                        super.onAuthenticationSucceeded(result)
//                        _auth.value = true
//                    }
//                }).authenticate(promptInfo)
//        }else{
//            _auth.value = false
//        }
//    }


}