package com.sophos.documentmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sophos.documentmanager.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(){

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    fun onLoginChanged(email: String, password: String) {
        viewModelScope.launch{
            val result = loginUseCase(email,password)
            if (result != null) {
                if (result.access){
                    println(result)
                    _email.value = result.name
                    _password.value = result.surname
                }
            }
        }
    }
}