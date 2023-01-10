package com.sophos.documentmanager.ui.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sophos.documentmanager.data.model.AttachmentModel
import com.sophos.documentmanager.data.model.UserModel
import com.sophos.documentmanager.domain.GetAttachmentUseCase
import com.sophos.documentmanager.domain.GetAttachmentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentShowViewModel @Inject constructor(
    private val getAttachmentUseCase: GetAttachmentUseCase,
    private val getAttachmentsUseCase: GetAttachmentsUseCase
): ViewModel(){

    private val _auth = MutableLiveData<String>()
    val auth: LiveData<String> = _auth
    var user : UserModel = UserModel()
    var created : Boolean = false
    var attachments : List<AttachmentModel> = emptyList()
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onCreate(authenticate: String?){
        user = Gson().fromJson(authenticate, UserModel::class.java)
        _auth.value = authenticate?:""
        created = true
        viewModelScope.launch{
            attachments = getAttachmentsUseCase("cristianorb@unicauca.edu.co") ?: emptyList()
        }
    }
}