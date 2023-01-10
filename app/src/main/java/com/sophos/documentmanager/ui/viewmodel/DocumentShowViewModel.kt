package com.sophos.documentmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sophos.documentmanager.data.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DocumentShowViewModel @Inject constructor(): ViewModel(){

    private val _auth = MutableLiveData<String>()
    val auth: LiveData<String> = _auth
    var user : UserModel = UserModel()
    var created : Boolean = false

    fun onCreate(authenticate: String?){
        user = Gson().fromJson(authenticate, UserModel::class.java)
        _auth.value = authenticate?:""
        created = true

    }
}